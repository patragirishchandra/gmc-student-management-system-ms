package com.gmc.studentmanagement.repository;


import com.gmc.studentmanagement.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TRepository class for StudentEntity.
 *
 * @author Girish Chandra Patra
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, String> {
  // This interface will inherit methods for basic CRUD (Create, Read, Update, Delete) operations from JpaRepository
  // The type parameters <StudentEntity, String> specify the entity type and the type of its primary key

  StudentEntity findByStudentId(String studentId);
}
