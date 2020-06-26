package com.project.seatingplan.Controller;

import com.project.seatingplan.Service.LkpSemesterService;
import com.project.seatingplan.dto.response.web.LkpSemesterResp;
import com.project.seatingplan.utils.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lkpSemesters")
public class LkpSemesterController {
  private static final Logger logger = LoggerFactory.getLogger(LkpSemesterController.class);

  private final LkpSemesterService lkpSemesterService;

  public LkpSemesterController(LkpSemesterService lkpSemesterService) {
    this.lkpSemesterService = lkpSemesterService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public RestResponse findAll() {
    try {
      logger.info("Fetching all lkp semester.");
      List<LkpSemesterResp> lkpSemesterRespList = lkpSemesterService.findAll();
      return RestResponse.build().withSuccess().withData(lkpSemesterRespList);

    } catch (Exception e) {
      logger.error("Failed to fetch semester due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to fetch semester due to:" + e.getMessage());
    }
  }
}
