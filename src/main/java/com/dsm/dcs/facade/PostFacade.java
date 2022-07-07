package com.dsm.dcs.facade;

import com.dsm.dcs.dto.response.PostListResponse;
import com.dsm.dcs.entity.post.Post;
import com.dsm.dcs.entity.post.PostRepository;
import com.dsm.dcs.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PostFacade {

    private final PostRepository postRepository;

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
    }

    public PostListResponse getPostList(Pageable page) {
        return new PostListResponse(postRepository.findAllByOrderByCreatedDateDesc(page).stream()
                .map(this::getPost).collect(Collectors.toList()));
    }


    private PostListResponse.PostResponse getPost(Post post) {
        return PostListResponse.PostResponse.builder()
                .title(post.getTitle())
                .createdDate(post.getCreatedDate())
                .build();
    }

}
