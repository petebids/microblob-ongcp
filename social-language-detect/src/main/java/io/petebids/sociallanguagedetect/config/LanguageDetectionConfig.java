package io.petebids.sociallanguagedetect.config;

import com.github.pemistahl.lingua.api.Language;
import com.github.pemistahl.lingua.api.LanguageDetector;
import com.github.pemistahl.lingua.api.LanguageDetectorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LanguageDetectionConfig {

    @Bean
    LanguageDetector languageDetector() {
        //@formatter:off
        return LanguageDetectorBuilder.fromLanguages(
                        Language.ENGLISH,
                        Language.LATIN,
                        Language.FRENCH)
                .build();
        //@formatter:on
    }
}

