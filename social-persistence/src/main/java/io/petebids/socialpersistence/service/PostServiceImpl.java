package io.petebids.socialpersistence.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.petebids.socialpersistence.mapper.PostMapper;
import io.petebids.socialpersistence.model.document.LanguageInfo;
import io.petebids.socialpersistence.model.document.PostDocument;
import io.petebids.socialpersistence.model.document.ProfanityInfo;
import io.petebids.socialpersistence.model.domain.Post;
import io.petebids.socialpersistence.model.domain.event.LanguageDetected;
import io.petebids.socialpersistence.model.domain.event.PostCreated;
import io.petebids.socialpersistence.model.domain.event.ProfanityDetected;
import io.petebids.socialpersistence.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class KafkaMongoPostService implements PostService {
    Logger logger = LoggerFactory.getLogger(KafkaMongoPostService.class);

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    PostRepository postRepository;


    @Autowired
    ReactiveMongoTemplate mongoTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    PostMapper postMapper;

    @Override
    public List<Post> getAllPosts() {
        return postMapper.toPosts(postRepository.findAll());
    }

    @Override
    public Post findById(String id) {
        logger.info("invoked with {}", id);
        PostDocument document = postRepository.findById(id).get();
        Post post = postMapper.toPost(document);
        return post;
    }

    @Override
    public Post createPost(String content) throws JsonProcessingException, ExecutionException, InterruptedException {
        logger.info("invoked with {}", content);
        PostDocument postDocument = new PostDocument();
        postDocument.setContent(content);
        postDocument.setId(UUID.randomUUID().toString());
        postRepository.save(postDocument);
        logger.info("created post {}", postDocument);
        return postMapper.toPost(postDocument);
    }

    @Override
    public void deleteAll() {
        postRepository.deleteAll();
    }


    @Override
    @KafkaListener(topics = "language-detected-v2", containerFactory = "kafkaListenerContainerFactory")
    public void handleLanguageDetected(String message) {
        logger.info("recieved {}", message);
        LanguageDetected languageDetected;
        try {
            languageDetected = objectMapper.readValue(message, LanguageDetected.class);
        } catch (JsonProcessingException e) {
            logger.error("json processing error {}", e.getMessage());
            return;
        }
        logger.info("deserialized {} ", languageDetected);
        String postId = languageDetected.getPostId();
        String language = languageDetected.getLanguage();
        logger.info("recived event detecting language {} for post {}", language, postId);
        Optional<PostDocument> optional = postRepository.findById(postId);
        if (!optional.isPresent()) {
            logger.error("post not found");
            return;
        }
        PostDocument post = optional.get();
        LanguageInfo languageInfo = new LanguageInfo(languageDetected.getLanguage(), new Date());
        post.setLanguageInfo(languageInfo);
        postRepository.save(post);
    }

    @Override
    @KafkaListener(topics = "profanity-detected-v3", containerFactory = "kafkaListenerContainerFactory")
    public void handleProfanityDetected(String message) throws JsonProcessingException {
        logger.info("recieved {}", message);
        ProfanityDetected profanityDetected = objectMapper.readValue(message, ProfanityDetected.class);
        logger.info("profanity detected {} ", profanityDetected);
        PostDocument post = postRepository.findById(profanityDetected.getPostId()).get();
        ProfanityInfo profanityInfo = new ProfanityInfo(profanityDetected.getProfane(), new Date());
        post.setProfanityInfo(profanityInfo);
        postRepository.save(post);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info(event.toString());
        mongoTemplate.changeStream(PostDocument.class)
                .watchCollection("postDocument")
                .filter(where("opertaionType").is("insert"))
                .listen()
                .map(postDocumentChangeStreamEvent -> {
                    logger.info(postDocumentChangeStreamEvent.toString());
                    PostDocument postDocument = postDocumentChangeStreamEvent.getBody();
                    PostCreated postCreated = new PostCreated(postDocument.getId(), postDocument.getContent());
                    String json = null;
                    try {
                        json = objectMapper.writeValueAsString(postCreated);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    logger.info("created event {}", json);
                    try {
                        kafkaTemplate.send("post-created", json).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    logger.info("event emiited");
                    return null;
                });


    }

    @Override
    public void emitPostCreated(Post post) throws JsonProcessingException, ExecutionException, InterruptedException {
        PostCreated postCreated = new PostCreated(post.getId(), post.getContent());
        String json = objectMapper.writeValueAsString(postCreated);
        logger.info("created event {}", json);
        kafkaTemplate.send("post-created", json).get();
        logger.info("event emiited");

    }


}
