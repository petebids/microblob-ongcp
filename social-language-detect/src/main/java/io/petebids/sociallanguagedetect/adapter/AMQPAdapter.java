package io.petebids.sociallanguagedetect.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.petebids.sociallanguagedetect.model.LanguageDetected;
import io.petebids.sociallanguagedetect.model.PostCreated;
import io.petebids.sociallanguagedetect.service.LanguageDetectionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(value = "${app.amqp.enabled}", havingValue = "true")
@Component
public class AMQPAdapter {

    @Autowired
    LanguageDetectionService languageDetectionService;

    @Autowired
    ObjectMapper objectMapper;


    @RabbitListener(queues = "post-created")
    @SendTo("language-detected")
    public LanguageDetected listen(@Payload String message) throws JsonProcessingException {
        PostCreated postCreated = objectMapper.readValue(message, PostCreated.class);
        return languageDetectionService.detectLanguage(postCreated);
    }
}
