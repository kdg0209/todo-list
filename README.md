# API 명세서

## 회원 관련

### 서비스 가입

- endpoint: http://localhost:8080/members/sign-up
- http-method: POST

```text
> 요청값
{
    "memberId": "user1",
    "password": "1234",
    "nickName": "KDG"    
}

> 응답값
{
    "id": "user1",
    "nickName": "KDG"
}
```

<br>

### 로그인

- endpoint: http://localhost:8080/members/login
- http-method: POST

```text
> 요청값
{
    "memberId": "user1",
    "password": "1234"  
}

> 응답값
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsInJvbGVzIjoiUk9MRV9VU0VSIiwiZXhwIjoxNzE3NjY1OTc0fQ.w7MMCTHmV47yEIoSBIfXqw8ROMb1PiEL2ShaFM-71YDHL5X9No0k3-EM9tAN4xGPOdMKWUnwupoh9Q5IYoLDHw"
}
```

<br>

### 탈퇴

- endpoint: http://localhost:8080/members/withdraw
- http-method: POST

```text
> 요청값
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsInJvbGVzIjoiUk9MRV9VU0VSIiwiZXhwIjoxNzE3NjY5MzM4fQ.WyS5sN9Zu-GewPH5OJ2K0HFMmuIAy1XOl6UgWhVv5ksrSiv-ZRLCaoi_MFSkW-nFC_vfMtzg-FabXP-JtR-JNw

> 응답값
{
    "isDeleted": true
}
```

<br>

## TODO LIST 관련

### todo 작성

- endpoint: http://localhost:8080/todos
- http-method: POST

```text
> 요청값
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsInJvbGVzIjoiUk9MRV9VU0VSIiwiZXhwIjoxNzE3NjY5MzM4fQ.WyS5sN9Zu-GewPH5OJ2K0HFMmuIAy1XOl6UgWhVv5ksrSiv-ZRLCaoi_MFSkW-nFC_vfMtzg-FabXP-JtR-JNw

{
    "title": "title",
    "contents": "contents",
    "status": "TODO" [TODO, IN_PROGRESS, DONE, PENDING]
}

> 응답값
{
    "todoId": 1
}
```

<br>

### 가장 최근의 TODO 1개 조회

- endpoint: http://localhost:8080/todos/latest
- http-method: GET

```text
> 요청값
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsInJvbGVzIjoiUk9MRV9VU0VSIiwiZXhwIjoxNzE3NjY5MzM4fQ.WyS5sN9Zu-GewPH5OJ2K0HFMmuIAy1XOl6UgWhVv5ksrSiv-ZRLCaoi_MFSkW-nFC_vfMtzg-FabXP-JtR-JNw

> 응답값: TODO가 있는 경우
{
    "id": 4,
    "title": "title 3",
    "contents": "contents 3",
    "status": "TODO",
    "createdDatetime": "2024-06-06T18:16:47.380367"
}

> 응답값: TODO가 없는 경우
{
    "id": null,
    "title": null,
    "contents": null,
    "status": null,
    "createdDatetime": null
}
```

<br>

### 전체 목록

- endpoint: http://localhost:8080/todos?page=0&size=10
- http-method: GET

```text
> 요청값
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsInJvbGVzIjoiUk9MRV9VU0VSIiwiZXhwIjoxNzE3NjY5MzM4fQ.WyS5sN9Zu-GewPH5OJ2K0HFMmuIAy1XOl6UgWhVv5ksrSiv-ZRLCaoi_MFSkW-nFC_vfMtzg-FabXP-JtR-JNw

> 응답값
{
    "todos": [
        {
            "id": 25,
            "title": "title 12",
            "contents": "123 12",
            "status": "대기",
            "createdDatetime": "2024-06-06T18:41:14.554422"
        },
        {
            "id": 24,
            "title": "title 12",
            "contents": "123 12",
            "status": "대기",
            "createdDatetime": "2024-06-06T18:41:14.031775"
        }
      ... 생략
    ]
}
```

<br>

### TODO 상태 변경

- endpoint: http://localhost:8080/todos/status
- http-method: PUT