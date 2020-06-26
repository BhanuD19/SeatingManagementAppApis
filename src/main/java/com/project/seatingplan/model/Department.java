package com.project.seatingplan.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "department")
public class Department implements Serializable {
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

  //bi-directional many-to-one association to Course
  @OneToMany(mappedBy = "department")
  private List<Course> courses;

  //bi-directional many-to-one association to Institute
  @ManyToOne
  private Institute institute;

  //bi-directional many-to-one association to LkpDepartment
  @ManyToOne
  @JoinColumn(name = "lkp_department_id")
  private LkpDepartment lkpDepartment;

  public Department() {
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

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }

  public Institute getInstitute() {
    return institute;
  }

  public void setInstitute(Institute institute) {
    this.institute = institute;
  }

  public LkpDepartment getLkpDepartment() {
    return lkpDepartment;
  }

  public void setLkpDepartment(LkpDepartment lkpDepartment) {
    this.lkpDepartment = lkpDepartment;
  }

  public Course addCourse(Course course) {
    getCourses().add(course);
    course.setDepartment(this);

    return course;
  }

  public Course removeCourse(Course course) {
    getCourses().remove(course);
    course.setDepartment(null);

    return course;
  }
}
