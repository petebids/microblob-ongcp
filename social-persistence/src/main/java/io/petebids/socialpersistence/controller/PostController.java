package io.petebids.socialpersistence.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.petebids.socialpersistence.model.document.PostDocument;
import io.petebids.socialpersistence.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class PostController {
    Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    PostService postService;


    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDocument> getById(@PathVariable String id) {
        logger.info("invoked with {}", id);
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/posts")
    public ResponseEntity<List<PostDocument>> getAll() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);

    }

    @PostMapping("/posts")
    public ResponseEntity<PostDocument> create(@RequestBody Map<String, String> requestBody) throws JsonProcessingException, ExecutionException, InterruptedException {
        String content = requestBody.get("content");
        logger.info("invoked with {}", content);
        return new ResponseEntity<>(postService.createPost(content), HttpStatus.CREATED);
    }

    @DeleteMapping("/posts")
    public ResponseEntity<Void> deleteAll() {
        postService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/posts/push-language-detection")
    public ResponseEntity<Void> pushLanguageDetection(){
        postService.getAllPosts().forEach(postDocument -> {
            try {
                postService.emitPostCreated(postDocument);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return new ResponseEntity(HttpStatus.OK);
    }
}
