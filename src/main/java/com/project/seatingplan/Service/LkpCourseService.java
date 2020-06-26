package com.project.seatingplan.Service;

import com.project.seatingplan.model.LkpCourse;
import com.project.seatingplan.repository.LkpCourseRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class LkpCourseService {
  private static final Logger logger = LoggerFactory.getLogger(LkpCourseService.class);

  private final LkpCourseRepo lkpCourseRepo;

  public LkpCourseService(LkpCourseRepo lkpCourseRepo) {
    this.lkpCourseRepo = lkpCourseRepo;
  }

  public List<LkpCourse> findAll() {
    return lkpCourseRepo.findAll();
  }
}
