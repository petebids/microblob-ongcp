package io.petebids.socialrestapi.model;

import java.time.LocalDate;

import java.util.UUID;

public class PostCreated {
    private LocalDate created;
    private UUID id;

    private String content;

    public PostCreated() {
    }

    public PostCreated(LocalDate created, UUID id, String content) {
        this.created = created;
        this.id = id;
        this.content = content;
    }

    public LocalDate getCreated() {
        return this.created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
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

    public PostCreated created(LocalDate created) {
        this.created = created;
        return this;
    }

    public PostCreated id(UUID id) {
        this.id = id;
        return this;
    }

    public PostCreated content(String content) {
        this.content = content;
        return this;
    }



    @Override
    public String toString() {
        return "{" +
            " created='" + getCreated() + "'" +
            ", id='" + getId() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
    
}