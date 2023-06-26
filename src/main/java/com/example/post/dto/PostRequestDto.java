package com.example.post.dto;

import com.example.post.entity.User;
import lombok.Getter;

@Getter
public class PostRequestDto {

    private String title;
    private String contents;

}
