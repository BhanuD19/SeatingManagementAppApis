package com.project.seatingplan.Service;

import com.project.seatingplan.dto.request.web.SubjectReq;
import com.project.seatingplan.dto.response.web.SubjectResp;
import com.project.seatingplan.model.Course;
import com.project.seatingplan.model.LkpSemester;
import com.project.seatingplan.model.Subject;
import com.project.seatingplan.repository.BatchRepo;
import com.project.seatingplan.repository.CourseRepo;
import com.project.seatingplan.repository.LkpSemesterRepo;
import com.project.seatingplan.repository.SubjectRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class SubjectService {
  private static final Logger logger = LoggerFactory.getLogger(SubjectService.class);

  private final BatchRepo batchRepo;
  private final CourseRepo courseRepo;
  private final LkpSemesterRepo lkpSemesterRepo;
  private final SubjectRepo subjectRepo;

  public SubjectService(BatchRepo batchRepo, CourseRepo courseRepo, LkpSemesterRepo lkpSemesterRepo, SubjectRepo subjectRepo) {
    this.batchRepo = batchRepo;
    this.courseRepo = courseRepo;
    this.lkpSemesterRepo = lkpSemesterRepo;
    this.subjectRepo = subjectRepo;
  }

  public Subject save(SubjectReq subjectReq) throws Exception {
    logger.info("Fetching entity for permanent course-id:{}", subjectReq.getCourseId());
    Course course = courseRepo.getOne(subjectReq.getCourseId());
    logger.info("Fetched entity for permanent course-id:{}", course.getId());

    logger.info("Fetching entity for permanent lkp-semester-id:{}", subjectReq.getLkpSemesterId());
    LkpSemester lkpSemester = lkpSemesterRepo.getOne(subjectReq.getLkpSemesterId());
    logger.info("Fetched entity for permanent lkp-semester-id:{}", lkpSemester.getId());

    Subject subject = new Subject();

    subject.setCourse(course);
    subject.setLkpSemester(lkpSemester);
    subject.setName(subjectReq.getName());
    subject.setCode(subjectReq.getCode());
    subject.setCreatedDate(Date.from(Instant.now()));

    return subjectRepo.save(subject);
  }

  public List<SubjectResp> findByCourseSemesterId(Integer lkpSemesterId, Integer courseId) {
    List<Subject> semesterList = subjectRepo.findByCourseIdSemesterId(lkpSemesterId, courseId);
    List<SubjectResp> subjectRespList = new ArrayList<>();
    SubjectResp subjectResp = null;

    for (Subject subject : semesterList) {
      subjectResp = new SubjectResp();

      subjectResp.setId(subject.getId());
      subjectResp.setCode(subject.getCode());
      subjectResp.setName(subject.getName());
      subjectResp.setCourseId(subject.getCourse().getId());
      subjectResp.setCourseName(subject.getCourse().getLkpCourse().getName());
      subjectResp.setDepartmentId(subject.getCourse().getDepartment().getId());
      subjectResp.setDepartmentName(subject.getCourse().getDepartment().getLkpDepartment().getName());
      subjectResp.setSemesterName(subject.getLkpSemester().getName());
      subjectRespList.add(subjectResp);
    }
    return subjectRespList;
  }

  public int markDeleted(Integer id) { return batchRepo.markDeleted(id); }
}
