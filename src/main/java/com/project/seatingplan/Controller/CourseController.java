package com.project.seatingplan.Controller;

import com.project.seatingplan.Service.CourseService;
import com.project.seatingplan.dto.request.web.CourseReq;
import com.project.seatingplan.model.Course;
import com.project.seatingplan.utils.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
  private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @RequestMapping(method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
  public RestResponse save (@RequestBody CourseReq courseReq) {
    try {
      logger.info("Add course in department: {}", courseReq.getDepartmentId());
      String message = courseService.save(courseReq);
      return RestResponse.build().withSuccess(message);
    } catch (Exception e) {
      logger.error("Failed to create course due to: {}", e.toString(), e);
      return RestResponse.build().withError("Failed to create department due to "+ e.getMessage());
    }
  }

  @RequestMapping(value = "/byDepartmentId/{departmentId}", method = RequestMethod.GET, produces = "application/json")
  public RestResponse findById(@PathVariable("departmentId") Integer departmentId) {
    try {
      Integer instituteId = 1;
      logger.info("Find course for departmentID:{}, instituteId:{}", departmentId, instituteId);
      List<Course> courseList = courseService.findByDepartment(instituteId, departmentId);
      if (courseList != null) {
        logger.info("Successfully course retrieved:{}", courseList);
        return RestResponse.build().withSuccess().withData(courseList);
      } else {
        return RestResponse.build().withError("No course data found");
      }
    } catch (Exception e) {
      logger.error("Failed to find course due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to find course by department due to"+ e.getMessage());
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public RestResponse delete(@PathVariable("id") Integer id) {
    try {
      logger.info("delete course for id:{}", id);
      int deleted = courseService.deleteCourse(id);
      if (deleted > 0) {
        return RestResponse.build().withSuccess("Course deleted successfully");
      } else {
        return RestResponse.build().withError("No course record deleted");
      }
    } catch (Exception e) {
      logger.error("Failed to delete course due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to delete course data due to"+ e.getMessage());
    }
  }
}
