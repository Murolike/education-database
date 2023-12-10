package com.education.Education.Database.Controllers;

import com.education.Education.Database.Forms.StudentForm;
import com.education.Education.Database.Models.Student;
import com.education.Education.Database.Responses.StudentResponse;
import com.education.Education.Database.Services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@Tag(name = "Student", description = "Управление студентами")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    @Operation(summary = "Поиск всех студентов", tags = {"Student"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = StudentResponse.class), mediaType = "application/json")}),
    })
    public Iterable<StudentResponse> index() {
        return this.studentService.findAll();
    }

    @PostMapping
    @Operation(summary = "Добавление студента", tags = {"Student"})
    public StudentResponse create(@Valid @RequestBody StudentForm studentForm) {
        Student student = this.studentService.create(studentForm);

        return this.studentService.getSingleResponse(student);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование студента", tags = {"Student"})
    public StudentResponse update(@PathVariable("id") Long id, @Valid @RequestBody StudentForm studentForm) {
        Student student = this.studentService.findById(id);
        student = this.studentService.update(student, studentForm);

        return this.studentService.getSingleResponse(student);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление студента", tags = {"Student"})
    public void delete(@PathVariable("id") Long id) {
        Student student = this.studentService.findById(id);
        this.studentService.delete(student);
    }
}
