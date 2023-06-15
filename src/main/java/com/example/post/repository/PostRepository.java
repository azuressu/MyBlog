package com.example.post.repository;

import com.example.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 모든 메모들을 수정한 시간을 기준으로 정렬
    List<Post> findAllByOrderByCreateTimeDesc();

}
