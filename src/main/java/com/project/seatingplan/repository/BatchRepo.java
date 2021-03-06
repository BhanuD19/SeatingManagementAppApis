package com.project.seatingplan.repository;

import com.project.seatingplan.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepo extends JpaRepository<Batch, Integer> {

  @Modifying
  @Query("update Batch e set e.isDeleted = 1 where e.id = :id")
  int markDeleted(@Param("id") Integer id);

  @Query("select e from Batch e where e.isDeleted = 0 and e.course.department.institute.id = :instituteId and e.course.id = :courseId")
  List<Batch> findByCourseId(@Param("instituteId") Integer instituteId, @Param("courseId") Integer courseId);
}
