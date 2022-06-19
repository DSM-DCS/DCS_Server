package com.dsm.dcs.service.post;

import com.dsm.dcs.dto.request.PostRequest;
import com.dsm.dcs.dto.response.PostIdResponse;
import com.dsm.dcs.dto.response.PostListResponse;
import com.dsm.dcs.entity.post.Post;
import com.dsm.dcs.entity.post.PostRepository;
import com.dsm.dcs.facade.PostFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

    private final PostFacade postFacade;
    private final PostRepository postRepository;

    public PostIdResponse savePost(PostRequest request) {
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
        Post post = postFacade.findById(id);
        return new PostIdResponse(postRepository.save(
                post.update(request.getTitle(), request.getContent())).getId());
    }

    public PostListResponse getPostList(Pageable page) {
        return postFacade.getPostList(page);
    }

}
