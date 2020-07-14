package io.petebids.socialrestapi.model;

import java.util.UUID;

public class Post {
    private UUID id;

    private String content;

    public Post() {
    }

    public Post(UUID id, String content) {
        this.id = id;
        this.content = content;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post id(UUID id) {
        this.id = id;
        return this;
    }

    public Post content(String content) {
        this.content = content;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
}