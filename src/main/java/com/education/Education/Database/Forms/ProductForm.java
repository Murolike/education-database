package com.education.Education.Database.Forms;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Schema(description = "Форма создания продукта")
public class ProductForm {

    @NotBlank
    @Length(min = 1, max = 255)
    @Schema(description = "Имя продукта")
    private String name;

    @NotNull
    @Schema(description = "Цена")
    private Float price;
}
