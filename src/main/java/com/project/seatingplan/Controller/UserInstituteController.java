package com.project.seatingplan.Controller;

import com.project.seatingplan.Service.UserInstituteService;
import com.project.seatingplan.model.UserInstitute;
import com.project.seatingplan.utils.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userInstitutes")
public class UserInstituteController {
  private static final Logger logger = LoggerFactory.getLogger(UserInstituteController.class);

  private final UserInstituteService userInstituteService;

  public UserInstituteController(UserInstituteService userInstituteService) {
    this.userInstituteService = userInstituteService;
  }

  // By Id
  @RequestMapping(value = "/ById/{id}", method = RequestMethod.GET, produces = "application/json")
  public RestResponse findUserInstituteById(@PathVariable("id") Integer id) {
    try {
      logger.info("find all user institutes by id:{}", id);
      UserInstitute userInstitute = userInstituteService.findById(id);
      return RestResponse.build().withSuccess().withData(userInstitute);
    } catch (Exception e) {
      logger.error("Failed to fetch user institute due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to fetch user institute due to:" + e.getMessage());
    }
  }

  // By UserId
  @RequestMapping(value = "/ByUserId/{id}", method = RequestMethod.GET, produces = "application/json")
  public RestResponse findAllByUserId(@PathVariable("id") Integer id) {
    try {
      logger.info("find all user institutes by userId: {}", id);
      List<UserInstitute> userInstitute = userInstituteService.findByUserId(id);
      return RestResponse.build().withSuccess().withData(userInstitute);

    } catch (Exception e) {
      logger.error("Failed to fetch user institute due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to fetch user institute due to:" + e.getMessage());
    }
  }

  //Delete
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
  public RestResponse delete(@PathVariable("id") Integer id) {
    try {
      logger.info("delete user institute by id:{}", id);
      int deleted = userInstituteService.markDeleted(id);
      if (deleted > 0) {
        return RestResponse.build().withSuccess("user institutes deleted successfully.");
      } else {
        return RestResponse.build().withError("user institutes not deleted");
      }
    } catch (Exception e) {
      logger.error("Failed to delete user institutes due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to delete user institutes due to:" + e.getMessage());
    }
  }
}
