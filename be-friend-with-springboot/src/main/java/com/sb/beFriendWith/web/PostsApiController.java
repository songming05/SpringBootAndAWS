package com.sb.beFriendWith.web;

import com.sb.beFriendWith.service.posts.PostsService;
import com.sb.beFriendWith.web.dto.PostsSaveRequestDto;
import com.sb.beFriendWith.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PostMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @PostMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        return postsService.delete(id);
    }
}
