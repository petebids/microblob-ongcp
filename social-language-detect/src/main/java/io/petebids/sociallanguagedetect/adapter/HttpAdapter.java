package io.petebids.sociallanguagedetect.adapter;


import io.petebids.sociallanguagedetect.model.LanguageDetected;
import io.petebids.sociallanguagedetect.model.PostCreated;
import io.petebids.sociallanguagedetect.service.LanguageDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@ConditionalOnProperty(value = "${app.http.enabled}", havingValue = "true")
@RestController
public class HttpAdapter {

    @Autowired
    LanguageDetectionService languageDetectionService;


    @PostMapping("/detect-language")
    public ResponseEntity<LanguageDetected> detectLanguage(@RequestBody PostCreated postCreated) {
        LanguageDetected languageDetected = languageDetectionService.detectLanguage(postCreated);
        return new ResponseEntity<>(languageDetected, HttpStatus.CREATED);
    }
}
