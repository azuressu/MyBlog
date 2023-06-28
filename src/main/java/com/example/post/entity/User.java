package com.example.post.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    // 중복 안됨
    @Id
    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[a-z0-9]*$")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Size(min = 8, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")  // post_id로 했더니 오류남
    private List<Post> postList = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String username, String password , UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void addPostList(Post post) {
        this.postList.add(post);
        post.setUser(this);
    }
}
