package com.gmc.studentmanagement.service;


import static com.gmc.studentmanagement.exception.enums.ErrorCodeEnum.RECORD_NOT_FOUND;
import static com.gmc.studentmanagement.exception.enums.ErrorMessageEnum.RECORD_NOT_FOUND_MSG;
import static com.gmc.studentmanagement.mapper.StudentMapper.getStudentRegistrationData;
import static com.gmc.studentmanagement.mapper.StudentMapper.mapStudentEntities;
import static com.gmc.studentmanagement.mapper.StudentMapper.mapStudentRegistrationData;
import static com.gmc.studentmanagement.mapper.StudentMapper.mapUpdatedResult;

import com.gmc.studentmanagement.entity.StudentEntity;
import com.gmc.studentmanagement.exception.RecordNotFoundException;
import com.gmc.studentmanagement.repository.StudentRepository;
import com.gmc.studentmanagemet.resource.ResultReviseInput;
import com.gmc.studentmanagemet.resource.StudentRegistrationData;
import com.gmc.studentmanagemet.resource.StudentRegistrationInput;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing student registration and updates.
 *
 * @author Girish Chandra Patra
 */
@Service
public class StudentManagementServiceImpl implements StudentManagementService {

  private static final Logger LOGGER = LoggerFactory.getLogger(StudentManagementServiceImpl.class);

  @Autowired
  StudentRepository studentRepository;

  /**
   * Registers students based on the provided registration input.
   *
   * @param xtTraceId                The trace ID for tracking the request.
   * @param studentRegistrationInput The input data for student registration.
   * @return A list of registered student data.
   */
  @Override
  public List<StudentRegistrationData> registerStudents(String xtTraceId,
      StudentRegistrationInput studentRegistrationInput) {

    LOGGER.info(
        "Student registration request received in the registerStudents() method for the traceId: [{}]",
        xtTraceId);
    List<StudentEntity> studentEntities = studentRepository.saveAll(
        mapStudentEntities(studentRegistrationInput));

    return mapStudentRegistrationData(studentEntities);

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
  public StudentRegistrationData reviseResult(String xtTraceId, String studentId,
      ResultReviseInput resultReviseInput) {

    LOGGER.info(
        "Result revise request received  method for the traceId: [{}]",
        xtTraceId);
    Optional<StudentEntity> optionalStudentEntity = studentRepository.findById(studentId);

    if (!optionalStudentEntity.isPresent()) {
      throw new RecordNotFoundException(RECORD_NOT_FOUND.getErrorCode(),
          RECORD_NOT_FOUND_MSG.getErrorMessage().concat(studentId));
    }
    StudentEntity studentEntity = optionalStudentEntity.get();

    mapUpdatedResult(resultReviseInput, studentEntity);

    return getStudentRegistrationData(studentEntity);
  }

}
