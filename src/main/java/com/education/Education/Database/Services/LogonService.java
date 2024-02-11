package com.education.Education.Database.Services;

import com.education.Education.Database.Models.Logon;
import com.education.Education.Database.Models.User;
import com.education.Education.Database.Repositories.LogonRepository;
import com.education.Education.Database.Responses.LogonResponse;
import com.education.Education.Database.Responses.UserResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogonService {

    @Autowired
    private LogonRepository repository;
    @Autowired
    private UserService userService;

    public LogonResponse logIn(User user) {
        Logon logon = this.create(user);

        return this.getSingleResponse(logon);
    }

    public LogonResponse logOut(Logon logon) {
        logon.setLogOut(LocalDateTime.now());
        repository.save(logon);

        return this.getSingleResponse(logon);
    }

    public Logon findByToken(String token) {
        return this.repository.findByToken(token).orElseThrow();
    }

    public Logon create(User user) {
        Logon model = new Logon();
        model.setUser(user);
        model.setToken(RandomStringUtils.randomAlphabetic(128));
        model.setLogIn(LocalDateTime.now());

        return repository.save(model);
    }

    public LogonResponse getSingleResponse(Logon logon) {
        User user = logon.getUser();
        UserResponse userResponse = this.userService.getSingleResponse(user);

        return LogonResponse.builder()
                .id(logon.getId())
                .token(logon.getToken())
                .logIn(logon.getLogIn())
                .logOut(logon.getLogOut())
                .user(userResponse)
                .build();
    }
}
