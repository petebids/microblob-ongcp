package io.petebids.socialpersistence.model.document;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PostDocument {

    @Id
    String id;

    @Version
    Long version;


    String content;

    LanguageInfo languageInfo;

    ProfanityInfo profanityInfo;

    Boolean recommended;

    public PostDocument(String id, Long version, String content, LanguageInfo languageInfo, ProfanityInfo profanityInfo, Boolean recommended) {
        this.id = id;
        this.version = version;
        this.content = content;
        this.languageInfo = languageInfo;
        this.profanityInfo = profanityInfo;
        this.recommended = recommended;
    }

    public PostDocument() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LanguageInfo getLanguageInfo() {
        return languageInfo;
    }

    public void setLanguageInfo(LanguageInfo languageInfo) {
        this.languageInfo = languageInfo;
    }

    public ProfanityInfo getProfanityInfo() {
        return profanityInfo;
    }

    public void setProfanityInfo(ProfanityInfo profanityInfo) {
        this.profanityInfo = profanityInfo;
    }

    public Boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }
}

