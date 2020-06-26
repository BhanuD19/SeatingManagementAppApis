package com.project.seatingplan.repository;

import com.project.seatingplan.model.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstituteRepo extends JpaRepository<Institute, Integer> {
  @Query("select e from Institute e where e.isDeleted = 0 and e.id =:id")
  Optional<Institute> findById(@Param("id") Integer id);

  @Query("select e from Institute e where e.isDeleted = 0 and e.alias =:alias")
  List<Institute> findByAlias(@Param("alias") String alias);


  @Query("select e from Institute e Join UserInstitute ui on e.id = ui.institute.id where e.isDeleted = 0 and ui.user.id=:userId")
  List<Institute> findByUserId(@Param("userId") Integer userId);

  @Modifying
  @Query("update Institute e set e.isDeleted = 1 where e.id = :id")
  int markDeleted(@Param("id") Integer id);

}
