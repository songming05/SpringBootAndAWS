package com.sb.beFriendWith.service.posts;

import com.sb.beFriendWith.domain.posts.PostRepository;
import com.sb.beFriendWith.domain.posts.Posts;
import com.sb.beFriendWith.web.dto.PostsResponseDto;
import com.sb.beFriendWith.web.dto.PostsSaveRequestDto;
import com.sb.beFriendWith.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostRepository postRepository;

    public Long save(PostsSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id)
        );
        //entity 영속성 context
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postRepository.findById(id).orElseThrow(
                ()  -> new IllegalArgumentException("해당 게시글이 없습니다. id = " +id)
        );

        return new PostsResponseDto(entity);
    }
}
