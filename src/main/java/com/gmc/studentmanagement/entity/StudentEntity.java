package com.gmc.studentmanagement.entity;


import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a Student Entity with their details and marks.
 *
 * @author Girish Chandra Patra
 */
@Entity
@Table(name = "student", schema = "gmc_studentmanagement")
public class StudentEntity {

  @Id
  @Column(name = "stud_id")
  private String studentId;

  // First Name of the student
  @Column(name = "first_nm")
  private String firstName;

  // Last Name of the student
  @Column(name = "last_nm")
  private String lastName;

  // Date of Birth of the student
  @Column(name = "dob")
  private LocalDate dob;

  // Section in which the student belongs (A, B, C)
  @Column(name = "sec")
  private String section;

  // Gender of the student (M, F, O)
  @Column(name = "gender")
  private String gender;

  // Marks for subject 1
  @Column(name = "marks1")
  private Long marks1;

  // Marks for subject 2
  @Column(name = "marks2")
  private Long marks2;

  // Marks for subject 3
  @Column(name = "marks3")
  private Long marks3;

  // Total marks obtained by the student
  @Column(name = "total_mrk")
  private Long totalMark;

  // Average marks of the student
  @Column(name = "average")
  private Long average;

  // Result of the student (Pass/Fail)
  @Column(name = "result")
  private String result;

  public StudentEntity() {
    //No param constructor
  }

  /**
   * Creates a StudentEntity object with provided details.
   *
   * @param studentId Unique identifier for the student
   * @param firstName First name of the student
   * @param lastName  Last name of the student
   * @param dob       Date of Birth of the student
   * @param section   Section in which the student belongs
   * @param gender    Gender of the student
   * @param marks1    Marks for subject 1
   * @param marks2    Marks for subject 2
   * @param marks3    Marks for subject 3
   * @param totalMark Total marks obtained by the student
   * @param average   Average marks of the student
   * @param result    Result of the student (Pass/Fail)
   */
  public StudentEntity(String studentId, String firstName, String lastName, LocalDate dob,
      String section, String gender, Long marks1, Long marks2, Long marks3, Long totalMark,
      Long average, String result) {
    this.studentId = studentId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dob = dob;
    this.section = section;
    this.gender = gender;
    this.marks1 = marks1;
    this.marks2 = marks2;
    this.marks3 = marks3;
    this.totalMark = totalMark;
    this.average = average;
    this.result = result;
  }

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }

  public String getSection() {
    return section;
  }

  public void setSection(String section) {
    this.section = section;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Long getMarks1() {
    return marks1;
  }

  public void setMarks1(Long marks1) {
    this.marks1 = marks1;
  }

  public Long getMarks2() {
    return marks2;
  }

  public void setMarks2(Long marks2) {
    this.marks2 = marks2;
  }

  public Long getMarks3() {
    return marks3;
  }

  public void setMarks3(Long marks3) {
    this.marks3 = marks3;
  }

  public Long getTotalMark() {
    return totalMark;
  }

  public void setTotalMark(Long totalMark) {
    this.totalMark = totalMark;
  }

  public Long getAverage() {
    return average;
  }

  public void setAverage(Long average) {
    this.average = average;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  /**
   * Returns a formatted string representation of the StudentEntity.
   *
   * @return String representation of the StudentEntity
   */
  @Override
  public String toString() {
    return "StudentEntity{" +
        "studentId='" + studentId + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", dob=" + dob +
        ", section='" + section + '\'' +
        ", gender='" + gender + '\'' +
        ", marks1=" + marks1 +
        ", marks2=" + marks2 +
        ", marks3=" + marks3 +
        ", totalMark=" + totalMark +
        ", average=" + average +
        ", result='" + result + '\'' +
        '}';
  }
}
