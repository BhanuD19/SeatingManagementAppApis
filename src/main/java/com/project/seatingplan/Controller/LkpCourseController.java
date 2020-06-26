package com.project.seatingplan.Controller;

import com.project.seatingplan.Service.LkpCourseService;
import com.project.seatingplan.model.LkpCourse;
import com.project.seatingplan.utils.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lkpCourse")
public class LkpCourseController {
  private static final Logger logger = LoggerFactory.getLogger(LkpCourseController.class);

  private final LkpCourseService lkpCourseService;

  public LkpCourseController(LkpCourseService lkpCourseService) {
    this.lkpCourseService = lkpCourseService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public RestResponse findByStateId() {
    try {
      logger.info("Fetching all lkp Course.");
      List<LkpCourse> lkpCourseList = lkpCourseService.findAll();
      return RestResponse.build().withSuccess().withData(lkpCourseList);

    } catch (Exception e) {
      logger.error("Failed to fetch course due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to fetch course due to:" + e.getMessage());
    }
  }
}
