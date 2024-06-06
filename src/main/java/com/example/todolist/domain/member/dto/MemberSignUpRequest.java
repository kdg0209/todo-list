package com.example.todolist.domain.member.dto;

import jakarta.validation.constraints.NotBlank;

public record MemberSignUpRequest(@NotBlank String memberId, @NotBlank String password, @NotBlank String nickName) {
}
