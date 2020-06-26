package com.project.seatingplan.Controller;

import com.project.seatingplan.Service.DepartmentService;
import com.project.seatingplan.dto.request.web.DepartmentReq;
import com.project.seatingplan.dto.response.web.DepartmentResp;
import com.project.seatingplan.utils.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
  private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

  private final DepartmentService departmentService;

  public DepartmentController(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
  public RestResponse save(@RequestBody DepartmentReq departmentReq) {

    try {
      logger.info("Add department in institute: {}", departmentReq.getInstituteId());
      String message = departmentService.save(departmentReq);
      return RestResponse.build().withSuccess(message);

    } catch (Exception e) {
      logger.error("Failed to create Department due to: {}", e.toString(), e);
      return RestResponse.build().withError("Failed to create Department..." + e.getMessage());
    }
  }

  @RequestMapping(value = "/byInstitute", method = RequestMethod.GET)
  public RestResponse findByInstituteId() {
    try {
      Integer instituteId = 1;
      logger.info("find department by id:{}", instituteId);
      List<DepartmentResp> departmentRespList = departmentService.findByInstituteId(instituteId);
      if (departmentRespList != null) {

        logger.info("Department fetched successfully");
        return RestResponse.build().withSuccess().withData(departmentRespList);
      } else {
        return RestResponse.build().withError("No data found");
      }
    } catch (Exception e) {
      logger.error("Failed to find department due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to find department due to :" + e.getMessage());
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public RestResponse delete(@PathVariable("id") Integer id) {
    try {
      logger.info("Delete Department for Id:{}", id);
      int deleteDepartment = departmentService.delete(id);
      if (deleteDepartment > 0) {
        return RestResponse.build().withSuccess("Department deleted successfully.");
      } else {
        return RestResponse.build().withError("No record deleted.");
      }
    } catch (Exception e) {
      logger.error("Failed to delete Department due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to delete Department due to :" + e.getMessage());
    }
  }
}
