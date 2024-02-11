package com.education.Education.Database.Responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "Форма ответа для статусов")
public class OrderStatusResponse {

    @Schema(description = "Идентификатор")
    public Long id;

    @Schema(description = "Заголовок")
    public String name;
}
