package com.demo.oms.service.impl;


import com.demo.oms.security.MyUserDetailsService;
import com.demo.oms.util.JwtTokenUtil;
import com.demo.oms.entity.AppUser;
import com.demo.oms.dto.UserLoginDTO;
import com.demo.oms.dto.UserTokenDTO;
import com.demo.oms.repository.AppUserRepository;
import com.demo.oms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;


@Service
public class AppUserServiceImpl implements UserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private EmailServiceImpl emailService;



    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private MyUserDetailsService jwtUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;




    @Override
    public void addAdmin(AppUser appUser) {
      String Email = appUser.getEmail();
        try {  emailService.ActivationMail(Email, appUser.getFirstName());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        appUserRepository.save(appUser);

        }

    @Override
    public Boolean EnableUser(String id) {
            appUserRepository.enableAppUser(id);
          AppUser user =appUserRepository.findById(id).get();
            String Email = user.getEmail();
            try {  emailService.ActivationMail(Email, user.getFirstName());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
         return true;
        }

    @Override
    public Boolean disableUser(String id) {
        appUserRepository.disableAppUser(id);
    return true ;}

    @Override
    public boolean GetEnabledByuser(String id) {
        return appUserRepository.userStatut(id);
    }

    @Override
    public String GetOldPassword(String id) {
        return appUserRepository.oldMdp(id);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser getUser(String id) {

        return appUserRepository.findById(id).get();
    }

    @Override
    public void UpdateUser(AppUser user) {
        appUserRepository.save(user);
    }
    @Override
    public void deleete(String id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public void ResetPass(String id, String password) {
        appUserRepository.resetPassword(password,id);
    }


    @Override
    public boolean sendEmail(String email) {
        boolean verify = false;
      String username = appUserRepository.emailSearch(email);
      if (username!=null)
      { try {  emailService.ResetPasswordMail(email, username);
          verify=true;}
      catch (MessagingException e) {
          e.printStackTrace();
      }}


        return verify;
    }




    public String authenticate (UserLoginDTO userLoginDTO) {

        try {
             authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword()));
        }
        catch (BadCredentialsException badCredentialsException)
        {
            AppUser user = appUserRepository.findById(userLoginDTO.getUsername()).get();
            throw new BadCredentialsException("Invalid password");
        }
       final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userLoginDTO.getUsername());

        String jwt = jwtUtil.generateToken(userDetails);
        return jwt;
    }


    @Override
    public UserTokenDTO createJwtToken(UserLoginDTO userLoginDTO)  {
        String userName = userLoginDTO.getUsername();
        String userPassword = userLoginDTO.getPassword();
        String jwt = authenticate(userLoginDTO);

        AppUser user =appUserRepository.findById(userName).get();


        UserTokenDTO userTokenDTO =new UserTokenDTO(jwt,user.getUsername(),user.getFirstName(),user.getLastName(),user.getEmail()
                ,user.getPassword(),user.getPhoneNumber(),user.getAddress(),user.getAppUserRole().getAuthority(),user.getEnabled());


        return userTokenDTO;
    }



    }



