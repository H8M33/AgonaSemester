package com.technokratos.repository;

import com.technokratos.model.PostEntity;
import dto.response.PostResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {

    Optional<PostEntity> findById(UUID id);

    List<PostEntity> getPostsByUser_Id(UUID userId);
}
