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

