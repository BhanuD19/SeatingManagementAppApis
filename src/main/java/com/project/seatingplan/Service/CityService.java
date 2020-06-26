package com.project.seatingplan.Service;

import com.project.seatingplan.dto.response.web.CityResp;
import com.project.seatingplan.model.City;
import com.project.seatingplan.repository.CityRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CityService {
  private final CityRepo cityRepo;

  public CityService(CityRepo cityRepo) {
    this.cityRepo = cityRepo;
  }

  @Transactional(readOnly = true)
  public List<CityResp> findAll(Integer stateId) {
    List<City> cityList = cityRepo.findAll();
    List<CityResp> cityRespList = new ArrayList<>();
    CityResp cityResp = null;

    for (City city: cityList) {
      cityResp = new CityResp();
      cityResp.setId(city.getId());
      cityResp.setName(city.getName());
      cityRespList.add(cityResp);
    }
    return cityRespList;
  }
}
