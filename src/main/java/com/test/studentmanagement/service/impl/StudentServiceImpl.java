package com.test.studentmanagement.service.impl;

import com.test.studentmanagement.dto.StudentDTO;
import com.test.studentmanagement.entity.Address;
import com.test.studentmanagement.entity.Department;
import com.test.studentmanagement.entity.Student;
import com.test.studentmanagement.exception.ResourceNotFoundException;
import com.test.studentmanagement.mapper.StudentMapper;
import com.test.studentmanagement.repository.AddressRepository;
import com.test.studentmanagement.repository.StudentRepository;
import com.test.studentmanagement.service.DepartmentService;
import com.test.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Department existingDepartment = departmentService.getDepartmentEntityById(studentDTO.getDepartment().getId());
        Student student = StudentMapper.mapToStudent(studentDTO);
        student.setDepartment(existingDepartment);
        Student savedStudent = studentRepository.save(student);
        StudentDTO savedStudentDTO = StudentMapper.mapToStudentDto(savedStudent);
        return savedStudentDTO;
    }

    @Override
    public StudentDTO getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Employee is not exists with given id :" + studentId));

        StudentDTO studentDTO = StudentMapper.mapToStudentDto(student);
        return studentDTO;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map((student) -> StudentMapper.mapToStudentDto(student))
            .collect(Collectors.toList());
    }

    @Override
    public StudentDTO updateStudent(Long studentId, StudentDTO updatedStudent) {
        Objects.requireNonNull(updatedStudent, "Student details cannot be null");
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

        Student updatedStudentObject = studentRepository.save(student);
        StudentDTO updatedStudentDTO = StudentMapper.mapToStudentDto(updatedStudentObject);
        return updatedStudentDTO;
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() ->
                new ResourceNotFoundException(("Student is not exist with given id: " + studentId)));
        studentRepository.deleteById(student.getId());
    }
}
