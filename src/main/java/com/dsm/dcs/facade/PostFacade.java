package com.dsm.dcs.facade;

import com.dsm.dcs.entity.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostFacade {

    private final PostRepository postRepository;



}
