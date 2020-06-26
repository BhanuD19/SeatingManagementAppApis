package com.project.seatingplan.repository;

import com.project.seatingplan.model.LkpSemester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LkpSemesterRepo extends JpaRepository<LkpSemester, Integer> {

  @Query("select e from LkpSemester e where e.isDeleted = 0")
  List<LkpSemester> findAll();
}
