package io.petebids.sociallanguagedetect.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pemistahl.lingua.api.Language;
import com.github.pemistahl.lingua.api.LanguageDetector;
import io.petebids.sociallanguagedetect.model.LanguageDetected;
import io.petebids.sociallanguagedetect.model.PostCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

@Service
public class LanguageDetectionService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    LanguageDetector langDetector;
    @Autowired
    ObjectMapper objectMapper;
    Logger logger = LoggerFactory.getLogger(LanguageDetectionService.class);
    @Value("$app.languagedetected.topic")
    private String OUTBOUND_TOPIC;

    // TODO https://stackoverflow.com/questions/60553992/how-do-i-deserialize-a-kafka-message-to-a-pojo
    @KafkaListener(topics = "post-created", groupId = "${app.consumergroup}")
    public void detectLanguage(@Payload String message) {
        try {
            logger.info("recieved message " + message);
            PostCreated post = objectMapper.readValue(message, PostCreated.class);
            Language language = langDetector.detectLanguageOf(post.getContent());
            logger.info("detected lanaguage " + language);
            LanguageDetected languageDetected = new LanguageDetected(post.getId(), language.toString());
            logger.info("language detected {}", languageDetected);
            String output = objectMapper.writeValueAsString(languageDetected);
            logger.info("emitting language detected event {}", output);
            handleAsyncResponse(kafkaTemplate.send("language-detected-v2", output));


        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());

        } catch (Throwable t) {
            logger.error(t.getMessage());
        }

    }

    @Async
    public void handleAsyncResponse(ListenableFuture<SendResult<String, String>> futureSendResult) throws ExecutionException, InterruptedException {
        logger.info("handling callback");
        futureSendResult.get();


    }

}