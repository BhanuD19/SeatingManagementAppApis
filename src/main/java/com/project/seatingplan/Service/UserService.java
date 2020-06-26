package com.project.seatingplan.Service;

import com.project.seatingplan.dto.request.mobile.ChangePassword;
import com.project.seatingplan.dto.request.mobile.ForgotPassword;
import com.project.seatingplan.dto.request.mobile.LoginUser;
import com.project.seatingplan.dto.request.web.UserReq;
import com.project.seatingplan.dto.response.mob.CityResp;
import com.project.seatingplan.dto.response.mob.LoginUserResp;
import com.project.seatingplan.dto.response.mob.StateResp;
import com.project.seatingplan.model.User;
import com.project.seatingplan.repository.CityRepo;
import com.project.seatingplan.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserService {
  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  private final UserRepo userRepo;
  private final CityRepo cityRepo;

  public UserService(UserRepo userRepo, CityRepo cityRepo) {
    this.userRepo = userRepo;
    this.cityRepo = cityRepo;
  }

  public void update(UserReq userReq) {
    User user = new User();
    user.setFirstName(userReq.getFirstName());
    user.setLastName(userReq.getLastName());

    user.setContactOne(userReq.getContactOne());
    user.setContactTwo(userReq.getContactTwo());
    user.setGender(userReq.getGender());
    user.setPassword(userReq.getPassword());
    user.setIsDeleted(0);
    userRepo.save(user);
  }

  public void changePassword(ChangePassword changePassword) {
    String oldPasswordFromReq = changePassword.getOldPassword();
    Integer userId = changePassword.getId();
    User user = userRepo.findByUserId(userId);
    String oldPassword = user.getPassword();
    if (oldPassword.equals(oldPasswordFromReq)) {
      userRepo.save(user);
      return;
    }
    logger.info("Entered old password doesn't match your password");
  }

  public void forgotPassword(ForgotPassword forgotPassword) {
    User user = userRepo.findOneByEmail(forgotPassword.getEmail());
    String userPassword = forgotPassword.getNewPassword();
    String confirmationPassword = forgotPassword.getNewPassword2();
    if (user != null) {
      if (userPassword.equals(confirmationPassword)) {
        if (!(userPassword.equals(user.getPassword()))) {
          userRepo.forgetPassword(userPassword, user.getEmail());
          return;
        } else {
          logger.info("New password cannot be equal to old one");
        }
      } else {
        logger.info("New password and confirmation password should be same");
      }
    } else {
      logger.info("No user with the given email is found");
    }
  }

  public LoginUserResp loginByEmailPassword(String email, String contactOne, String password) {
    User user = userRepo.loginByEmailPassword(email, contactOne, password);
    LoginUserResp loginUserResp = null;
    CityResp cityResp = new CityResp();

    if (user != null) {
      loginUserResp = new LoginUserResp();
      loginUserResp.setId(user.getId());
      loginUserResp.setFirstName(user.getFirstName());
      loginUserResp.setLastName(user.getLastName());
      loginUserResp.setEmail(user.getEmail());
      loginUserResp.setGender(user.getGender());
      loginUserResp.setContactOne(user.getContactOne());
      loginUserResp.setContactTwo(user.getContactTwo());
      loginUserResp.setProfilePhoto(user.getProfilePhoto());
      loginUserResp.setCity(cityResp);
    }

    return loginUserResp;
  }

  public User findOneByEmail(String email) { return userRepo.findOneByEmail(email); }

  public List<User> findAll() { return userRepo.findAll(); }

  public User findOneById (Integer id) {
    User user = userRepo.findOneById(id);
    return user;
  }
}
