package com.gmc.studentmanagement.web;

import com.gmc.studentmanagement.service.StudentManagementService;
import com.gmc.studentmanagemet.handler.StudentmanagemetApi;
import com.gmc.studentmanagemet.resource.ResultReviseInput;
import com.gmc.studentmanagemet.resource.StudentDetails;
import com.gmc.studentmanagemet.resource.StudentRegistrationData;
import com.gmc.studentmanagemet.resource.StudentRegistrationInput;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for student management system
 *
 * @author Girish Chandra Patra
 */
@RestController
@RequestMapping("/gmc/v1")
public class StudentManagementController implements StudentmanagemetApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(StudentManagementController.class);

  @Autowired
  StudentManagementService studentManagementService;

  /**
   * Registers students based on the provided registration input.
   *
   * @param xtTraceId                The trace ID for tracking the request.
   * @param studentRegistrationInput The input data for student registration.
   * @return A list of registered student data.
   */
  @Override
  public ResponseEntity<List<StudentRegistrationData>> registerStudents(String xtTraceId,
      StudentRegistrationInput studentRegistrationInput) {
    LOGGER.info(
        "Student registration request received StudentManagementController registerStudents() method for the traceId: [{}]",
        xtTraceId);

    return new ResponseEntity<>(
        studentManagementService.registerStudents(xtTraceId, studentRegistrationInput),
        HttpStatus.CREATED);
  }

  /**
   * Updates the marks/results of a student.
   *
   * @param xtTraceId         The trace ID for tracking the request.
   * @param studentId         The ID of the student whose marks are to be updated.
   * @param resultReviseInput The input data for updating student marks.
   * @return The updated student's registration data.
   */
  @Override
  public ResponseEntity<StudentRegistrationData> reviseResult(String xtTraceId,
      String studentId, ResultReviseInput resultReviseInput) {

    LOGGER.info(
        "Mark renovate request received StudentManagementController updateStudentMark() method for studentId: [{}] and traceId: [{}]",
        studentId, xtTraceId);

    return new ResponseEntity<>(
        studentManagementService.reviseResult(xtTraceId, studentId, resultReviseInput),
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<StudentDetails> getStudentResult(String xtTraceId, String studentId) {
    return new ResponseEntity<>(
        studentManagementService.getStudentResult(xtTraceId, studentId),
        HttpStatus.OK);
  }

 /* @Override
  public ResponseEntity<StudentsDetails> getStudentsResult(String xtTraceId) {
    return new ResponseEntity<>(
        studentManagementService.getStudentResult(xtTraceId),
        HttpStatus.OK);
  }*/
}
