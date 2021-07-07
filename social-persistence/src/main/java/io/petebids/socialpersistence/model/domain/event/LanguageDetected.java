package io.petebids.socialpersistence.model.domain.event;


public class LanguageDetected {

    String postId;

    String language;

    public LanguageDetected(String postId, String language) {
        this.postId = postId;
        this.language = language;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
