package io.petebids.sociallanguagedetect.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.petebids.sociallanguagedetect.model.LanguageDetected;
import io.petebids.sociallanguagedetect.model.PostCreated;
import io.petebids.sociallanguagedetect.service.LanguageDetectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

@ConditionalOnProperty(value = "${app.kafka.enabled}", havingValue = "yes")
@Component
public class KafkaAdapter {

    Logger logger = LoggerFactory.getLogger(KafkaAdapter.class);

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    LanguageDetectionService languageDetectionService;

    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = "post-created")
    public void listen(@Payload String message) throws JsonProcessingException, ExecutionException, InterruptedException {
        PostCreated postCreated = objectMapper.readValue(message, PostCreated.class);
        LanguageDetected languageDetected = languageDetectionService.detectLanguage(postCreated);
        String output = objectMapper.writeValueAsString(languageDetected);
        handleAsyncResponse(kafkaTemplate.send("language-detected", output));
    }


    @Async
    public void handleAsyncResponse(ListenableFuture<SendResult<String, String>> futureSendResult) throws ExecutionException, InterruptedException {
        logger.info("handling callback");
        futureSendResult.get();


    }

}
