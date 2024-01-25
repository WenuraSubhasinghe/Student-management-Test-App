package com.test.studentmanagement.service.impl;

import com.test.studentmanagement.dto.StudentDto;
import com.test.studentmanagement.entity.Address;
import com.test.studentmanagement.entity.Student;
import com.test.studentmanagement.exception.ResourceNotFoundException;
import com.test.studentmanagement.mapper.StudentMapper;
import com.test.studentmanagement.repository.AddressRepository;
import com.test.studentmanagement.repository.StudentRepository;
import com.test.studentmanagement.service.DepartmentService;
import com.test.studentmanagement.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private DepartmentService departmentService;
    private AddressRepository addressRepository;
    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto, departmentService);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(savedStudent);
    }
    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Employee is not exists with given id :" + studentId));
        return StudentMapper.mapToStudentDto(student);
    }
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map((student) -> StudentMapper.mapToStudentDto(student))
            .collect(Collectors.toList());
    }
    @Override
    public StudentDto updateStudent(Long studentId, StudentDto updatedStudent) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() ->
                new ResourceNotFoundException(("Student is not exist with given id: " + studentId)));

        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setDateOfBirth(updatedStudent.getDateOfBirth());
        student.setEmail(updatedStudent.getEmail());
        Address existingAddress = addressRepository.findById(student.getAddress().getId())
            .orElseThrow(() ->
                new ResourceNotFoundException("Address is not exist!."));
        existingAddress.setCity(updatedStudent.getAddress().getCity());
        existingAddress.setStreet(updatedStudent.getAddress().getStreet());
        addressRepository.save(existingAddress);

        Student updatedStudentObj = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(updatedStudentObj);
    }
    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() ->
                new ResourceNotFoundException(("Student is not exist with given id: " + studentId)));
        studentRepository.deleteById(studentId);
    }
}
