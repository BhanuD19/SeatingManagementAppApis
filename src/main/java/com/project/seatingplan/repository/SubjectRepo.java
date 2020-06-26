package com.project.seatingplan.repository;

import com.project.seatingplan.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Integer> {
  @Query("select e from Subject e where e.isDeleted = 0 and e.lkpSemester.id=:lkpSemesterId and e.course.id=:courseId")
  List<Subject> findByCourseIdSemesterId(@Param("lkpSemesterId") Integer lkpSemesterId, @Param("courseId") Integer courseId);


}
