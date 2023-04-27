package com.technokratos.service;

import com.technokratos.exception.PostNotFoundException;
import com.technokratos.model.PostEntity;
import com.technokratos.model.UserEntity;
import com.technokratos.repository.PostRepository;
import com.technokratos.util.mapper.PostMapper;
import dto.request.PostRequest;
import dto.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasePostService implements PostService{

    private final PostRepository repository;
    private final PostMapper mapper;
    private final UserService userService;

    @Override
    public void createPost(UUID userId, PostRequest request) {
        PostEntity postEntity = mapper.toEntity(request);
        UserEntity entity = userService.getUserEntity(userId);
        postEntity.setUser(entity);
        userService.saveUser(entity);
        repository.save(postEntity);
    }

    @Override
    public PostResponse updatePost(UUID postId, PostRequest request) {
        Optional<PostEntity> postEntityOptional = repository.findById(postId);
        if (postEntityOptional.isPresent()){
            PostEntity postEntity = mapper.toEntity(request);
            postEntity.setId(postId);
            return mapper.toResponse(repository.save(postEntity));
        }
        throw new PostNotFoundException(postId);
    }

    @Override
    public void deletePostById(UUID postId) {
        repository.deleteById(postId);
    }

    @Override
    public PostResponse getPostById(UUID postId) {
        return repository.findById(postId)
                .map(mapper::toResponse)
                .orElseThrow(() -> new PostNotFoundException(postId));
    }

    @Override
    public List<PostResponse> getPosts() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<PostResponse> getPostsByUserId(UUID userId) {
        return repository.getPostsByUser_Id(userId).stream().map(mapper::toResponse).toList();
    }
}
