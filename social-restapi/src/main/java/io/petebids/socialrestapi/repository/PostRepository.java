package io.petebids.socialrestapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import io.petebids.socialrestapi.model.PostEntity;

public interface PostRepository extends PagingAndSortingRepository<PostEntity, UUID>{

    public List<PostEntity> findByProfane(Boolean profanity, Pageable pageable);
    
}