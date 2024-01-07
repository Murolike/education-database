package com.education.Education.Database.Forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Форма запроса для создания/редактирования студента")
public class UserForm {

    @NotBlank
    @Length(min = 1, max = 255)
    @Schema(description = "Имя")
    private String firstName;

    @NotBlank
    @Length(min = 1, max = 255)
    @Schema(description = "Фамилия")
    private String lastName;

    @Length(max = 255)
    @Schema(description = "Отчество")
    private String middleName;

    @Length(min = 10, max = 10)
    @Schema(description = "Номер телефона")
    private String phoneNumber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Дата рождения")
    private LocalDate birthDate;
}
