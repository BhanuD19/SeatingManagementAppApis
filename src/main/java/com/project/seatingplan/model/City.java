package com.project.seatingplan.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "city")
public class City implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_date")
  private Date createdDate;

  @Column(name = "is_deleted")
  private int isDeleted;

  private String name;

  private int pincode;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_date")
  private Date updatedDate;

  @OneToMany(mappedBy = "city")
  private List<Institute> institutes;

  @OneToMany(mappedBy = "city")
  private List<User> users;

  public City() {
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPincode() {
    return pincode;
  }

  public void setPincode(int pincode) {
    this.pincode = pincode;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public List<Institute> getInstitutes() {
    return institutes;
  }

  public void setInstitutes(List<Institute> institutes) {
    this.institutes = institutes;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public Institute addInstitute(Institute institute) {
    getInstitutes().add(institute);
    institute.setCity(this);

    return institute;
  }

  public Institute removeInstitute(Institute institute) {
    getInstitutes().remove(institute);
    institute.setCity(null);

    return institute;
  }

  public User addUser(User user) {
    getUsers().add(user);
    user.setCity(this);

    return user;
  }

  public User removeUser(User user) {
    getUsers().remove(user);
    user.setCity(null);

    return user;
  }
}
