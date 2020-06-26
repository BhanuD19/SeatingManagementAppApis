package com.project.seatingplan.repository;

import com.project.seatingplan.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer> {
  @Modifying
  @Query("update Department  e set e.isDeleted = 1 where e.id = :id")
  int markDeleted(@Param("id") Integer id);

  @Query("select e from Department e where e.isDeleted = 0 and e.id = :id")
  Optional<Department> findById(@Param("id") Integer id);

  @Query("select e from Department e where e.isDeleted = 0 and e.institute.id = :instituteId")
  List<Department> findByInstituteId(@Param("instituteId") Integer instituteId);

}
