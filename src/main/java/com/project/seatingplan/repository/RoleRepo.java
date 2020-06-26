package com.project.seatingplan.repository;

import com.project.seatingplan.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
  @Modifying
  @Query("update Role e set e.isDeleted = 1 where e.id = :id")
  int markDeleted(@Param("id") Integer id);

  @Query("select e from Role e where e.isDeleted = 0 and e.id = :id")
  Role findByRoleId(@Param("id") Integer id);

  @Query("select e from Role e where e.isDeleted = 0 and e.name = :name")
  Role findByName(@Param("name") String name);

}
