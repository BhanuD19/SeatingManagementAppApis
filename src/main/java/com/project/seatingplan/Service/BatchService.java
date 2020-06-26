package com.project.seatingplan.Service;

import com.project.seatingplan.dto.request.web.BatchReq;
import com.project.seatingplan.dto.response.web.BatchResp;
import com.project.seatingplan.model.Batch;
import com.project.seatingplan.model.Course;
import com.project.seatingplan.repository.BatchRepo;
import com.project.seatingplan.repository.CourseRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BatchService {
  private static final Logger logger = LoggerFactory.getLogger(BatchService.class);

  private final BatchRepo batchRepo;
  private CourseRepo courseRepo;

  public BatchService(BatchRepo batchRepo, CourseRepo courseRepo) {
    this.batchRepo = batchRepo;
    this.courseRepo = courseRepo;
  }

  @Transactional
  public Batch save(BatchReq batchReq) throws Exception {
    logger.info("Fetching entity for permanent course-id: {}", batchReq.getCourseId());
    Course course = courseRepo.getOne(batchReq.getCourseId());
    logger.info("Fetched entity for permanent course-id:{}", batchReq.getCourseId());
    Batch batch = new Batch();
    batch.setCourse(course);
    batch.setName(batchReq.getName());
    batch.setCode(batchReq.getCode());
    batch.setDescription(batchReq.getDescription());
    batch.setStartYear(batchReq.getStartYear());
    batch.setEndYear(batchReq.getEndYear());
    batch.setCreatedDate(Date.from(Instant.now()));
    batch.setIsDeleted(0);

    return batchRepo.save(batch);
   }

  @Transactional
  public String update(BatchReq batchReq) throws Exception {
    logger.info("Fetching entity for permanent course-id: {}", batchReq.getCourseId());
    Course course = courseRepo.getOne(batchReq.getCourseId());
    logger.info("Fetched entity for permanent course-id:{}", batchReq.getCourseId());

    Batch ref = batchRepo.getOne(batchReq.getId());
    logger.info("fetching batch id:{}", ref.getId());
    Batch batch = new Batch();
    batch.setId(ref.getId());
    batch.setCourse(course);
    batch.setName(batchReq.getName());
    batch.setCode(batchReq.getCode());
    batch.setDescription(batchReq.getDescription());
    batch.setStartYear(batchReq.getStartYear());
    batch.setEndYear(batchReq.getEndYear());
    batch.setCreatedDate(Date.from(Instant.now()));
    batch.setIsDeleted(0);
    Batch updateBatch = batchRepo.save(batch);
    if(updateBatch != null) {
      return "Batch uploaded Successfully.";
    }
    return "Failed to update batch!!";
  }

  public List<BatchResp> findByCourseId(Integer instituteId, Integer courseId) {
    List<Batch> batchList = batchRepo.findByCourseId(instituteId, courseId);
    List<BatchResp> batchRespList = new ArrayList<>();
    BatchResp batchResp = null;

    for (Batch batch : batchList) {
      batchResp = new BatchResp();
      batchResp.setId(batch.getId());
      batchResp.setCode(batch.getCode());
      batchResp.setName(batch.getName());
      batchResp.setDescription(batch.getDescription());
      batchResp.setStartYear(batch.getStartYear());
      batchResp.setEndYear(batch.getEndYear());
      batchResp.setCourseId(batch.getCourse().getId());
      batchResp.setCourseName(batch.getCourse().getLkpCourse().getName());
      batchRespList.add(batchResp);
    }
    return batchRespList;
  }

  public int markDelete(Integer id) {
    return batchRepo.markDeleted(id);
  }
}
