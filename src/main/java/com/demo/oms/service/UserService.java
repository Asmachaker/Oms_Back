package com.demo.oms.service;

import com.demo.oms.entity.AppUser;
import com.demo.oms.dto.UserLoginDTO;
import com.demo.oms.dto.UserTokenDTO;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService {


   void addAdmin(AppUser c) throws MessagingException;

    Boolean EnableUser(String id);

    Boolean disableUser(String id);

    boolean GetEnabledByuser(String id);

    String GetOldPassword(String id);

    List<AppUser> getAllUsers();

    AppUser getUser(String id);

   void UpdateUser(AppUser user);

    void deleete(String id);

    void ResetPass(String id, String password);

    boolean sendEmail(String email);

    UserTokenDTO createJwtToken(UserLoginDTO userLoginDTO);

    interface EmailService {
   }
}
