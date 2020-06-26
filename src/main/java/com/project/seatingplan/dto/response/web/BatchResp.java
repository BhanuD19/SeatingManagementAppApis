package com.project.seatingplan.dto.response.web;

import java.util.Date;

public class BatchResp {
  private int id;
  private String name;
  private String code;
  private String description;
  private Date startYear;
  private Date endYear;
  private int courseId;
  private String courseName;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getStartYear() {
    return startYear;
  }

  public void setStartYear(Date startYear) {
    this.startYear = startYear;
  }

  public Date getEndYear() {
    return endYear;
  }

  public void setEndYear(Date endYear) {
    this.endYear = endYear;
  }

  public int getCourseId() {
    return courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }
}
