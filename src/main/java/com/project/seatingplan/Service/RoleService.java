package com.project.seatingplan.Service;

import com.project.seatingplan.model.Role;
import com.project.seatingplan.repository.RoleRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RoleService {

  private final RoleRepo roleRepo;

  public RoleService(RoleRepo roleRepo) {
    this.roleRepo = roleRepo;
  }

  public Role findById(Integer id) {return roleRepo.getOne(id); }

  public Role findByName(String name) { return roleRepo.findByName(name); }

  public List<Role> findAll() { return roleRepo.findAll(); }

  public int delete(Integer id) { return roleRepo.markDeleted(id); }
}
