package io.petebids.socialpersistence.repository;

import io.petebids.socialpersistence.model.document.PostDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<PostDocument, String> {

    @Query("{'approved': true }")
    List<PostDocument> findApproved();

}
