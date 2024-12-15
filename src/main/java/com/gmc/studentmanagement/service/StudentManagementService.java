package com.gmc.studentmanagement.service;


import com.gmc.studentmanagemet.resource.ResultReviseInput;
import com.gmc.studentmanagemet.resource.StudentDetails;
import com.gmc.studentmanagemet.resource.StudentRegistrationData;
import com.gmc.studentmanagemet.resource.StudentRegistrationInput;

import java.util.List;

/**
 * Service interface for managing student registration and updates.
 *
 * @author Girish Chandra Patra
 */
public interface StudentManagementService {

    /**
     * Registers students based on the provided registration input.
     *
     * @param xtTraceId             The trace ID for tracking the request.
     * @param studentRegistrationInput The input data for student registration.
     * @return A list of registered student data.
     */
    List<StudentRegistrationData> registerStudents(String xtTraceId, StudentRegistrationInput studentRegistrationInput);

    /**
     * Updates the marks/results of a student.
     *
     * @param xtTraceId           The trace ID for tracking the request.
     * @param studentId           The ID of the student whose marks are to be updated.
     * @param resultReviseInput The input data for updating student marks.
     *
     * @return The updated student's registration data.
     */
    StudentRegistrationData reviseResult(String xtTraceId, String studentId, ResultReviseInput resultReviseInput);

  StudentDetails getStudentResult(String xtTraceId, String studentId);
}
