package com.education.Education.Database.Controllers;

import com.education.Education.Database.Forms.UserForm;
import com.education.Education.Database.Models.User;
import com.education.Education.Database.Responses.UserResponse;
import com.education.Education.Database.Services.UserService;
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
@RequestMapping("/user")
@Tag(name = "User", description = "Управление пользователями")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Поиск всех пользователей", tags = {"User"})
    @ApiResponses({@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json")}),})
    public Iterable<UserResponse> index() {
        return this.userService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить пользователя по идентификатору", tags = {"User"})
    public UserResponse get(@PathVariable("id") Long id) {
        User user = this.userService.findById(id);

        return this.userService.getSingleResponse(user);
    }

    @PostMapping
    @Operation(summary = "Добавление пользователя", tags = {"User"})
    public UserResponse create(@Valid @RequestBody UserForm userForm) {
        User user = this.userService.create(userForm);

        return this.userService.getSingleResponse(user);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование пользователя", tags = {"User"})
    public UserResponse update(@PathVariable("id") Long id, @Valid @RequestBody UserForm userForm) {
        User user = this.userService.findById(id);
        user = this.userService.update(user, userForm);

        return this.userService.getSingleResponse(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пользователя", tags = {"User"})
    @ApiResponses({@ApiResponse(responseCode = "500", description = "Проблемы при выполнении ПО"),})
    public void delete(@PathVariable("id") Long id) {
        User user = this.userService.findById(id);
        this.userService.delete(user);
    }
}
