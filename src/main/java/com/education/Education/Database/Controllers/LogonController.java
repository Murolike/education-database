package com.education.Education.Database.Controllers;

import com.education.Education.Database.Models.Logon;
import com.education.Education.Database.Models.User;
import com.education.Education.Database.Responses.LogonResponse;
import com.education.Education.Database.Services.LogonService;
import com.education.Education.Database.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logon")
@Tag(name = "Logon", description = "Управление аутентификация")
public class LogonController {

    @Autowired
    private LogonService logonService;
    @Autowired
    private UserService userService;

    @PostMapping("/in")
    @Operation(summary = "Вход", tags = {"Logon"})
    @ApiResponses({@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = LogonResponse.class), mediaType = "application/json")}),})
    public LogonResponse in(@RequestParam Long userId) {
        User user = userService.findById(userId);

        return this.logonService.logIn(user);
    }

    @PostMapping("/out")
    @Operation(summary = "Выход", tags = {"Logon"})
    @ApiResponses({@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = LogonResponse.class), mediaType = "application/json")}),})
    public LogonResponse out(@RequestParam String token) {
        Logon logon = logonService.findByToken(token);

        return this.logonService.logOut(logon);
    }
}
