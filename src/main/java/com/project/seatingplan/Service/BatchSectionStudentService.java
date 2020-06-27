package com.project.seatingplan.Service;

import com.project.seatingplan.model.Batch;
import com.project.seatingplan.model.BatchSectionStudent;
import com.project.seatingplan.model.LkpSemester;
import com.project.seatingplan.model.Student;
import com.project.seatingplan.repository.BatchRepo;
import com.project.seatingplan.repository.BatchSectionStudentRepo;
import com.project.seatingplan.repository.LkpSemesterRepo;
import com.project.seatingplan.repository.StudentRepo;
import org.apache.commons.io.FilenameUtils;
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
import java.io.InputStream;
import java.time.Instant;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class BatchSectionStudentService {

  private final static Logger logger = LoggerFactory.getLogger(BatchSectionStudentService.class);
  private final BatchSectionStudentRepo batchSectionStudentRepo;
  private final StudentRepo studentRepo;
  private final BatchRepo batchRepo;
  private final LkpSemesterRepo lkpSemesterRepo;

  public BatchSectionStudentService(BatchSectionStudentRepo batchSectionStudentRepo, StudentRepo studentRepo, BatchRepo batchRepo, LkpSemesterRepo lkpSemesterRepo) {
    this.batchSectionStudentRepo = batchSectionStudentRepo;
    this.studentRepo = studentRepo;
    this.batchRepo = batchRepo;
    this.lkpSemesterRepo = lkpSemesterRepo;
  }

  public String save(Integer batchId, Integer lkpSemesterId, MultipartFile file) throws IOException {
    String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
    logger.info("File extension:{}", fileExtension);

    InputStream fileInputStream = file.getInputStream();

    try (XSSFWorkbook wb = new XSSFWorkbook(fileInputStream)) {
      XSSFSheet sheet = wb.getSheetAt(0);
      XSSFRow row;
      XSSFCell cell;
      Iterator<Row> rows = sheet.rowIterator();
      Student student = null;

      while (rows.hasNext()) {
        row = (XSSFRow) rows.next();
        if (row.getRowNum() == 0) {continue;}
        Iterator<Cell> cells = row.cellIterator();
        student = new Student();
        student.setCreatedDate(Date.from(Instant.now()));
        student.setIsDeleted(0);

        while (cells.hasNext()) {
          cell = (XSSFCell) cells.next();
          if (cell.getCellType() == CellType.STRING) {
            if (0 == cell.getColumnIndex()) {

            }
            else if (1 == cell.getColumnIndex()) {
              student.setFirstName(cell.getStringCellValue());
            }
            else if (2 == cell.getColumnIndex()) {
              student.setLastName(cell.getStringCellValue());
            }
            else if (3 == cell.getColumnIndex()) {
              student.setMotherName(cell.getStringCellValue());
            }
            else if (4 == cell.getColumnIndex()) {
              student.setFatherName(cell.getStringCellValue());
            }
            else if (5 == cell.getColumnIndex()) {
              student.setEmail(cell.getStringCellValue());
            }
            else if (6 == cell.getColumnIndex()) {
              if (cell.getCellType() == CellType.STRING) {
                student.setContactOne(cell.getStringCellValue());
              }
              if(cell.getCellType() == CellType.NUMERIC) {
                student.setContactOne(String.valueOf(cell.getNumericCellValue()));
              }
              System.out.println(cell.getNumericCellValue());
            }
            else if (7 == cell.getColumnIndex()) {
              if(cell.getCellType() == CellType.STRING) {
                student.setRollNumber(cell.getStringCellValue());
              }
              if (cell.getCellType() == CellType.NUMERIC) {
                student.setRollNumber(String.valueOf(cell.getNumericCellValue()));
              }
            }
            else if (8 == cell.getColumnIndex()) {
              if(cell.getCellType() == CellType.STRING) {
                student.setEnrollmentNo(cell.getStringCellValue());
              }
              if (cell.getCellType() == CellType.NUMERIC) {
                student.setEnrollmentNo(String.valueOf(cell.getNumericCellValue()));
              }
            }
          }
        }
        logger.info("firstName:{}, lastName:{}, email:{}, contactOne:{}, rollNo:{}, enrollmentNo:{}", student.getFirstName(), student.getLastName(), student.getEmail(),
          student.getContactOne(), student.getRollNumber(), student.getEnrollmentNo());

        logger.info("Fetching batch entity for lkp-semester-id:{}", lkpSemesterId);

        LkpSemester lkpSemester = lkpSemesterRepo.getOne(lkpSemesterId);

        logger.info("Fetching batch entity for batch-id:{}", batchId);
        Batch batch = batchRepo.getOne(batchId);

        Student savedStudent = studentRepo.save(student);

        BatchSectionStudent batchSectionStudent = new BatchSectionStudent();
        batchSectionStudent.setLkpSemester(lkpSemester);
        batchSectionStudent.setBatch(batch);
        batchSectionStudent.setStudent(savedStudent);

        batchSectionStudent.setCreatedDate(Date.from(Instant.now()));
        batchSectionStudent.setIsDeleted(0);

        batchSectionStudentRepo.save(batchSectionStudent);
      }
    }
    return "Bulk Student upload successful.";
  }

  @Transactional(readOnly = true)
  public List<Student> findByBatchId(Integer batchId) {
    List<Student> studentList = studentRepo.findByStudent(batchId);
    return studentList;
  }
}
