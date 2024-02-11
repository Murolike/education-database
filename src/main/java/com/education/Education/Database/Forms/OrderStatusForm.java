package com.education.Education.Database.Forms;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Форма смены статуса заказа")
public class OrderStatusForm {

    @NotNull
    @Schema(description = "Идентификатор статуса")
    private Long statusId;
}
