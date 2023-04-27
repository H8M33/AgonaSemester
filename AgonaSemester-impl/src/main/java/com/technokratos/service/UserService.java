package com.technokratos.service;

import com.technokratos.model.UserEntity;
import dto.request.PostRequest;
import dto.request.UserRequest;
import dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void createUser(UserRequest request);

    UserResponse updateUser(UUID userId, UserRequest request);

    void deleteUserById(UUID userId);

    UserResponse getUserById(UUID userId);

    List<UserResponse> getUsers();

    UserEntity getUserEntity(UUID userid);

    List<UserResponse> getUsersByChatId(UUID chatID);

    UserResponse getUserByPostId(UUID postId);

    void saveUser(UserEntity entity);
}
