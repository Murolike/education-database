package com.education.Education.Database.Forms;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Форма создания заказа")
public class OrderForm {

    @NotNull
    @Schema(description = "Идентификатор пользователя")
    private Long userId;

    @NotNull
    @Schema(description = "Идентификатор продукта")
    private Long productId;
}
