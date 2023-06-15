package com.example.post.controller;

import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;
import com.example.post.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // ResponseBody는 붙이면 안됨 !
@RequestMapping("/api")
public class PostController {

    // 제어의 흐름 : PostController → PostService → PostRepository
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/posts/{id}")
    public PostResponseDto getOnePost(@PathVariable Long id) {
        return postService.getOnePost(id);
    }

    @PutMapping("/posts/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    // 비밀번호 처리 어떻게 할 건지 생각해보기
    // RequestParam은 key와 value를 url에 전달함
    // RequestBody 방식
    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.deletePost(id, requestDto);
    }

    // Param 방식
//    @DeleteMapping("/posts/{id}")
//    public String deletePost(@PathVariable Long id, @RequestParam String password){
//        return postService.deletePost(id, password);
//    }
}
