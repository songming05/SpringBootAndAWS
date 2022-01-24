package com.sb.beFriendWith.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp() {
        postsRepository.deleteAll(); //다중 테스트 실행 시 삭제하지 않으면 H2 DB에 데이터가 남을 수 있다.
    }

    @Test
    public void 게시글저장_불러오기() {
        String title = "글제목 테스트";
        String content = "내용 테스트";

        Posts posts = Posts.builder()
                .title(title)
                .content(content)
                .author("songming")
                .build();
        postsRepository.save(posts); //H2 DB가 사용될 것

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts onePosts = postsList.get(0);
        assertThat(onePosts.getTitle().contentEquals(title));
        assertThat(onePosts.getContent().contentEquals(content));
        assertThat(onePosts.getId().toString().length() > 0); //auto increment
    }

    @Test
    public void MySQL문법로그로_변경(){
        //org.hibernate.dialect.MySQL5Dialect
        //org.hibernate.dialect.MySQL5InnoDBDialect
    }

}
