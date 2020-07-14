package io.petebids.socialrestapi.service;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.petebids.socialrestapi.model.Post;
import io.petebids.socialrestapi.model.PostCreated;
import io.petebids.socialrestapi.model.PostEntity;
import io.petebids.socialrestapi.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    KafkaTemplate<String , String> kafkaTemplate;

    @Value("$app.postcreated.topic")
    String topic;




    public ResponseEntity<Post> getById(UUID id ){
        PostEntity entity = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Post post = new Post();
        post.setId(entity.getId());
        post.setContent(entity.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(post); 
    }

    public ResponseEntity<?> deleteByID(UUID id ){
        postRepository.deleteById(id);
        return ResponseEntity.noContent().build() ; 
    }

    public ResponseEntity<Post> createPost(Post post){
        try{
            PostCreated event = new PostCreated();
            event.created(LocalDate.now())
                .content(post.getContent());
            ObjectMapper objectMapper = new ObjectMapper();
            String postMessage = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(topic, postMessage );
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(post);
        }
        catch (JsonProcessingException j ){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY) ; 

        }
    }
}