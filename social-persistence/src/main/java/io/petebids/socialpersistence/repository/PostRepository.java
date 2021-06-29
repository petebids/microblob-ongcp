package io.petebids.socialpersistence.repository;

import io.petebids.socialpersistence.model.document.PostDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<PostDocument, String> {
}
