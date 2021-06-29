package io.petebids.socialpersistence.model.event;

public class ProfanityDetected {

    Boolean profane;

    String postId;

    public ProfanityDetected(Boolean profane, String postId) {
        this.profane = profane;
        this.postId = postId;
    }

    public Boolean getProfane() {
        return profane;
    }

    public void setProfane(Boolean profane) {
        this.profane = profane;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
