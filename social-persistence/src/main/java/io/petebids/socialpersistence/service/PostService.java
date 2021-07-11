package io.petebids.socialpersistence.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.petebids.socialpersistence.model.domain.Post;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public interface PostService {

    List<Post> getAllPosts();

    List<Post> getApprovedPosts();

    Post findById(String id);

    Post createPost(String content) throws JsonProcessingException, ExecutionException, InterruptedException;

    void deleteAll();


    /**
     * @param message
     */
    void handleLanguageDetected(String message);

    void handleProfanityDetected(String message) throws JsonProcessingException;

    void emitPostCreated(Post post) throws JsonProcessingException, ExecutionException, InterruptedException;
}
