package com.dsm.dcs.service.post;

import com.dsm.dcs.dto.request.PostRequest;
import com.dsm.dcs.dto.response.PostIdResponse;
import com.dsm.dcs.entity.post.Post;
import com.dsm.dcs.entity.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

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


}
