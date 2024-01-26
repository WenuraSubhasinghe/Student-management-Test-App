package com.test.studentmanagement.service;


import com.test.studentmanagement.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO createStudent(StudentDTO studentDTO);
    StudentDTO getStudentById(Long studentId);
    List<StudentDTO> getAllStudents();
    StudentDTO updateStudent(Long studentId, StudentDTO updatedStudent);
    void deleteStudent(Long studentId);
}
