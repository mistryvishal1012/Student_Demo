package com.example.student.Controller;


import com.example.student.Exception.RegistrationError;
import com.example.student.Model.AppUser.AppUser;
import com.example.student.Model.AppUser.AppUserRole;
import com.example.student.Model.ChanegRole;
import com.example.student.Model.RegistrationRequest;
import com.example.student.Service.RegistrationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("api/v1/auth")
public class RegistrationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RegistrationService registrationService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest registrationRequest){
        String  username = registrationRequest.getUsername();
        String password = registrationRequest.getPassword();
       try{
           if(registrationService.findByUsername(username)){
               throw new RegistrationError("User Already Exists");
           }

           if(password == null || password.length() < 6){
               throw new RegistrationError("Password Must Be 6 Charcter Or Long");
           }

           return new ResponseEntity<String>(registrationService.registerUser(registrationRequest),HttpStatus.OK);
       }catch (RegistrationError exception){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
       }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegistrationRequest registrationRequest){
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    registrationRequest.getUsername(), registrationRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jws = Jwts.builder().setSubject(authentication.getName()).signWith(SignatureAlgorithm.HS256,"securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecure").claim("Role",authentication.getAuthorities()).compact();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Bearer",jws);
            return ResponseEntity.ok().headers(httpHeaders).body("Login");
        }catch (Exception ex){
            switch (ex.getMessage()){
                case "Bad credentials":
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Password");
                case "No value present":
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Does Not Exists");
                default:
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
            }
        }
    }


}
