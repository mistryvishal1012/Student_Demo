package com.example.student.Repository;

import com.example.student.Model.AppUser.AppUser;
import com.example.student.Model.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<AppUser,Long> {

    Optional<AppUser> findByUsername(String username);
}
