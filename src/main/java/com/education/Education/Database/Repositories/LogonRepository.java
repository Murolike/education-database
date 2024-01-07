package com.education.Education.Database.Repositories;

import com.education.Education.Database.Models.Logon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogonRepository extends CrudRepository<Logon, Long> {
    Optional<Logon> findByToken(String token);
}
