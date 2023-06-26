package com.example.post.service;

import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;
import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import com.example.post.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto createPost(PostRequestDto requestDto, UserDetailsImpl userDetails) {
        Post post = new Post();
        post.setUser(userDetails.getUser());
        post.setTitle(requestDto.getTitle());
        post.setContents(requestDto.getContents());

        // DB에 저장
        Post savePost = postRepository.save(post);
        PostResponseDto postResponseDto = new PostResponseDto(savePost);
        return postResponseDto;
    }

    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreateTimeDesc().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getOnePost(Long id) {
        Post post = findPost(id);

        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = findPost(id);


        post.update(requestDto);
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;

    }

    public String deletePost(Long id, PostRequestDto requestDto) {
        Post post = findPost(id);

        postRepository.delete(post);
        return "{\"success\":\"true\"}";

    }

    // 해당 포스트를 찾아서 반환
    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다."));
    }

}
