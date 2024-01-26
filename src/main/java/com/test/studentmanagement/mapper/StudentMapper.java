package com.test.studentmanagement.mapper;

import com.test.studentmanagement.dto.StudentDTO;
import com.test.studentmanagement.entity.Student;

public class StudentMapper {
    public static StudentDTO mapToStudentDto(Student student) {
        return new StudentDTO(
            student.getId(),
            student.getFirstName(),
            student.getLastName(),
            student.getDateOfBirth(),
            student.getEmail(),
            AddressMapper.mapToAddressDto(student.getAddress()),
            DepartmentMapper.mapToDepartmentDto(student.getDepartment())
        );
    }

    public static Student mapToStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setEmail(studentDTO.getEmail());
        student.setAddress(AddressMapper.mapToAddress(studentDTO.getAddress()));
        return student;
    }
}
