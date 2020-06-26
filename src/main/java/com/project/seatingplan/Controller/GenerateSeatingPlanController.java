package com.project.seatingplan.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.seatingplan.Service.GenerateSeatingPlanService;
import com.project.seatingplan.dto.request.web.SeatingPlanGenerateReq;
import com.project.seatingplan.utils.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/generateSeatingPlan")
public class GenerateSeatingPlanController {
  private static final Logger logger = LoggerFactory.getLogger(GenerateSeatingPlanController.class);

  private final GenerateSeatingPlanService generateSeatingPlanService;

  public GenerateSeatingPlanController(GenerateSeatingPlanService generateSeatingPlanService) {
    this.generateSeatingPlanService = generateSeatingPlanService;
  }

  @RequestMapping(method = RequestMethod.POST, headers = ("content-type=multipart/*"), produces = "application/json", consumes = "application/json")
  public RestResponse save(@RequestParam(value = "inputJson", required = false) String inputJson, @RequestParam(value = "studentListFile") MultipartFile inputFile) {
    try {
      logger.info("Input Json:{}", inputJson);

      logger.info("Student bulk upload File-name:{}", inputFile.getOriginalFilename());

      ObjectMapper mapper = new ObjectMapper();
      SeatingPlanGenerateReq seatingPlanGenerateReq = mapper.readValue(inputJson, SeatingPlanGenerateReq.class);

      logger.info("Generate seating plan request view:{}", seatingPlanGenerateReq);

      List<Map<String, Object>> seatingPlanList = generateSeatingPlanService.createSeatingPlan(seatingPlanGenerateReq, inputFile);
      return RestResponse.build().withSuccess().withData(seatingPlanList);

    } catch (Exception e) {
      logger.error("Failed to generate due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to find generator seating plan to :" + e.getMessage());

    }

  }
}
