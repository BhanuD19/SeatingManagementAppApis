package com.project.seatingplan.Service;

import com.project.seatingplan.model.UserInstitute;
import com.project.seatingplan.repository.UserInstituteRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserInstituteService {
  private final UserInstituteRepo userInstituteRepo;

  public UserInstituteService(UserInstituteRepo userInstituteRepo) {
    this.userInstituteRepo = userInstituteRepo;
  }

  public void save() {

  }

  public UserInstitute findById(Integer id) { return userInstituteRepo.getOne(id); }

  public List<UserInstitute> findByUserId(Integer id) { return userInstituteRepo.findByUserId(id); }

  public List<UserInstitute> findAll() { return userInstituteRepo.findAll(); }

  public int markDeleted(Integer id) { return userInstituteRepo.markDeleted(id); }
}
