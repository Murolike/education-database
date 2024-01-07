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

    @Length(max = 255)
    @NotBlank
    @Schema(description = "Имя")
    private String firstName;

    @Length(max = 255)
    @Schema(description = "Фамилия")
    private String lastName;

    @Schema(description = "Отчество")
    private String middleName;

    @Schema(description = "Номер телефона")
    @Length(min = 10, max = 10)
    private String phoneNumber;

    @Schema(description = "Дата рождения")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
}
