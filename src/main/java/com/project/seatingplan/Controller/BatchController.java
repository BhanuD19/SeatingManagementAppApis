package com.project.seatingplan.Controller;

import com.project.seatingplan.Service.BatchService;
import com.project.seatingplan.dto.request.web.BatchReq;
import com.project.seatingplan.dto.response.web.BatchResp;
import com.project.seatingplan.utils.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batches")
public class BatchController {
  private static final Logger logger = LoggerFactory.getLogger(BatchController.class);

  private final BatchService batchService;

  public BatchController(BatchService batchService) {
    this.batchService = batchService;
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
  public RestResponse save(@RequestBody BatchReq batchReq) {
    try {
      logger.info("Save batch: {}", batchReq);
      batchService.save(batchReq);
    } catch (Exception e) {
      logger.error("Failed to add batch due to: {}", e.toString(), e);
      return RestResponse.build().withError("Failed to add batch due to: " + e.getMessage());
    }
    logger.info("batch added successfully");
    return RestResponse.build().withSuccess("batch added successfully")
  }


  @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
  public RestResponse update(@RequestBody BatchReq batchReq) {
    try {
      logger.info("Update batch: {}", batchReq);
      batchService.update(batchReq);
    } catch (Exception e) {
      logger.error("Failed to update batch due to: {}", e.toString(), e);
      return RestResponse.build().withError("Failed to update batch due to: " + e.getMessage());
    }
    logger.info("batch updated successfully");
    return RestResponse.build().withSuccess("batch updated successfully")
  }

  @RequestMapping(value = "/byCourse/{courseId}", method = RequestMethod.GET)
  public RestResponse findById(@PathVariable("courseId") Integer courseId) {
    try {
      Integer instituteId = 1;
      logger.info("Finding batch by course-id:{}", courseId);
      List<BatchResp> batchRespList = batchService.findByCourseId(instituteId, courseId);
      if (batchRespList != null) {
        logger.info("Batch retrieved successfully");
        return RestResponse.build().withSuccess().withData(batchRespList);
      } else {
        return RestResponse.build().withError("no batch data found");
      }
    } catch (Exception e) {
      logger.error("Failed to find batch due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to find batch due to: " + e.getMessage());
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
  public RestResponse delete(@PathVariable("id") Integer id) {
    try {
      logger.info("Batch delete by batch-id:{}", id);
      int batchDelete = batchService.markDelete(id);
      if (batchDelete > 0) {
        logger.info("Batch deleted successfully");
        return RestResponse.build().withSuccess("Batch deleted successfully");
      } else {
        return RestResponse.build().withError("no batch data deleted");
      }
    } catch (Exception e) {
      logger.error("Failed to delete batch due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to delete batch due to: " + e.getMessage());
    }
  }
}
