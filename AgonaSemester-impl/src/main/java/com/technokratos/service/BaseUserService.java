package com.technokratos.service;

import com.technokratos.exception.UserNotFoundException;
import com.technokratos.model.UserEntity;
import com.technokratos.repository.UserRepository;
import com.technokratos.util.mapper.UserMapper;
import dto.request.PostRequest;
import dto.request.UserRequest;
import dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BaseUserService implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public void createUser(UserRequest request) {
        repository.save(mapper.toEntity(request));
    }

    @Override
    public UserResponse updateUser(UUID userId, UserRequest request) {
        Optional<UserEntity> userEntityOptional = repository.findById(userId);
        if (userEntityOptional.isPresent()){
            UserEntity userEntity = mapper.toEntity(request);
            userEntity.setId(userId);
            userEntity.setChats(userEntityOptional.get().getChats());
            return mapper.toResponse(repository.save(userEntity));
        }
        throw new UserNotFoundException(userId);
    }

    @Override
    public void deleteUserById(UUID userId) {
        repository.deleteById(userId);
    }

    @Override
    public UserResponse getUserById(UUID userId) {
        return repository.findById(userId)
                .map(mapper::toResponse)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public List<UserResponse> getUsers() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public UserEntity getUserEntity(UUID userId) {
        Optional<UserEntity> userEntityOptional = repository.findById(userId);
        if (userEntityOptional.isPresent()){
            return userEntityOptional.get();
        }
        throw new UserNotFoundException(userId);
    }

    @Override
    public List<UserResponse> getUsersByChatId(UUID chatID) {
        return repository.getUsersByChats_Id(chatID).stream().map(mapper::toResponse).toList();
    }

    @Override
    public UserResponse getUserByPostId(UUID postId) {
        return mapper.toResponse(repository.getUserByPosts_Id(postId));
    }

    @Override
    public void saveUser(UserEntity entity) {
        repository.save(entity);
    }

}
