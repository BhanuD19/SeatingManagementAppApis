package com.project.seatingplan.Service;

import com.project.seatingplan.model.LkpDepartment;
import com.project.seatingplan.repository.LkpDepartmentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class LkpDepartmentService {
  private static final Logger logger = LoggerFactory.getLogger(LkpDepartmentService.class);

  private final LkpDepartmentRepo lkpDepartmentRepo;

  public LkpDepartmentService(LkpDepartmentRepo lkpDepartmentRepo) {
    this.lkpDepartmentRepo = lkpDepartmentRepo;
  }

  public List<LkpDepartment> findAll() {
    return lkpDepartmentRepo.findAll();
  }
}
