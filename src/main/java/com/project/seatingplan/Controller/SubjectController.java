package com.project.seatingplan.Controller;

import com.project.seatingplan.Service.SubjectService;
import com.project.seatingplan.dto.request.web.SubjectReq;
import com.project.seatingplan.dto.response.web.SubjectResp;
import com.project.seatingplan.utils.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
  private static final Logger logger = LoggerFactory.getLogger(SubjectController.class);

  private final SubjectService subjectService;

  public SubjectController(SubjectService subjectService) {
    this.subjectService = subjectService;
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
  public RestResponse save(@RequestBody SubjectReq subjectReq) {
    try {
      logger.info("save Subject:{}", subjectReq);
      subjectService.save(subjectReq);
    } catch (Exception e) {
      logger.error("Failed to add subject due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to add subject due to :" + e.getMessage());
    }
    return RestResponse.build().withError("Failed to add subject.");
  }

  @RequestMapping(value = "/byFilter", method = RequestMethod.GET)
  public RestResponse findById(@RequestParam("courseId") Integer courseId,
                               @RequestParam("lkpSemesterId") Integer lkpSemesterId) {
    try {
      logger.info("find subject for courseId:{}, lkpSemesterId:{}", courseId, lkpSemesterId);
      List<SubjectResp> subjectRespList = subjectService.findByCourseSemesterId(lkpSemesterId, courseId);
      if (subjectRespList != null) {
        return RestResponse.build().withSuccess().withData(subjectRespList);
      } else {
        return RestResponse.build().withError("No batch data found");
      }
    } catch (Exception e) {
      logger.error("Failed to find subject due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to find subject by courseId semesterId due to :" + e.getMessage());
    }
  }
}
