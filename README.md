![header](https://capsule-render.vercel.app/api?type=waving&color=auto&height=200&section=header&text=블로그백엔드서버&fontSize=50)
## 📝 블로그 백엔드 서버 만들기 Lv2
✍️ 개인과제 ✍️
## 📍 프로그램 요구사항
1. 회원 가입 API
  - username, password를 Client에서 전달받기
  - username은 최소 4자 이상 10자 이하이며 알파벳 소문자와 숫자로 구성
  - password는 최소 8자 이상 15자 이하이며 알파벳 대소문자와 숫자로 구성
  - DB에 중복된 username이 없다면 회원을 저장하고 Client로 성공했다는 메시지와 상태코드 반환
2. 로그인 API
  - username, password를 Client에서 전달받기
  - DB에서 username을 사용해 저장된 회원의 유무를 확인하고, 있다면 password 비교
  - 로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용해 토큰을 발급하고, 발급한 토큰을 Header에 추가한 후 성공했다는 메시지와 상태코드 Client에 반환
3. 전체 게시글 목록을 조회하는 API
  - 제목, 작성자명(username), 작성 내용, 작성 날짜 조회
  - 작성 날짜를 기준으로 내림차순으로 정렬
4. 게시글을 작성하는 API
  - 토큰을 검사해 유효한 토큰일 경우에만 작성 가능
  - 제목, 작성 내용 저장
  - 저장된 게시글을 Client로 반환 (username은 로그인 된 사용자)
5. 선택한 게시글을 조회하는 API
  - 선택한 게시글의 제목, 작성자명, 작성 날짜, 작성 내용 조회
  - 검색 기능이 아닌, 간단한 게시글 조회로 구현
6. 선택한 게시글을 수정하는 API
  - 토큰을 검사해 유효한 토큰이면서 해당 사용자가 작성한 게시글만 수정 가능
  - 제목, 작성 내용을 수정하고 수정된 게시글을 Client로 반환
7. 선택한 게시글을 삭제하는 API
  - 토큰을 검사해 유효한 토큰이면서 해당 사용자가 작성한 게시글만 삭제 가능
  - 선택한 게시글을 삭제하고 Client로 성공했다는 메시지와 상태코드 반환
## 사용한 Tool
<img src="https://img.shields.io/badge/git-F05032?style=flat&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=flat&logo=github&logoColor=white">
<img src ="https://img.shields.io/badge/Java-007396?&style=flat&logo=Java&logoColor=white">
<img src="https://img.shields.io/badge/intellijidea-000000?style=flat&logo=intellijidea&logoColor=white">
## 🪶 API 명세서
<img src="https://github.com/azuressu/MyBlog/assets/74248726/aded181c-a03e-422f-a343-06cbbf17a9cd"  width="800"/>
<img src="https://github.com/azuressu/MyBlog/assets/74248726/9596f8bc-18ef-4896-a795-358417855d34"  width="800"/>
<img src="https://github.com/azuressu/MyBlog/assets/74248726/7138c328-f7c8-476b-9dc1-d0e3e8d71024"  width="800"/>
## 📜 테이블 구성
<img src="https://github.com/azuressu/MyBlog/assets/74248726/e1d3f00b-6bd3-4394-a9d3-d204d0db172a"  width="800"/>
<img src="https://github.com/azuressu/MyBlog/assets/74248726/8f3c37d7-e954-45db-8803-89278d03d78a"  width="800"/>
## 📜 ERD 설계
<img src="https://github.com/azuressu/MyBlog/assets/74248726/06683415-edf4-4a32-84de-99d42be2164f"  width="800"/>
<img src="https://github.com/azuressu/MyBlog/assets/74248726/75d1f15d-37cb-4673-bc6c-a352687c9b72"  width="800"/>
## 🗂️ 파일 구성
1. controller 패키지 – PostController.java, UserController.java
2. dto 패키지 – PostRequestDto.java, PostResponseDto.java, SignupRequestDto.java, StatusResponseDto.java, LoginRequestDto.java 
3. entity 패키지 – Post.java, Timestapmed.java, User.java, UserRoleEnum.java
4. repository 패키지 – PostRepository,java, UserRepository.java
5. service 패키지 – PostService.java, UserService.java
6. jwt 패키지 – JwtUtil.java
7. security 패키지 – JwtAuthenticationFilter.java, JwtAuthorizationFilter.java, UserDetailsImpl.java, UserDetailsServiceImpl.java, WebSecurityConfig.java

![Footer](https://capsule-render.vercel.app/api?type=waving&color=auto&height=200&section=footer)
