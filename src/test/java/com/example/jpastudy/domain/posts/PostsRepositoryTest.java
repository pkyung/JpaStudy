package com.example.jpastudy.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "게시글제목";
        String content = "게시글내용";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("dmsrud2002@naver.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> posts = postsRepository.findAll();

        //then
        Posts post = posts.get(0);

        System.out.println(">>>>>>>> createDate="+post.getCreatedDate()+", modifiedDate="+post.getModifiedDate());

        Assertions.assertThat(post.getCreatedDate()).isAfter(now);
        Assertions.assertThat(post.getModifiedDate()).isAfter(now);

    }

}
