package com.project.seatingplan.repository;

import com.project.seatingplan.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
  @Query("select e from Student e join BatchSectionStudent bss on e.id = bss.student.id where bss.batch.id=:batchId")
  List<Student> findByStudent(@Param("batchId") Integer batchId);
}
