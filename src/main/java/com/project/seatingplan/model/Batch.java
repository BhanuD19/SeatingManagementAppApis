package com.project.seatingplan.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "batch")
public class Batch implements Serializable {
  private static final Long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String code;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_date")
  private Date createdDate;

  private String description;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "end_year")
  private Date endYear;

  @Column(name = "is_deleted")
  private int isDeleted;

  private String name;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "start_year")
  private Date startYear;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_date")
  private Date updatedDate;

  @ManyToOne
  private Course course;

  @OneToMany(mappedBy = "batch")
  private List<BatchSectionStudent> batchSectionStudents;

  public Batch() {
  }

  public int getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(int isDeleted) {
    this.isDeleted = isDeleted;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getEndYear() {
    return endYear;
  }

  public void setEndYear(Date endYear) {
    this.endYear = endYear;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getStartYear() {
    return startYear;
  }

  public void setStartYear(Date startYear) {
    this.startYear = startYear;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public List<BatchSectionStudent> getBatchSectionStudents() {
    return batchSectionStudents;
  }

  public void setBatchSectionStudents(List<BatchSectionStudent> batchSectionStudents) {
    this.batchSectionStudents = batchSectionStudents;
  }

  public BatchSectionStudent addBatchSectionStudents(BatchSectionStudent batchSectionStudent) {
    getBatchSectionStudents().add(batchSectionStudent);
    batchSectionStudent.setBatch(this);

    return batchSectionStudent;
  }

  public BatchSectionStudent removeBatchSectionStudents(BatchSectionStudent batchSectionStudent) {
    getBatchSectionStudents().remove(batchSectionStudent);
    batchSectionStudent.setBatch(null);

    return batchSectionStudent;
  }

}
