package com.test.studentmanagement.mapper;

import com.test.studentmanagement.dto.StudentDto;
import com.test.studentmanagement.entity.Department;
import com.test.studentmanagement.entity.Student;
import com.test.studentmanagement.service.DepartmentService;

public class StudentMapper {

    private DepartmentService departmentService;
    public static StudentDto mapToStudentDto(Student student){
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getDateOfBirth(),
                student.getEmail(),
                AddressMapper.mapToAddressDto(student.getAddress()),
                DepartmentMapper.mapToDepartmentDto(student.getDepartment())
        );
    }
    public static Student mapToStudent(StudentDto studentDto, DepartmentService departmentService){
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setDateOfBirth(studentDto.getDateOfBirth());
        student.setEmail(studentDto.getEmail());
        student.setAddress(AddressMapper.mapToAddress(studentDto.getAddress()));
//Build code to Retrieve existing Department for Student
        Department existingDepartment = departmentService.getDepartmentEntityById(studentDto.getDepartment().getId());
        if(existingDepartment != null){
            student.setDepartment(existingDepartment);
        } else {
            student.setDepartment(DepartmentMapper.mapToDepartment(studentDto.getDepartment()));
        }
        return student;
    }
}
