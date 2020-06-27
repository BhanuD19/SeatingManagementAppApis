package com.project.seatingplan.Controller;

import com.project.seatingplan.Service.CityService;
import com.project.seatingplan.dto.response.web.CityResp;
import com.project.seatingplan.utils.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {
  private static final Logger logger = LoggerFactory.getLogger(CityController.class);

  private final CityService cityService;

  public CityController(CityService cityService) {
    this.cityService = cityService;
  }

  @RequestMapping(value = "/{stateId}", method = RequestMethod.GET, produces = "application/json")
  public RestResponse findByStateId(@PathVariable("stateId") Integer stateId) {
    try {
      logger.info("Fetching cities according to state-id:{}", stateId);
      List<CityResp> cityRespList = cityService.findAll(stateId);
      return RestResponse.build().withSuccess().withData(cityRespList);
    } catch (Exception e) {
      logger.error("Failed to fetch cities due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to fetch cities due to: "+ e.getMessage());
    }
  }
}
