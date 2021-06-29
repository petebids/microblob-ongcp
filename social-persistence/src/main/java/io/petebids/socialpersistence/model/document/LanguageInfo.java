package io.petebids.socialpersistence.model.document;

import java.util.Date;

public class LanguageInfo {

    String language;

    Date detectedAt;


    public LanguageInfo(String language, Date detectedAt) {
        this.language = language;
        this.detectedAt = detectedAt;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getDetectedAt() {
        return detectedAt;
    }

    public void setDetectedAt(Date detectedAt) {
        this.detectedAt = detectedAt;
    }
}
