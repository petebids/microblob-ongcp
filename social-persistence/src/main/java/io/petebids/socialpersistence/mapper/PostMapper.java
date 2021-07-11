package io.petebids.socialpersistence.mapper;

import io.petebids.socialpersistence.model.document.PostDocument;
import io.petebids.socialpersistence.model.domain.Post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post toPost(PostDocument document);

    PostDocument toDocument(Post post);

    List<Post> toPosts(List<PostDocument> documents);


}
