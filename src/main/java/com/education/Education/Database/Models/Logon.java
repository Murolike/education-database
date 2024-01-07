package com.education.Education.Database.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "logon")
@Getter
@Setter
public class Logon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "log_in", nullable = false)
    private LocalDateTime logIn;

    @Column(name = "log_out")
    private LocalDateTime logOut;
}
