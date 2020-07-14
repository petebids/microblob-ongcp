package io.petebids.sociallanguagedetect.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
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

@Service
public class LanguageDetectionService {

    @Value("$app.languagedetected.topic")
    private String OUTBOUND_TOPIC;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    LanguageDetector langDetector = LanguageDetectorBuilder.fromLanguages(Language.ENGLISH, Language.FRENCH).build();

    Logger logger = LoggerFactory.getLogger(LanguageDetectionService.class);
    // TODO https://stackoverflow.com/questions/60553992/how-do-i-deserialize-a-kafka-message-to-a-pojo 
    @KafkaListener(topics = "${app.postcreated.topic}", groupId = "${app.consumergroup}")
    public void detectLanguage(@Payload String message) {
        try{
            logger.info("recieved message " + message);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode post = objectMapper.readTree(message);
            Language language = langDetector.detectLanguageOf(post.get("content").asText());
            logger.info("detected lanaguage "+ language);

        }
        catch( JsonProcessingException e ){
            logger.error(e.getMessage());

        }
        catch(Throwable t ){
            logger.error(t.getMessage());
        }

    }

}