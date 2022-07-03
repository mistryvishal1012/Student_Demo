package com.example.student.Controller;

import com.example.student.Model.AppUser.AppUser;
import com.example.student.Model.AppUser.AppUserRole;
import com.example.student.Model.ChanegRole;
import com.example.student.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("api/v1/admin/changeRole")
public class AdminController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("{id}")
    @PreAuthorize("hasAnyAuthority('role:change')")
    public ResponseEntity<String> changeRole(@PathVariable long id, @RequestBody ChanegRole chanegRole){
        System.out.println(id);
        try{
            AppUser appUser = registrationService.findByUserID(chanegRole.getApp_user_id());
            appUser.setAppUserRole(AppUserRole.valueOf(chanegRole.getRole_name()));
            registrationService.updateAppUser(appUser);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

}
