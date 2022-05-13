package com.demo.oms.controller;


import com.demo.oms.entity.AppUser;
import com.demo.oms.dto.UserLoginDTO;
import com.demo.oms.dto.UserTokenDTO;
import com.demo.oms.entity.Enum.AppUserRole;
import com.demo.oms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AppUserController {

    @Autowired
          private UserService appUserService;



    public AppUserController(UserService appUserService) {

        this.appUserService = appUserService;

    }
    @PostMapping("/addAdmin")
    public ResponseEntity<?> saveUser(@RequestBody AppUser appUser) {
        appUser.setAppUserRole(AppUserRole.USER);
        appUserService.addAdmin(appUser);

        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<AppUser>> getAllUsers() {

        List<AppUser> userList = appUserService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/GetEnable/{id}")
    public ResponseEntity<Boolean> getStatut( @PathVariable String id) {

       boolean enabled = appUserService.GetEnabledByuser(id);
        return new ResponseEntity<>(enabled, HttpStatus.OK);
    }

    @GetMapping("/GetMdp/{id}")
    public ResponseEntity<String> getOldPassword(@PathVariable String id) {

        String enabled = appUserService.GetOldPassword(id);
        return new ResponseEntity<>(enabled, HttpStatus.OK);
    }

    @PostMapping("/EnableAdmin/{id}")
    public boolean EnableAdmin(@PathVariable String id) {

       return  appUserService.EnableUser(id);}
//        return new ResponseEntity<>(HttpStatus.OK);}

    @PostMapping("/DisableAdmin/{id}")
    public boolean disableAdmin(@PathVariable String id) {
            return appUserService.disableUser(id);}

    @PostMapping("/sendEmail")
    public ResponseEntity<Boolean> sendEmail(@RequestBody String email) {
      Boolean mail = appUserService.sendEmail(email);

        return new ResponseEntity<>(mail, HttpStatus.OK);}



    @PutMapping("/ModifyAdmin")
    public ResponseEntity<?> ModifyAdmin( @RequestBody AppUser appUser) {

        appUserService.UpdateUser(appUser);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/GetAdmin/{id}")
    public AppUser GetAdmin(  @PathVariable String id) {

      AppUser appUser= appUserService.getUser(id);
        return appUser;
    }

    @PostMapping("/authenticate")
    public UserTokenDTO authenticateUser(@RequestBody UserLoginDTO userLoginDTO )  {

        return appUserService.createJwtToken(userLoginDTO);
    }

    @PostMapping("/delete/{id}")
    public void deleteUser (@PathVariable String id )  {

  appUserService.deleete(id);
    }


    @PostMapping("/resetPassword/{id}")
    public void resetPass (@PathVariable String id, @RequestBody String Password)  {

        appUserService.ResetPass(id, Password);;
    }




}
