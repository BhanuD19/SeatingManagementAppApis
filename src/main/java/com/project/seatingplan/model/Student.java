package com.project.seatingplan.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
public class Student implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "contact_one")
  private String contactOne;

  @Column(name = "contact_two")
  private String contactTwo;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_date")
  private Date createdDate;

  @Temporal(TemporalType.DATE)
  private Date dob;

  private String email;

  @Column(name = "enrollment_no")
  private String enrollmentNo;

  @Column(name = "father_name")
  private String fatherName;

  @Column(name = "first_name")
  private String firstName;

  private String gender;

  @Column(name = "is_deleted")
  private int isDeleted;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "mother_name")
  private String motherName;

  @Column(name = "roll_number")
  private String rollNumber;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_date")
  private Date updatedDate;

  //bi-directional many-to-one association to BatchSectionStudent
  @OneToMany(mappedBy = "student")
  private List<BatchSectionStudent> batchSectionStudents;

  public Student() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getContactOne() {
    return contactOne;
  }

  public void setContactOne(String contactOne) {
    this.contactOne = contactOne;
  }

  public String getContactTwo() {
    return contactTwo;
  }

  public void setContactTwo(String contactTwo) {
    this.contactTwo = contactTwo;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEnrollmentNo() {
    return enrollmentNo;
  }

  public void setEnrollmentNo(String enrollmentNo) {
    this.enrollmentNo = enrollmentNo;
  }

  public String getFatherName() {
    return fatherName;
  }

  public void setFatherName(String fatherName) {
    this.fatherName = fatherName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public int getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(int isDeleted) {
    this.isDeleted = isDeleted;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMotherName() {
    return motherName;
  }

  public void setMotherName(String motherName) {
    this.motherName = motherName;
  }

  public String getRollNumber() {
    return rollNumber;
  }

  public void setRollNumber(String rollNumber) {
    this.rollNumber = rollNumber;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public List<BatchSectionStudent> getBatchSectionStudents() {
    return batchSectionStudents;
  }

  public void setBatchSectionStudents(List<BatchSectionStudent> batchSectionStudents) {
    this.batchSectionStudents = batchSectionStudents;
  }

  public BatchSectionStudent addBatchSectionStudent(BatchSectionStudent batchSectionStudent) {
    getBatchSectionStudents().add(batchSectionStudent);
    batchSectionStudent.setStudent(this);

    return batchSectionStudent;
  }

  public BatchSectionStudent removeBatchSectionStudent(BatchSectionStudent batchSectionStudent) {
    getBatchSectionStudents().remove(batchSectionStudent);
    batchSectionStudent.setStudent(null);

    return batchSectionStudent;
  }
}
