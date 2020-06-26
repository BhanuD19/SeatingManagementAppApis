package com.project.seatingplan.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "batch_section_student")
public class BatchSectionStudent implements Serializable {
  private static final Long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_date")
  private Date createdDate;

  @Column(name = "is_deleted")
  private int isDeleted;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_date")
  private Date updatedDate;

  @ManyToOne
  private Batch batch;

  @ManyToOne
  @JoinColumn(name = "lkp_section_id")
  private LkpSection lkpSection;

  @ManyToOne
  @JoinColumn(name = "lkp_semester_id")
  private LkpSemester lkpSemester;

  @ManyToOne
  private Student student;

  public BatchSectionStudent() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public int getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(int isDeleted) {
    this.isDeleted = isDeleted;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public Batch getBatch() {
    return batch;
  }

  public void setBatch(Batch batch) {
    this.batch = batch;
  }

  public LkpSection getLkpSection() {
    return lkpSection;
  }

  public void setLkpSection(LkpSection lkpSection) {
    this.lkpSection = lkpSection;
  }

  public LkpSemester getLkpSemester() {
    return lkpSemester;
  }

  public void setLkpSemester(LkpSemester lkpSemester) {
    this.lkpSemester = lkpSemester;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }
}
