package io.petebids.socialpersistence.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.petebids.socialpersistence.model.domain.Post;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/posts")
public class PostController {
    Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    PostService postService;


    @GetMapping("/{id}")
    public ResponseEntity<Post> getById(@PathVariable String id) {
        logger.info("invoked with {}", id);
        Post post = postService.findById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        var allPosts = postService.getAllPosts();
        logger.info("got {} posts", allPosts.size());
        return new ResponseEntity<>(allPosts, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Map<String, String> requestBody) throws JsonProcessingException, ExecutionException, InterruptedException {
        String content = requestBody.get("content");
        logger.info("invoked with {}", content);
        var post = postService.createPost(content);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        postService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/push-language-detection")
    public ResponseEntity<Void> pushLanguageDetection() {
        postService.getAllPosts().forEach(post -> {
            try {
                postService.emitPostCreated(post);
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
