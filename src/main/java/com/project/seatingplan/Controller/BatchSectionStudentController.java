package com.project.seatingplan.Controller;

import com.project.seatingplan.Service.BatchSectionStudentService;
import com.project.seatingplan.model.Student;
import com.project.seatingplan.utils.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/batchSectionStudents")
public class BatchSectionStudentController {

  private static final Logger logger = LoggerFactory.getLogger(BatchSectionStudentController.class);
  private final BatchSectionStudentService batchSectionStudentService;

  public BatchSectionStudentController(BatchSectionStudentService batchSectionStudentService) {
    this.batchSectionStudentService = batchSectionStudentService;
  }

  @RequestMapping(value = "/bulkInsert", method = RequestMethod.POST, headers = ("content-type=multipart/*"), produces = "application/json", consumes = "application/json")
  public RestResponse bulkInsert (
    @RequestParam(value = "batch_id", required = false) Integer batchId,
    @RequestParam(value = "lkp_semester", required = false) Integer lkpSemesterId,
    @RequestParam(value = "studentListFile") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
    try {
      logger.info("Student bulk upload file-name:{}", file.getOriginalFilename());

      String message = batchSectionStudentService.save(batchId, lkpSemesterId,file);
      logger.info(message);
      return RestResponse.build().withSuccess(message);
    } catch (IOException e) {
      logger.error("Failed to add bulk students due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to add bulk students due to: "+ e.getMessage());
    }
  }
  @RequestMapping(value = "/byBatch", method = RequestMethod.GET, produces = "application/json")
  public RestResponse findByBatchId(@RequestParam("batchId") Integer batchId) {

    try {
      logger.info("Find student by batch id:{}", batchId); //For HOD regarding tasks
      List<Student> studentList = batchSectionStudentService.findByBatchId(batchId);

      logger.info("Fetched student list size:{}", studentList.size());
      return RestResponse.build().withSuccess().withData(studentList);
    } catch (Exception e) {
      logger.error("Failed to find batchSectionStudent due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to find batchSectionStudent due to: "+ e.getMessage());
    }
  }
}
