package com.technokratos.repository;

import com.technokratos.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findById(UUID id);

    List<UserEntity> getUsersByChats_Id(UUID chatID);

    UserEntity getUserByPosts_Id(UUID postId);
}
