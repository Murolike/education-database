package com.education.Education.Database.Responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Schema(description = "Форма ответа для заказа")
public class OrderResponse {

    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Пользователь")
    private UserResponse user;

    @Schema(description = "Продукт")
    private ProductResponse product;

    @Schema(description = "Статус")
    private OrderStatusResponse orderStatus;

    @Schema(description = "Дата создание")
    private LocalDateTime createdAt;

    @Schema(description = "Дата обновления")
    private LocalDateTime updatedAt;
}
