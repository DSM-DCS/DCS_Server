package com.dsm.dcs.facade;

import com.dsm.dcs.entity.post.Post;
import com.dsm.dcs.entity.post.PostRepository;
import com.dsm.dcs.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostFacade {

    private final PostRepository postRepository;

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
    }

}
