package io.petebids.sociallanguagedetect.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pemistahl.lingua.api.Language;
import com.github.pemistahl.lingua.api.LanguageDetector;
import io.petebids.sociallanguagedetect.model.LanguageDetected;
import io.petebids.sociallanguagedetect.model.PostCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LanguageDetectionService {
    Logger logger = LoggerFactory.getLogger(LanguageDetectionService.class);

    @Autowired
    LanguageDetector langDetector;
    @Autowired
    ObjectMapper objectMapper;


    public LanguageDetected detectLanguage(PostCreated post) {

        Language language = langDetector.detectLanguageOf(post.getContent());
        logger.info("detected lanaguage " + language);
        LanguageDetected languageDetected = new LanguageDetected(post.getId(), language.toString());
        return languageDetected;
    }


}