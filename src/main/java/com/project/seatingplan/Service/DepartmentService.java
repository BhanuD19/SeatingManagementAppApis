package com.project.seatingplan.Service;

import com.project.seatingplan.dto.request.web.DepartmentReq;
import com.project.seatingplan.dto.response.web.DepartmentResp;
import com.project.seatingplan.model.Department;
import com.project.seatingplan.repository.DepartmentRepo;
import com.project.seatingplan.repository.InstituteRepo;
import com.project.seatingplan.repository.LkpDepartmentRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DepartmentService {
  private final DepartmentRepo departmentRepo;
  private final InstituteRepo instituteRepo;
  private final LkpDepartmentRepo lkpDepartmentRepo;

  public DepartmentService(DepartmentRepo departmentRepo, InstituteRepo instituteRepo, LkpDepartmentRepo lkpDepartmentRepo) {
    this.departmentRepo = departmentRepo;
    this.instituteRepo = instituteRepo;
    this.lkpDepartmentRepo = lkpDepartmentRepo;
  }

  public String save(DepartmentReq departmentReq) throws Exception {
    Department department = new Department();
    department.setInstitute(instituteRepo.getOne(departmentReq.getInstituteId()));
    department.setLkpDepartment(lkpDepartmentRepo.getOne(departmentReq.getLkpDepartmentId()));
    department.setCreatedDate(Date.from(Instant.now()));
    department.setIsDeleted(0);

    Department saveDepartment = departmentRepo.save(department);

    if(saveDepartment != null) {
      return "Department created successfully";
    }

    return "Failed to create department";
  }

  @Transactional(readOnly = true)
  public List<DepartmentResp> findByInstituteId(Integer instituteId) {
    List<Department> departmentList = departmentRepo.findByInstituteId(instituteId);
    List<DepartmentResp> departmentRespList = new ArrayList<>();
    DepartmentResp departmentResp = null;

    for (Department department : departmentList) {
      departmentResp = new DepartmentResp();
      departmentResp.setId(department.getId());
      departmentResp.setInstituteId(department.getInstitute().getId());
      departmentResp.setInstituteName(department.getInstitute().getName());
      departmentResp.setName(department.getLkpDepartment().getName());
      departmentResp.setCode(department.getLkpDepartment().getCode());

      departmentRespList.add(departmentResp);
    }
    return departmentRespList;
  }

  @Transactional(readOnly = true)
  public DepartmentResp findById(Integer id) {
    Department department = departmentRepo.findByDepartmentId(id);
    DepartmentResp departmentResp = new DepartmentResp();
    departmentResp.setId(department.getLkpDepartment().getId());
    departmentResp.setInstituteId(department.getInstitute().getId());
    departmentResp.setInstituteName(department.getInstitute().getName());
    departmentResp.setName(department.getLkpDepartment().getName());
    return departmentResp;
  }

  public int delete(Integer id) {
    return departmentRepo.markDeleted(id);
  }
}
