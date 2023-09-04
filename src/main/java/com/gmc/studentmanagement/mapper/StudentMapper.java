package com.gmc.studentmanagement.mapper;

import static com.gmc.studentmanagement.constants.StudentManagementConstants.FIFTEEN;
import static com.gmc.studentmanagement.constants.StudentManagementConstants.THIRTY_FIVE;
import static com.gmc.studentmanagement.constants.StudentManagementConstants.THREE;
import static com.gmc.studentmanagement.constants.StudentManagementConstants.TWENTY;
import static com.gmc.studentmanagement.enums.ResultEnum.FAIL;
import static com.gmc.studentmanagement.enums.ResultEnum.PASS;
import static com.gmc.studentmanagement.exception.enums.ErrorCodeEnum.BAD_REQUEST;
import static com.gmc.studentmanagement.exception.enums.ErrorMessageEnum.AGE_RESTRICTION_MSG;

import com.gmc.studentmanagement.entity.StudentEntity;
import com.gmc.studentmanagement.exception.InvalidRequestException;
import com.gmc.studentmanagemet.resource.ResultReviseInput;
import com.gmc.studentmanagemet.resource.StudentData;
import com.gmc.studentmanagemet.resource.StudentRegistrationData;
import com.gmc.studentmanagemet.resource.StudentRegistrationInput;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for mapping between different data structures related to students.
 *
 * @author Girish Chandra Patra
 */
public class StudentMapper {

  private static final Logger LOGGER = LoggerFactory.getLogger(StudentMapper.class);

  /**
   * Maps student registration input data to a list of student entities.
   *
   * @param studentRegistrationInput the input data containing student registration information
   * @return a list of mapped student entities
   */
  public static List<StudentEntity> mapStudentEntities(
      StudentRegistrationInput studentRegistrationInput) {
    List<StudentEntity> studentEntityList = new ArrayList<>();

    studentRegistrationInput.getStudents().forEach(studentData -> {

      LocalDate dob = validateAge(studentData);

      StudentEntity studentEntity = new StudentEntity();
      studentEntity.setStudentId(String.valueOf(UUID.randomUUID()));
      studentEntity.setFirstName(studentData.getFirstName());
      studentEntity.setLastName(studentData.getLstName());
      studentEntity.setDob(dob);
      studentEntity.setSection(studentData.getSection().getValue());
      studentEntity.setGender(studentData.getGender().getValue());
      studentEntity.setCoreJava(Long.valueOf(studentData.getCoreJava()));
      studentEntity.setSpring(Long.valueOf(studentData.getSpring()));
      studentEntity.setCloudTechnology(Long.valueOf(studentData.getCloudTechnology()));

      studentEntity.setTotalMark(calculateTotalMark(studentEntity));
      studentEntity.setAverage(calculateAverageMark(studentEntity));
      studentEntity.setResult(calculateResult(studentEntity));

      studentEntityList.add(studentEntity);
    });
    return studentEntityList;
  }

  /**
   * Validates the age of a student.
   *
   * @param studentData the student data containing date of birth
   * @return the validated date of birth
   * @throws InvalidRequestException if age validation fails
   */
  public static LocalDate validateAge(StudentData studentData) {

    LOGGER.info("Age validation started for student name: [{}]", studentData.getFirstName());
    // Define a custom date format
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Parse the string into a LocalDate object
    LocalDate dob = LocalDate.parse(studentData.getDob(), formatter);

    LocalDate currentDate = LocalDate.now();
    Period age = Period.between(dob, currentDate);
    if (age.getYears() <= FIFTEEN || age.getYears() > TWENTY) {
      throw new InvalidRequestException(BAD_REQUEST.getErrorCode(),
          AGE_RESTRICTION_MSG.getErrorMessage());
    }
    return dob;
  }

  /**
   * Calculates the total marks for a student entity.
   *
   * @param studentEntity the student entity for which to calculate total marks
   * @return the calculated total marks
   */
  public static long calculateTotalMark(StudentEntity studentEntity) {
    LOGGER.info(
        "Calculating total mark for studentId: [{}] and student name: [{}]",
        studentEntity.getStudentId(), studentEntity.getFirstName());
    return studentEntity.getCoreJava() + studentEntity.getSpring() + studentEntity.getCloudTechnology();
  }

