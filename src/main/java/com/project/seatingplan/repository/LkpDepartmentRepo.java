package com.project.seatingplan.repository;

import com.project.seatingplan.model.LkpDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LkpDepartmentRepo extends JpaRepository<LkpDepartment, Integer> {
  @Query("select e from LkpDepartment e where e.isDeleted = 0 and e.id=:id")
  LkpDepartment findByLkpDepartmentId(@Param("id") Integer id);

  @Query("select e from LkpDepartment e where e.isDeleted = 0")
  List<LkpDepartment> findAll();
}
