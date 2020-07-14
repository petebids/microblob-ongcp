package io.petebids.socialrestapi.model;

import java.util.UUID;


import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;



public class PostEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String content;

    private String language;

    private boolean profane;


    public PostEntity() {
    }

    public PostEntity(UUID id, String content, String language, boolean profane) {
        this.id = id;
        this.content = content;
        this.language = language;
        this.profane = profane;
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

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isProfane() {
        return this.profane;
    }

    public boolean getProfane() {
        return this.profane;
    }

    public void setProfane(boolean profane) {
        this.profane = profane;
    }

    public PostEntity id(UUID id) {
        this.id = id;
        return this;
    }

    public PostEntity content(String content) {
        this.content = content;
        return this;
    }

    public PostEntity language(String language) {
        this.language = language;
        return this;
    }

    public PostEntity profane(boolean profane) {
        this.profane = profane;
        return this;
    }



    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", content='" + getContent() + "'" +
            ", language='" + getLanguage() + "'" +
            ", profane='" + isProfane() + "'" +
            "}";
    }
        
}