package com.education.Education.Database.Services;

import com.education.Education.Database.Forms.UserForm;
import com.education.Education.Database.Models.User;
import com.education.Education.Database.Repositories.UserRepository;
import com.education.Education.Database.Responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Iterable<UserResponse> findAll() {
        Iterable<User> cursor = this.repository.findAll();
        ArrayList<UserResponse> models = new ArrayList<>();

        for (User user : cursor) {
            UserResponse model = UserResponse.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .middleName(user.getMiddleName())
                    .birthDate(user.getBirthDate())
                    .phoneNumber(user.getPhoneNumber())
                    .createdAt(user.getCreatedAt())
                    .updatedAt(user.getUpdatedAt())
                    .build();

            models.add(model);
        }

        return models;
    }

    public User findById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    public User create(UserForm userForm) {
        User user = new User();
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setMiddleName(userForm.getMiddleName());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setBirthDate(userForm.getBirthDate());
        user.setCreatedAt(LocalDateTime.now());

        return this.repository.save(user);
    }

    public UserResponse getSingleResponse(User user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .middleName(user.getMiddleName())
                .phoneNumber(user.getPhoneNumber())
                .birthDate(user.getBirthDate())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public User update(User user, UserForm userForm) {
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setMiddleName(userForm.getMiddleName());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setBirthDate(userForm.getBirthDate());
        user.setUpdatedAt(LocalDateTime.now());


        return this.repository.save(user);
    }

    public void delete(User user) {
        this.repository.delete(user);
    }
}
