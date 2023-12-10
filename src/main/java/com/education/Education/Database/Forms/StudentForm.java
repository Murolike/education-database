package com.education.Education.Database.Forms;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Schema(description = "Форма запроса для создания/редактирования студента")
public class StudentForm {

    @Length(max = 255)
    @NotBlank
    @Schema(description = "Имя")
    private String firstName;

    @Length(max = 255)
    @Schema(description = "Фамилия")
    private String lastName;

    @Schema(description = "Отчество")
    private String middleName;
}
