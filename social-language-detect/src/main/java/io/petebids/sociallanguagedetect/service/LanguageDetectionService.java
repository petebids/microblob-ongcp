package io.petebids.sociallanguagedetect.service;

import io.petebids.sociallanguagedetect.model.LanguageDetected;
import io.petebids.sociallanguagedetect.model.PostCreated;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pemistahl.lingua.api.Language;
import com.github.pemistahl.lingua.api.LanguageDetector;
import com.github.pemistahl.lingua.api.LanguageDetectorBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

@Service
public class LanguageDetectionService {

    @Value("$app.languagedetected.topic")
    private String OUTBOUND_TOPIC;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    LanguageDetector langDetector = LanguageDetectorBuilder.fromLanguages(Language.ENGLISH,Language.LATIN, Language.FRENCH).build();

    @Autowired
    ObjectMapper objectMapper;

    Logger logger = LoggerFactory.getLogger(LanguageDetectionService.class);
    // TODO https://stackoverflow.com/questions/60553992/how-do-i-deserialize-a-kafka-message-to-a-pojo 
    @KafkaListener(topics = "post-created", groupId = "${app.consumergroup}")
    public void detectLanguage(@Payload String message ) {
        try{
            logger.info("recieved message " + message);
            PostCreated post = objectMapper.readValue(message, PostCreated.class);
            Language language = langDetector.detectLanguageOf(post.getContent());
            logger.info("detected lanaguage "+ language);
            LanguageDetected ld = new LanguageDetected(post.getId(), language.toString());
            logger.info("language detected {}", ld);
            String output = objectMapper.writeValueAsString(ld);
            logger.info("emitting language detected event {}", output);
            kafkaTemplate.send("language-detected-v2", output).get();

        }
        catch( JsonProcessingException e ){
            logger.error(e.getMessage());

        }
        catch(Throwable t ){
            logger.error(t.getMessage());
        }

    }

}