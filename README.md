![header](https://capsule-render.vercel.app/api?type=waving&color=auto&height=200&section=header&text=블로그백엔드서버&fontSize=50)

## 📝 블로그 백엔드 서버 만들기
✍️ 개인과제 ✍️


## 📍 프로그램 요구사항
1. 전체 게시글 목록을 조회하는 API
  - 제목, 작성자명, 작성 내용, 작성 날짜 조회
  - 작성 날짜를 기준으로 내림차순으로 정렬
2. 게시글을 작성하는 API
  - 제목, 작성자명, 비밀번호, 작성 내용 저장
  - 저장된 게시글을 Client로 반환
3. 선택한 게시글을 조회하는 API
  - 선택한 게시글의 제목, 작성자명, 작성 날짜, 작성 내용 조회
  - 검색 기능이 아닌, 간단한 게시글 조회로 구현
4. 선택한 게시글을 수정하는 API
  - 수정 요청 시, 수정할 데이터와 비밀번호를 같이 보내 서버에서 비밀번호 일치 여부를 확인
  - 제목, 작성자명, 작성 내용을 수정하고 수정된 게시글을 Client로 반환
5. 선택한 게시글을 삭제하는 API
  - 삭제 요청 시, 비밀번호를 같이 보내 서버에서 비밀번호 일치 여부를 확인
  - 선택한 게시글을 작세하고 Client로 성공했다는 표시 반환
  - 
### ❗ 프로그램 설계 시 주의사항
1. Entity를 그대로 반환하는 것이 아닌, DTO에 담아서 반환하기
2. JSON을 반환하는 API 형태로 진행하기
3. 서버가 반환하는 결과값을 Postman으로 확인하기

## 사용한 Tool
<img src="https://img.shields.io/badge/git-F05032?style=flat&logo=git&logoColor=white"/>
<img src="https://img.shields.io/badge/github-181717?style=flat&logo=github&logoColor=white"/>
<img src="https://img.shields.io/badge/java-007396?style=flat&logo=java&logoColor=white"/>
<img src="https://img.shields.io/badge/intellijidea-000000?style=flat&logo=intellijidea&logoColor=white"/>


## 🪶 API 명세서
<p align="center">
  <img src="https://github.com/azuressu/MyBlog/assets/74248726/4f754778-50b0-4e4b-a76f-a9ea1a1a9d0e">
</p>
<p align="center">
  <img src="https://github.com/azuressu/MyBlog/assets/74248726/1f0ffc67-40f5-47bc-b0f1-d1ff1e1c82f8">
</p>


## 📜 테이블 구성
<p align="center">
  <img src="https://github.com/azuressu/MyBlog/assets/74248726/7207f44b-7eb6-4cb8-b82f-6eda9dc8987d">
</p>


## 🛞 Use Case 다이어그램
(정답은 아닌 것 같지만 그려보았다)
<p align="center">
  <img src="https://github.com/azuressu/MyBlog/assets/74248726/e3050766-1576-4548-a0c5-555ccac57ef9">
</p>


## 🗂️ 파일 구성
1. controller 패키지 – PostController.java
2. dto 패키지 – PostRequestDto.java, PostResponseDto.java
3. entity 패키지 – Post.java, Timestapmed.java
4. repository 패키지 – PostRepository,java
5. service 패키지 – PostService.java


## 🪄 코드 구성
1. PostController.java
```java
@RestController
@RequestMapping("/api")
public class PostController {
  private final PostService postService;
  public PostController(PostService postService) {
    this.postService = postService;
  }

  // 게시글 등록
  @PostMapping("/posts")
  public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
    return postService.createPost(requestDto);
  }
  // 전체 게시글 조회
  @GetMapping("/posts")
  public List<PostResponseDto> getPosts() {
    return postService.getPosts();
  }
  // 선택 게시글 조회
  @GetMapping("/posts/{id}")
  public PostResponseDto getOnePost(@PathVariable Long id) {
    return postService.getOnePost(id);
  }
  // 게시글 수정
  @PutMapping("/posts/{id}")
  public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
    return postService.updatePost(id, requestDto);
  }
  // 게시글 삭제
  @DeleteMapping("/posts/{id}")
  public String deletePost(@PathVariable Long id, @RequestParam String password) {
    return postService.deletePost(id, password);
  }
}
```
컨트롤러 역할을 하는 클래스이다.
기능별로 url과 메소드를 매핑하여 메소드를 작성했다.

2. PostRequestDto.java
```java
@Getter
public class PostRequestDto {
  private String title;     // 게시글 제목
  private String username;  // 작성자 이름
  private String contents;  // 게시글 내용
  private String password;  // 비밀번호
}
```
사용자로부터 입력받을 값을 저장하기 위한 Dto 클래스이다.
게시글의 제목, 작성자 이름, 게시글 내용, 비밀번호 필드를 private으로 선언하고, 그에 접근 가능한 Getter를 만들었다.

3. PostResponseDto.java
```java
@Getter
public class PostResponseDto {
  private Long id;                  // id
  private String username;          // 작성자 이름
  private String contents;          // 게시글 내용
  private String title;             // 게시글 제목
  private LocalDateTime createTime; // 작성일
  private LocalDateTime modifyTime; // 수정일
  
  public PostResponseDto(Post post) {
    this.id = post.getId();
    this.title = post.getTitle();
    this.username = post.getUsername();
    this.contents = post.getContents();
    this.createTime = post.getCreateTime();
    this.modifyTime = post.getModifyTime();
    }
}
```
사용자에게 보여줄 정보를 저장하기 위한 Dto클래스이다.
id와 게시글 제목, 작성자 이름, 게시글 내용, 게시글 작성일, 게시글 수정일 필드를 private으로 선언하고, 그에 접근 가능한 Getter 메소드를 만들었다.

4. Post.java
```java
@Entity
@Getter
@Setter
@Table(name = "post")
@NoArgsConstructor
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // auto_increase
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "contents", nullable = false)
    private String contents;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }

    public void update(PostRequestDto requestDto) {
        // 제목, 작성자명, 작성 내용을 수정
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}
```
테이블에 넣어줄 엔티티 Post 클래스이다. (Timestamped 추상 클래스를 상속받는다)
PK로 사용될 id와, 게시글 제목, 게시글 내용, 작성자명, 비밀번호를 필드를 private으로 선언하고, 각 필드에 접근 가능한 Getter와 설정 가능한 Setter 메소드를 만들었다.
기본생성자와 파라미터 값을 받는 생성자를 만들고, 게시글 수정을 위한 메소드도 하나 만들었다. 

5. Timestamped.java
```java
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Timestamped {

    @CreatedDate
    @Column(updatable = false)        // 이후에는 값이 수정되지 않음
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createTime;

    @LastModifiedDate
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifyTime;

}
```
작성일과 수정일 시간 데이터를 저장할 추상 클래스이다.
LocalDateTime 형태의 작성일과 수정일 필드를 만들고 접근 가능한 Getter 메소드를 만들었다.

6. PostRepository.java
```java
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreateTimeDesc();
}
```
Repository 역할의 PostRepository 인터페이스이다.
JpaRepository를 상속받으며 Post 타입과 Long 타입을 갖고, 게시글들을 시간에 따라 내림차순 정렬하여 보여주기 위해 메소드를 만들었다.

7. PostService.java
```java
@Service
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
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

        // 비밀번호 확인 후
        if (post.getPassword().equals(requestDto.getPassword())) {
            post.update(requestDto);
            PostResponseDto postResponseDto = new PostResponseDto(post);
            return postResponseDto;
        } else {
            return null;
        }
    }

    // RequestBody 방식
    public String deletePost(Long id, PostRequestDto requestDto) {
        Post post = findPost(id);

        // 비밀번호 확인 후
        if (post.getPassword().equals(requestDto.getPassword())) {
            postRepository.delete(post);
            return "{\"success\":\"true\"}";
        } else {
            return "{\"success\":\"false\"}";
        }
    }

    // 해당 포스트를 찾아서 반환
    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다."));
    }

}
```
Service 기능을 수행할 수 있는 클래스이다.
Repository 필드를 선언하고 생성자에 넣어준다.
게시글 작성, 전체 게시글 조회, 선택 게시글 조회, 게시글 수정, 게시글 삭제 기능을 수행할 수 있는 메소드들을 만들고, id를 입력받았을 때, 해당 게시글을 찾아서 반환해주는 메소드를 만들었다.

![Footer](https://capsule-render.vercel.app/api?type=waving&color=auto&height=200&section=footer)
