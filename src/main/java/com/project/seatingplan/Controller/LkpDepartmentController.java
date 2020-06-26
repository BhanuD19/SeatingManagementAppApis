package com.project.seatingplan.Controller;

import com.project.seatingplan.Service.LkpDepartmentService;
import com.project.seatingplan.model.LkpDepartment;
import com.project.seatingplan.utils.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lkpDepartment")
public class LkpDepartmentController {
  private static final Logger logger = LoggerFactory.getLogger(LkpDepartmentController.class);

  private final LkpDepartmentService lkpDepartmentService;

  public LkpDepartmentController(LkpDepartmentService lkpDepartmentService) {
    this.lkpDepartmentService = lkpDepartmentService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public RestResponse findByStateId() {
    try {
      logger.info("Fetching all lkp department.");
      List<LkpDepartment> lkpDepartmentList = lkpDepartmentService.findAll();
      return RestResponse.build().withSuccess().withData(lkpDepartmentList);

    } catch (Exception e) {
      logger.error("Failed to fetch department due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to fetch department due to:" + e.getMessage());
    }
  }
}
