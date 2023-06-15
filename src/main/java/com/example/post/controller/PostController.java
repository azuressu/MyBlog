package com.example.post.controller;

import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;
import com.example.post.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // ResponseBody는 붙이면 안됨 !
@RequestMapping("/api")
public class PostController {

    // 제어의 흐름 : MemoController → MemoService → MemoRepository
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
    // https://chanho0912.tistory.com/20
    // https://scoring.tistory.com/entry/%EC%8A%A4%ED%94%84%EB%A7%81-REST-Controller%EC%97%90%EC%84%9C-%EC%82%AC%EC%9A%A9%EB%90%98%EB%8A%94-%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98
    /*@DeleteMapping("/posts/{id}")
    public Long deleteMemo(@PathVariable Long id, @RequestParam String password){
        return postService.deletePost(id, password);
    }*/
    @DeleteMapping("/posts/{id}")
    public String deleteMemo(@PathVariable Long id, @RequestParam String password){
        return postService.deletePost(id, password);
    }
}
