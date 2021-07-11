package io.petebids.socialpersistence.model.domain;

public class Post {

    private String id;

    private String content;

    public Post(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public Post() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
