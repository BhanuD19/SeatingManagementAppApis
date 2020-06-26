package com.project.seatingplan.Service;

import com.project.seatingplan.dto.request.web.CourseReq;
import com.project.seatingplan.model.Course;
import com.project.seatingplan.repository.CourseRepo;
import com.project.seatingplan.repository.DepartmentRepo;
import com.project.seatingplan.repository.LkpCourseRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class CourseService {
  private final CourseRepo courseRepo;
  private final DepartmentRepo departmentRepo;
  private final LkpCourseRepo lkpCourseRepo;

  public CourseService(CourseRepo courseRepo, DepartmentRepo departmentRepo, LkpCourseRepo lkpCourseRepo) {
    this.courseRepo = courseRepo;
    this.departmentRepo = departmentRepo;
    this.lkpCourseRepo = lkpCourseRepo;
  }

  public String save(CourseReq courseReq) throws Exception {
    Course course = new Course();
    course.setDepartment(departmentRepo.getOne(courseReq.getDepartmentId()));
    course.setLkpCourse(lkpCourseRepo.findByLkpCourseId(courseReq.getLkpCourseId()));
    course.setCreatedDate(Date.from(Instant.now()));
    course.setIsDeleted(0);

    Course saveCourse = courseRepo.save(course);

    if (saveCourse != null) {
      return "Course saved successfully";
    }
    return "Failed to save course";
  }

  public List<Course> findByDepartment(Integer instituteId, Integer departmentId) {
    List<Course> courseList = courseRepo.findByDepartment(instituteId, departmentId);
    return courseList;
  }

  public int deleteCourse(Integer id) { return courseRepo.markDeleted(id); }
}
