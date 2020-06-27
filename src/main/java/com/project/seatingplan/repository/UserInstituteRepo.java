package com.project.seatingplan.repository;

import com.project.seatingplan.model.UserInstitute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInstituteRepo extends JpaRepository<UserInstitute, Integer> {

  @Query("select e from UserInstitute e where e.isDeleted = 0 and e.id =:id")
  UserInstitute findByUserInstituteId(@Param("id") Integer id);

  @Query("select e from UserInstitute e where e.isDeleted = 0 and e.user.id =:id")
  List<UserInstitute> findByUserId(@Param("id") Integer id);

  @Modifying
  @Query("update UserInstitute e set e.isDeleted = 1 where e.id = :id")
  int markDeleted(@Param("id") Integer id);
}