  /**
   * Calculates the average mark for a student entity.
   *
   * @param studentEntity the student entity for which to calculate average mark
   * @return the calculated average mark
   */
  public static long calculateAverageMark(StudentEntity studentEntity) {
    LOGGER.info(
        "Calculating average mark for studentId: [{}] and student name: [{}]",
        studentEntity.getStudentId(), studentEntity.getFirstName());

    return studentEntity.getTotalMark() / THREE;
  }

  /**
   * Calculates the result (PASS/FAIL) for a student entity.
   *
   * @param studentEntity the student entity for which to calculate the result
   * @return the calculated result
   */
  public static String calculateResult(StudentEntity studentEntity) {

    LOGGER.info(
        "Calculating result for studentId: [{}] and student name: [{}]",
        studentEntity.getStudentId(), studentEntity.getFirstName());
    String result = null;
    if (studentEntity.getCoreJava() >= THIRTY_FIVE
        && studentEntity.getSpring() >= THIRTY_FIVE
        && studentEntity.getCloudTechnology() >= THIRTY_FIVE) {

      result = PASS.name();
    } else {
      result = FAIL.name();
    }

    LOGGER.info(
        "Result calculated for studentId: [{}] and student name: [{}]. Result: [{}]",
        studentEntity.getStudentId(), studentEntity.getFirstName(), result);

    return result;
  }

  /**
   * Maps a list of student entities to a list of student registration data.
   *
   * @param studentEntities the list of student entities to be mapped
   * @return a list of mapped student registration data
   */
  public static List<StudentRegistrationData> mapStudentRegistrationData(
      List<StudentEntity> studentEntities) {
    List<StudentRegistrationData> studentRegistrationDataList = new ArrayList<>();

    studentEntities.forEach(studentEntity -> {
      StudentRegistrationData studentRegistrationData = getStudentRegistrationData(
          studentEntity);

      studentRegistrationDataList.add(studentRegistrationData);

    });
    return studentRegistrationDataList;
  }

  /**
   * Maps updated result input to a modified student entity.
   *
   * @param resultReviseInput the input containing updated result information
   * @param studentEntity       the student entity to be updated
   * @return the updated student entity
   */
  public static StudentEntity mapUpdatedResult(ResultReviseInput resultReviseInput,
      StudentEntity studentEntity) {

    LOGGER.info("Result revise started for student name: [{}] and studentId: [{}]",
        studentEntity.getFirstName(), studentEntity.getStudentId());

    studentEntity.setCoreJava(Long.valueOf(resultReviseInput.getCoreJava()));
    studentEntity.setSpring(Long.valueOf(resultReviseInput.getSpring()));
    studentEntity.setCloudTechnology(Long.valueOf(resultReviseInput.getCloudTechnology()));

    studentEntity.setTotalMark(calculateTotalMark(studentEntity));
    studentEntity.setAverage(calculateAverageMark(studentEntity));
    studentEntity.setResult(calculateResult(studentEntity));
    return studentEntity;
  }

  /**
   * Retrieves student registration data from a student entity.
   *
   * @param studentEntity the student entity from which to retrieve registration data
   * @return the mapped student registration data
   */
  public static StudentRegistrationData getStudentRegistrationData(StudentEntity studentEntity) {

    StudentRegistrationData studentRegistrationData = new StudentRegistrationData();

    studentRegistrationData.setStudentId(studentEntity.getStudentId());
    studentRegistrationData.setName(
        studentEntity.getFirstName().concat(" ").concat(studentEntity.getLastName()));
    studentRegistrationData.setTotalMark((int) StudentMapper.calculateTotalMark(studentEntity));
    studentRegistrationData.setAverage(BigDecimal.valueOf(calculateAverageMark(studentEntity)));
    studentRegistrationData.setResult(studentEntity.getResult());
    return studentRegistrationData;
  }

}
