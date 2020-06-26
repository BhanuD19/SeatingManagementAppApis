package com.project.seatingplan.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "course")
public class Course implements Serializable {
  private static final long serialVersionUID = 1L;

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

  //bi-directional many-to-one association to Batch
  @OneToMany(mappedBy = "course")
  private List<Batch> batches;

  //bi-directional many-to-one association to Department
  @ManyToOne
  private Department department;

  //bi-directional many-to-one association to LkpCourse
  @ManyToOne
  @JoinColumn(name = "lkp_course_id")
  private LkpCourse lkpCourse;

  //bi-directional many-to-one association to Subject
  @OneToMany(mappedBy = "course")
  private List<Subject> subjects;

  public Course() {
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

  public List<Batch> getBatches() {
    return batches;
  }

  public void setBatches(List<Batch> batches) {
    this.batches = batches;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public LkpCourse getLkpCourse() {
    return lkpCourse;
  }

  public void setLkpCourse(LkpCourse lkpCourse) {
    this.lkpCourse = lkpCourse;
  }

  public List<Subject> getSubjects() {
    return subjects;
  }

  public void setSubjects(List<Subject> subjects) {
    this.subjects = subjects;
  }

  public Batch addBatch(Batch batch) {
    getBatches().add(batch);
    batch.setCourse(this);

    return batch;
  }

  public Batch removeBatch(Batch batch) {
    getBatches().remove(batch);
    batch.setCourse(null);

    return batch;
  }

  public Subject addSubject(Subject subject) {
    getSubjects().add(subject);
    subject.setCourse(this);

    return subject;
  }

  public Subject removeSubject(Subject subject) {
    getSubjects().remove(subject);
    subject.setCourse(null);

    return subject;
  }
}
