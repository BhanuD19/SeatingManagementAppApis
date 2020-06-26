package com.project.seatingplan.Controller;

import com.project.seatingplan.Service.FileService;
import com.project.seatingplan.Service.UserService;
import com.project.seatingplan.dto.request.mobile.ChangePassword;
import com.project.seatingplan.dto.request.mobile.LoginUser;
import com.project.seatingplan.dto.response.mob.LoginUserResp;
import com.project.seatingplan.model.User;
import com.project.seatingplan.repository.UserRepo;
import com.project.seatingplan.utils.RestResponse;
import com.project.seatingplan.utils.RestUriConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/users")
public class UserController {
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  private final UserService userService;
  private final UserRepo userRepo;
  private final FileService fileService;

  public UserController(UserService userService, UserRepo userRepo, FileService fileService) {
    this.userService = userService;
    this.userRepo = userRepo;
    this.fileService = fileService;
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
  public RestResponse l(@RequestBody LoginUser loginUserReq) {
    try {
      logger.info("user login for email-id :{}", loginUserReq.getEmail());
      LoginUserResp loginUserResp = userService.loginByEmailPassword(loginUserReq.getEmail(), loginUserReq.getContactOne(), loginUserReq.getPassword());

      if (loginUserResp != null) {
        logger.info("User login successfully, email-id:{}, user-id:{}", loginUserResp.getEmail(), loginUserResp.getId());
        return RestResponse.build().withSuccess("User Login successfully.").withData(loginUserResp);
      } else {
        logger.error("User email password is incorrect.");
        return RestResponse.build().withError("User email password is incorrect.");
      }
    } catch (Exception e) {
      logger.error("Failed to login user due to  {}:", e.toString(), e);
      return RestResponse.build().withError("Failed to login user due to :" + e.toString());
    }
  }

  @RequestMapping(value = "/changePassword", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
  public RestResponse ChangePassword(@RequestBody ChangePassword changePasswordReq) {
    try {
      logger.info("update user NewPassword:{}", changePasswordReq.getNewPassword());
      userService.changePassword(changePasswordReq);
    } catch (Exception e) {
      logger.error("Failed to update user New password due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to update user New password due to :" + e.getMessage());
    }
    logger.error(" Cannot changePassword for userId: {}.", changePasswordReq.getId());
    return RestResponse.build().withError("NewPassword not updated for login.");
  }

  @RequestMapping(value = "/image/{info:.+}", method = RequestMethod.GET, produces = "image/jpeg")
  public byte[] getImage(@PathVariable("info") String info) {
    try {
      logger.info("get user image with info:{}", info);
      if (info.endsWith(FileService.IMAGE_SUFFIX)) {
        return getImageWithName(info);
      } else {
        return getImageWithId(Integer.parseInt(info));
      }
    } catch (Exception e) {
      logger.error("Failed to get user's Image due to:{}", e.toString(), e);
      throw new IllegalArgumentException("No image found due to:" + e, e);
    }
  }

  private byte[] getImageWithId(Integer id) throws Exception {
    logger.info("get user image for id:{}", id);
    User entity = userRepo.getOne(id);
    byte[] data = fileService.getImageDataWithURI(entity.getProfilePhoto());
    logger.info("returning image with size:{} ", data.length);
    return data;
  }

  private byte[] getImageWithName(String name) throws Exception {
    logger.info("get user image for name:{}", name);
    byte[] data = fileService.getImageDataWithName(RestUriConstants.USER, name);
    logger.info("returning image with size:{} ", data.length);
    return Base64.getEncoder().encode(data);
  }
}
