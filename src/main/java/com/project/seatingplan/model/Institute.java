package com.project.seatingplan.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "institute")
public class Institute implements Serializable {
  private static final Long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String address;

  private String alias;

  private String code;

  @Column(name = "contact_one")
  private String contactOne;

  @Column(name = "contact_two")
  private String contactTwo;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_date")
  private Date createdDate;

  private String email;

  @Column(name = "is_deleted")
  private int isDeleted;

  private String logo;

  private String name;

  @Column(name = "registration_no")
  private String registrationNo;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_date")
  private Date updatedDate;

  private String website;

  //bi-directional many-to-one association to Department
  @OneToMany(mappedBy = "institute")
  private List<Department> departments;

  //bi-directional many-to-one association to City
  @ManyToOne
  private City city;

  //bi-directional many-to-one association to UserInstitute
  @OneToMany(mappedBy = "institute")
  private List<UserInstitute> userInstitutes;

  public Institute() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(int isDeleted) {
    this.isDeleted = isDeleted;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRegistrationNo() {
    return registrationNo;
  }

  public void setRegistrationNo(String registrationNo) {
    this.registrationNo = registrationNo;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public List<Department> getDepartments() {
    return departments;
  }

  public void setDepartments(List<Department> departments) {
    this.departments = departments;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public List<UserInstitute> getUserInstitutes() {
    return userInstitutes;
  }

  public void setUserInstitutes(List<UserInstitute> userInstitutes) {
    this.userInstitutes = userInstitutes;
  }

  public Department addDepartment(Department department) {
    getDepartments().add(department);
    department.setInstitute(this);

    return department;
  }

  public Department removeDepartment(Department department) {
    getDepartments().remove(department);
    department.setInstitute(null);

    return department;
  }

  public UserInstitute addUserInstitute(UserInstitute userInstitute) {
    getUserInstitutes().add(userInstitute);
    userInstitute.setInstitute(this);

    return userInstitute;
  }

  public UserInstitute removeUserInstitute(UserInstitute userInstitute) {
    getUserInstitutes().remove(userInstitute);
    userInstitute.setInstitute(null);

    return userInstitute;
  }

}
