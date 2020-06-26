package com.project.seatingplan.repository;

import com.project.seatingplan.model.LkpSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LkpSectionRepo extends JpaRepository<LkpSection, Integer> {
  @Query("select e from LkpSection e where e.isDeleted = 0")
  List<LkpSection> findAll();
}
