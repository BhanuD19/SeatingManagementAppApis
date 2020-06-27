package com.project.seatingplan.repository;

import com.project.seatingplan.model.LkpCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LkpCourseRepo extends JpaRepository<LkpCourse, Integer> {
  @Query("select e from LkpCourse e where e.isDeleted = 0 and e.id=:id")
  List<LkpCourse> findAllById(@Param("id") Integer id);

  @Query("select e from LkpCourse e where e.isDeleted = 0 and e.id=:id")
  LkpCourse findByLkpCourseId(@Param("id") Integer id);

  @Query("select e from LkpCourse e where e.isDeleted = 0 and e.name=:name")
  List<LkpCourse> findAllByName(@Param("name") String name);

  @Query("select e from LkpCourse e where e.isDeleted = 0 and e.name=:code")
  List<LkpCourse> findAllByCode(@Param("code") String code);

}
