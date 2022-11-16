package com.sb.beFriendWith.service.posts;

import com.sb.beFriendWith.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@SpringBootTest(classes = {Application.class})
public class PostsServiceTest {
    @Autowired
    PostsService sut;

    @Test
    public void 테스트_파생쿼리_직접쿼리() {
        sut.findAllDesc();
    }

}