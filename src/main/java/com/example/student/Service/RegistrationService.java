package com.example.student.Service;

import com.example.student.Exception.NotFoundException;
import com.example.student.Exception.RegistrationError;
import com.example.student.Model.AppUser.AppUser;
import com.example.student.Model.AppUser.AppUserRole;
import com.example.student.Model.RegistrationRequest;
import com.example.student.Repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class RegistrationService implements UserDetailsService{

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<AppUser> appUser = registrationRepository.findByUsername(s);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.get().getAppUserRole().getGrantedAuthorities().forEach(authority -> {
            authorities.add(authority);
        });
        return new org.springframework.security.core.userdetails.User(appUser.get().getUsername(),appUser.get().getPassword(),authorities);
    }


    public Boolean findByUsername(String username) {
        Optional<AppUser> appUser = registrationRepository.findByUsername(username);
        return appUser.isPresent() ? true : false;
    }

    public AppUser findByUserID(long id) throws NotFoundException {
        Optional<AppUser> appUser = registrationRepository.findById(id);
        if(!appUser.isPresent()){
            throw new NotFoundException("User Not Found");
        }
        return appUser.get();
    }

    @Transactional
    public String registerUser(RegistrationRequest registrationRequest) {
        String  username = registrationRequest.getUsername();
        String password = registrationRequest.getPassword();
        if(registrationRepository.findByUsername(username).isPresent()){
            throw new RegistrationError("User Already Exists");
        }

        if(password == null || password.length() < 6){
            throw new RegistrationError("Password Must Be 6 Charcter Or Long");
        }
        password = passwordEncoder.encode(registrationRequest.getPassword());
        AppUser appUser = new AppUser(
                registrationRequest.getUsername(),
                password,
                AppUserRole.STUDENT
        );

        System.out.println(appUser);
        registrationRepository.save(appUser);
        return "Registered !!";
    }

    public String loginUser(RegistrationRequest registrationRequest) {
        String  username = registrationRequest.getUsername();
        String password = registrationRequest.getPassword();
        Optional<AppUser> currentUser = registrationRepository.findByUsername(username);
        if(!currentUser.isPresent()){
            throw new RegistrationError("User Dose Not Exists");
        }

        if(!passwordEncoder.matches(password,currentUser.get().getPassword())){
            throw new RegistrationError("Password is Invalid");
        }

        return "Login!!";
    }

    public String updateAppUser(AppUser appUser){
        registrationRepository.saveAndFlush(appUser);
        return "Done";
    }
}
