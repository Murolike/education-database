package com.education.Education.Database.Responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Schema(description = "Форма ответа для пользователя")
public class LogonResponse {

    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Пользователь")
    private UserResponse user;

    @Schema(description = "Токен")
    private String token;

    @Schema(description = "Дата аутентификации")
    private LocalDateTime logIn;

    @Schema(description = "Дата де-аутентификации")
    private LocalDateTime logOut;
}
