package com.dsm.dcs.controller;

import com.dsm.dcs.dto.request.PostRequest;
import com.dsm.dcs.dto.response.PostIdResponse;
import com.dsm.dcs.dto.response.PostListResponse;
import com.dsm.dcs.dto.response.PostResponse;
import com.dsm.dcs.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostIdResponse savePost(@Valid @RequestBody PostRequest request) {
        return postService.savePost(request);
    }

    @PatchMapping("/{id}")
    public PostIdResponse updatePost(@PathVariable("id") Long postId, @Valid @RequestBody PostRequest request) {
        return postService.updatePost(postId, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id") Long postId) {
        postService.deletePost(postId);
    }

    @GetMapping
    public PostListResponse getPostList(Pageable page) {
        return postService.getPostList(page);
    }

    @GetMapping("/{id}")
    public PostResponse getPost(@PathVariable("id") Long postId) {
        return postService.getPost(postId);
    }
    
}
