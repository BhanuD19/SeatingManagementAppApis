package com.project.seatingplan.Service;

import com.project.seatingplan.dto.request.web.InstituteReq;
import com.project.seatingplan.dto.response.web.InstituteResp;
import com.project.seatingplan.model.City;
import com.project.seatingplan.model.Institute;
import com.project.seatingplan.repository.CityRepo;
import com.project.seatingplan.repository.InstituteRepo;
import com.project.seatingplan.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class InstituteService {
  private static final Logger logger = LoggerFactory.getLogger(InstituteService.class);
  private final InstituteRepo instituteRepo;
  private final CityRepo cityRepo;
  private final UserRepo userRepo;

  public InstituteService(InstituteRepo instituteRepo, CityRepo cityRepo, UserRepo userRepo) {
    this.instituteRepo = instituteRepo;
    this.cityRepo = cityRepo;
    this.userRepo = userRepo;
  }

  public void save(InstituteReq instituteReq, Integer userId) throws Exception {
    Institute institute = new Institute();

    institute.setId(instituteReq.getId());

    institute.setAddress(instituteReq.getAddress());
    institute.setAlias(instituteReq.getAlias());
    institute.setCode(instituteReq.getCode());
    institute.setEmail(instituteReq.getEmail());
    institute.setName(instituteReq.getName());
    institute.setContactOne(instituteReq.getContactOne());
    institute.setContactTwo(instituteReq.getContactTwo());
    institute.setWebsite(instituteReq.getWebsite());
    institute.setCity(cityRepo.getOne(instituteReq.getCityId()));
    institute.setRegistrationNo(instituteReq.getRegistrationNo());
    institute.setCreatedDate(Date.from(Instant.now()));
    institute.setIsDeleted(0);

    instituteRepo.save(institute);
  }

  public String update(InstituteReq instituteReq) {
    logger.info("Fetching entity for cityId: {}", instituteReq.getCityId());
    City city = cityRepo.getOne(instituteReq.getCityId());
    logger.info("Fetched entity for cityId: {}, cityName: {}", city.getId(), city.getName());

    Institute ref = instituteRepo.getOne(instituteReq.getId());
    Institute institute = new Institute();

    institute.setId(instituteReq.getId());
    institute.setAddress(instituteReq.getAddress());
    institute.setEmail(instituteReq.getEmail());
    institute.setName(instituteReq.getName());
    institute.setContactOne(instituteReq.getContactOne());
    institute.setContactTwo(instituteReq.getContactTwo());
    institute.setWebsite(instituteReq.getWebsite());
    institute.setCity(city);
    institute.setLogo(instituteReq.getLogo());
    institute.setUpdatedDate(Date.from(Instant.now()));
    institute.setCreatedDate(ref.getCreatedDate());
    institute.setIsDeleted(ref.getIsDeleted());
    institute.setLogo(instituteReq.getLogo());
    institute.setAlias(ref.getAlias());
    institute.setCode(ref.getCode());
    institute.setRegistrationNo(ref.getRegistrationNo());

    if (instituteReq.getLogo() == null) {
      institute.setLogo(ref.getLogo());
    }
    Institute saveInstitute = instituteRepo.save(institute);
    if (saveInstitute != null) {
      return "done";
    }
    return "fail";
  }

  public Institute findById(Integer id) {return instituteRepo.findByInstituteId(id);}

  public List<Institute> findByAlias(String alias) {return instituteRepo.findByAlias(alias);}

  public List<InstituteResp> findByUserId (Integer userId) {
    List<InstituteResp> instituteRespList = new ArrayList<>();
    List<Institute> instituteList = instituteRepo.findByUserId(userId);

    for (Institute institute : instituteList) {
      InstituteResp instituteResp = new InstituteResp();
      instituteResp.setId(institute.getId());
      instituteResp.setAddress(institute.getAddress());
      instituteResp.setEmail(institute.getEmail());
      instituteResp.setName(institute.getName());
      instituteResp.setContactOne(institute.getContactOne());
      instituteResp.setContactTwo(institute.getContactTwo());
      instituteResp.setWebsite(institute.getWebsite());
      instituteResp.setLogo(institute.getLogo());
      instituteResp.setAlias(institute.getAlias());
      instituteResp.setCode(institute.getCode());
      instituteResp.setRegistrationNo(institute.getRegistrationNo());
      instituteRespList.add(instituteResp);
    }
    return instituteRespList;
  }

  public int markDeleted(Integer id) {
    return instituteRepo.markDeleted(id);
  }
}
