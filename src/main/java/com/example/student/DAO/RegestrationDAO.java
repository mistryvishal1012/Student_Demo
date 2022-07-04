package com.example.student.DAO;

import com.example.student.Model.AppUser.AppUser;

public interface RegestrationDAO {

    AppUser findByUsername(String username);

    AppUser saveAndFlush(AppUser appUser);
}
