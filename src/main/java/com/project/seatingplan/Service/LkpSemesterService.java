package com.project.seatingplan.Service;

import com.project.seatingplan.dto.response.web.LkpSemesterResp;
import com.project.seatingplan.model.LkpSemester;
import com.project.seatingplan.repository.LkpSemesterRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LkpSemesterService {
  private static final Logger logger = LoggerFactory.getLogger(LkpSemesterService.class);

  private final LkpSemesterRepo lkpSemesterRepo;

  public LkpSemesterService(LkpSemesterRepo lkpSemesterRepo) {
    this.lkpSemesterRepo = lkpSemesterRepo;
  }

  public List<LkpSemesterResp> findAll() {
    List<LkpSemester> lkpSemesterList = lkpSemesterRepo.findAll();
    List<LkpSemesterResp> lkpSemesterRespList = new ArrayList<>();

    for (LkpSemester lkpSemester : lkpSemesterList) {
      LkpSemesterResp lkpSemesterResp = new LkpSemesterResp();
      lkpSemesterResp.setId(lkpSemester.getId());
      lkpSemesterResp.setName(lkpSemester.getName());
      lkpSemesterRespList.add(lkpSemesterResp);
    }
    return lkpSemesterRespList;
  }
}
