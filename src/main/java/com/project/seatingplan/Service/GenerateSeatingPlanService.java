package com.project.seatingplan.Service;

import com.project.seatingplan.dto.request.web.SeatingPlanBatchReq;
import com.project.seatingplan.dto.request.web.SeatingPlanGenerateReq;
import com.project.seatingplan.model.Student;
import com.project.seatingplan.repository.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

@Service
@Transactional
public class GenerateSeatingPlanService {
  private static final Logger logger = LoggerFactory.getLogger(GenerateSeatingPlanService.class);

  private final InstituteRepo instituteRepo;
  private final DepartmentRepo departmentRepo;
  private final CourseRepo courseRepo;
  private final StudentRepo studentRepo;
  private final BatchSectionStudentRepo batchSectionStudentRepo;

  public GenerateSeatingPlanService(InstituteRepo instituteRepo, DepartmentRepo departmentRepo, CourseRepo courseRepo, StudentRepo studentRepo, BatchSectionStudentRepo batchSectionStudentRepo) {
    this.instituteRepo = instituteRepo;
    this.departmentRepo = departmentRepo;
    this.courseRepo = courseRepo;
    this.studentRepo = studentRepo;
    this.batchSectionStudentRepo = batchSectionStudentRepo;
  }

  public List<Map<String, Object>> createSeatingPlan(SeatingPlanGenerateReq seatingPlanGenerateReq, MultipartFile inputFile) throws Exception {
    int totalStudent = 0;
    int roomCapacity = 0;

    List<SeatingPlanBatchReq> seatingPlanBatchReqList = seatingPlanGenerateReq.getBatchInfoViewArray();
    logger.info("Generate seating plan to total no of batch: {}", seatingPlanBatchReqList.size());

    int totalSubject = seatingPlanBatchReqList.size();
    List<Map<String, Object>> dataList = readFile(inputFile);
    logger.info("Student details at row 1: {}", dataList.get(1));

    List<List<Map<String, Object>>> studentListList = new ArrayList<>();

    for (SeatingPlanBatchReq seatingPlanBatchReq : seatingPlanBatchReqList) {
      logger.info("Fetching student for subject code: {}", seatingPlanBatchReq.getSubjectCode());

      List<Map<String, Object>> studentList = new ArrayList<>();

      for (Map<String, Object>map : dataList) {
        String subjectCode = (String) map.get("subjectCode");
        if(subjectCode.equalsIgnoreCase(seatingPlanBatchReq.getSubjectCode())) {
          studentList.add(map);
        }
      }
      logger.info("Fetching student list size: {}", studentList.size());
      studentListList.add(studentList);
      totalStudent = totalStudent + studentList.size();
    }
    logger.info("Student list for subject: {}", studentListList.size());
    List<String> roomNameList = seatingPlanGenerateReq.getRoomNameArray();
    List<Integer> studentCapacityList = seatingPlanGenerateReq.getStudentCountArray();
    logger.info("Total number of rooms: {}", roomNameList.size());

    for (Integer integer : studentCapacityList) {
      roomCapacity = roomCapacity + integer;
    }
    if (roomCapacity < totalStudent) {
      throw new Exception("Total room capacity for seating is less than total allowable students.");
    }
    List<Map<String, Object>> seatingPlanList = new ArrayList<>();

    for (int i= 0; i < roomNameList.size(); i++) {
      Integer studentCapacityCount = studentCapacityList.get(i);
      int remainder = studentCapacityCount % totalStudent;
      Integer studentPerBatch = (studentCapacityCount - remainder) / totalSubject;

      for (int j=0; j < totalSubject; j++) {
        int count = 0;
        Map<String, Object> map = new HashMap<>();
        map.put("roomNo", roomNameList.get(i));
        map.put("departmentName", seatingPlanBatchReqList.get(j).getDepartmentName());
        map.put("courseName", seatingPlanBatchReqList.get(j).getCourseName());
        map.put("subjectCode", seatingPlanBatchReqList.get(j).getSubjectCode());
        map.put("subjectName", seatingPlanBatchReqList.get(j).getSubjectName());
        map.put("roomCapacity", String.valueOf(studentCapacityList));

        StringBuilder sb = new StringBuilder();

        boolean firstRollnumber = true;

        List<Map<String, Object>> list = studentListList.get(j);
        for (Iterator<Map<String, Object>> iterator = list.iterator(); iterator.hasNext();) {
          if (count == studentPerBatch) {
            break;
          }
          Map<String, Object> next = iterator.next();
          String rollNumber = (String) next.get("rollNumber");
          if (firstRollnumber) {
            sb.append(rollNumber);
            firstRollnumber = false;
          } else {
            sb.append(", ");
            sb.append(rollNumber.substring(Math.max(rollNumber.length() - 8, 0)));
          }
          count++;
          iterator.remove();
        }
        map.put("studentCount", String.valueOf(count));
        map.put("seating", sb.toString());
        seatingPlanList.add(map);
      }
    }
    logger.info("Final output:{}", seatingPlanList);
    return seatingPlanList;
  }

  public List<Map<String, Object>> readFile(MultipartFile file) throws IOException {
    logger.info("Read file start");

    List<Map<String, Object>> studentList = new ArrayList<>();

    try (XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream())) {
      XSSFSheet sheet = wb.getSheetAt(0);
      XSSFRow row;
      XSSFCell cell;
      Iterator<Row> rows = sheet.rowIterator();
      Student student = null;

      while (rows.hasNext()) {
        row = (XSSFRow) rows.next();
        if (row.getRowNum() == 0) {continue;}
        Map<String, Object> map = new HashMap<>();
        Iterator<Cell> cells = row.cellIterator();

        student = new Student();
        student.setCreatedDate(Date.from(Instant.now()));
        student.setIsDeleted(0);

        while (cells.hasNext()) {
          cell = (XSSFCell) cells.next();
          if (cell.getCellType() == CellType.STRING) {
            if (0 == cell.getColumnIndex()) {
              if (cell.getCellType() == CellType.STRING) {
                map.put("id", cell.getStringCellValue());
              }
              if (cell.getCellType() == CellType.NUMERIC) {
                map.put("id", cell.getNumericCellValue());
              }
            } else if (2 == cell.getColumnIndex()) {
              map.put("rollNumber", cell.getStringCellValue());
            } else if (3 == cell.getColumnIndex()) {
              map.put("subjectCode", cell.getStringCellValue());
            }
          }
        }
        studentList.add(map);
      }
    }
    logger.info("Read file end");
    return studentList;
  }
}
