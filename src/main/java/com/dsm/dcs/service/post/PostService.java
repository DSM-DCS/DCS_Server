package com.dsm.dcs.service.post;

import com.dsm.dcs.dto.request.PostRequest;
import com.dsm.dcs.dto.response.PostIdResponse;
import com.dsm.dcs.dto.response.PostListResponse;
import com.dsm.dcs.dto.response.PostResponse;
import com.dsm.dcs.entity.post.Post;
import com.dsm.dcs.entity.post.PostRepository;
import com.dsm.dcs.exception.ForbiddenException;
import com.dsm.dcs.facade.PostFacade;
import com.dsm.dcs.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

    private final PostFacade postFacade;
    private final UserFacade userFacade;
    private final PostRepository postRepository;

    public PostIdResponse savePost(PostRequest request) {
        userFacade.checkRoleAdmin();
        return new PostIdResponse(
                postRepository.save(
                        Post.builder()
                                .title(request.getTitle())
                                .content(request.getContent())
                                .build()
                ).getId()
        );
    }

    public PostIdResponse updatePost(Long id, PostRequest request) {
        userFacade.checkRoleAdmin();
        Post post = postFacade.findById(id);
        return new PostIdResponse(postRepository.save(
                post.update(request.getTitle(), request.getContent())).getId());
    }
    public void deletePost(Long postId) {
        userFacade.checkRoleAdmin();
        postRepository.delete(postFacade.findById(postId));
    }

    public PostListResponse getPostList(Pageable page) {
        if(!userFacade.isAdmin() && !userFacade.isUser()) {
            throw ForbiddenException.EXCEPTION;
        }
        return postFacade.getPostList(page);
    }

    public PostResponse getPost(Long postId) {
        if(!userFacade.isAdmin() && !userFacade.isUser()) {
            throw ForbiddenException.EXCEPTION;
        }
        Post post = postFacade.findById(postId);
        return PostResponse.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .createdDate(post.getCreatedDate())
                .build();
    }

}
