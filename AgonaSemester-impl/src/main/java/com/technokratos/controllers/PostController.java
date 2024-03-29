package com.technokratos.controllers;

import api.PostApi;
import com.technokratos.service.PostService;
import dto.request.PostRequest;
import dto.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostController implements PostApi {

    private final PostService service;

    @Override
    public void createPost(UUID userId, PostRequest request) {
        service.createPost(userId, request);
    }

    @Override
    public PostResponse updatePost(UUID postId, PostRequest request) {
        return service.updatePost(postId, request);
    }

    @Override
    public void deletePostById(UUID postId) {
        service.deletePostById(postId);
    }

    @Override
    public PostResponse getPostById(UUID postId) {
        return service.getPostById(postId);
    }

    @Override
    public List<PostResponse> getPosts() {
        return service.getPosts();
    }

    @Override
    public List<PostResponse> getPostsByUserId(UUID userId) {
        return service.getPostsByUserId(userId);
    }
}
