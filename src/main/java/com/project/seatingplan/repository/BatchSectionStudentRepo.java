package com.project.seatingplan.repository;

import com.project.seatingplan.model.BatchSectionStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BatchSectionStudentRepo extends JpaRepository<BatchSectionStudent, Integer> {
  @Query("select e from BatchSectionStudent e where e.isDeleted = 0 and e.id = :id")
  BatchSectionStudent findByBatchSectionStudentId(@Param("id") Integer id);

  @Query("select e from BatchSectionStudent e where e.isDeleted = 0 and e.batch.course.department.institute.id = :instituteId and e.batch.course.id = :courseId")
  List<BatchSectionStudent> findByCourseId(@Param("instituteId") Integer instituteId, @Param("courseId") Integer courseId);

  @Query("select e from BatchSectionStudent e where e.isDeleted = 0 and e.batch.course.department.institue.id = :institueId and e.batch.id = :batchId")
  List<BatchSectionStudent> findByBatchIdByInstituteId(@Param("instituteId") Integer instituteId, @Param("batchId") Integer batchId);

  @Query("select e from BatchSectionStudent e where e.isDeleted = 0 and e.batch.id = :batchId")
  List<BatchSectionStudent> findByBatchId(@Param("batchId") Integer batchId);
}
