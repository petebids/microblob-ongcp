package io.petebids.socialpersistence.model.document;

import java.util.Date;

public class ProfanityInfo {

    Boolean profane;

    Date detectedAt;

    public ProfanityInfo(Boolean profane, Date detectedAt) {
        this.profane = profane;
        this.detectedAt = detectedAt;
    }

    public Boolean getProfane() {
        return profane;
    }

    public void setProfane(Boolean profane) {
        this.profane = profane;
    }

    public Date getDetectedAt() {
        return detectedAt;
    }

    public void setDetectedAt(Date detectedAt) {
        this.detectedAt = detectedAt;
    }
}
