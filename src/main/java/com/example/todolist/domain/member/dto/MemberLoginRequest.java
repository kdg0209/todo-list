package com.example.todolist.domain.member.dto;

import jakarta.validation.constraints.NotBlank;

public record MemberLoginRequest(@NotBlank String memberId, @NotBlank String password) {
}
