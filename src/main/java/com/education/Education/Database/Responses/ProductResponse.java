package com.education.Education.Database.Responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Schema(description = "Форма ответа для продукта")
public class ProductResponse {

    @Schema(description = "Идентификатор")
    private Long id;

    @Schema(description = "Имя продукта")
    private String name;

    @Schema(description = "Цена")
    private Float price;

    @Schema(description = "Дата создания")
    private LocalDateTime createdAt;

    @Schema(description = "Дата обновления")
    private LocalDateTime updatedAt;
}
