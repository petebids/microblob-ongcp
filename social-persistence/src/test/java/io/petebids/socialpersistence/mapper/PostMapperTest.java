package io.petebids.socialpersistence.mapper;

import io.petebids.socialpersistence.model.document.PostDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PostMapperTest {

    @Autowired
    PostMapper postMapper;


    @Test
    public void givenValidPostDocument_WhenMapInvoked_ThenPropertiesMatch() {
        var document = new PostDocument();
        document.setId("adgkjlsld");
        document.setContent("lorem ipsum");
        var post = postMapper.toPost(document);
        assertEquals(document.getContent(), post.getContent());
        assertEquals(document.getId(), post.getId());


    }

}