package com.example.student.Repository;

import com.example.student.DAO.RegestrationDAO;
import com.example.student.Model.AppUser.AppUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RegistrationRepository implements RegestrationDAO {

    @Override
    public AppUser findByUsername(String username) {
        return null;
    }

    @Override
    public AppUser saveAndFlush(AppUser appUser) {
        return null;
    }

    public AppUser findById(long id) {
        return null;
    }

    public void save(AppUser appUser) {
        return;
    }
}
