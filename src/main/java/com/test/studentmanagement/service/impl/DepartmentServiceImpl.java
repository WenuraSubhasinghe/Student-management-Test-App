package com.test.studentmanagement.service.impl;

import com.test.studentmanagement.dto.DepartmentDTO;
import com.test.studentmanagement.entity.Department;
import com.test.studentmanagement.exception.ResourceNotFoundException;
import com.test.studentmanagement.mapper.DepartmentMapper;
import com.test.studentmanagement.repository.DepartmentRepository;
import com.test.studentmanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = DepartmentMapper.mapToDepartment(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        DepartmentDTO savedDepartmentDTO = DepartmentMapper.mapToDepartmentDto(savedDepartment);
        return savedDepartmentDTO;
    }

    @Override
    public DepartmentDTO getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Department doesn't exists with given id :" + departmentId));
        DepartmentDTO departmentDTO = DepartmentMapper.mapToDepartmentDto(department);
        return departmentDTO;
    }

    @Override
    public Department getDepartmentEntityById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Department doesn't exist with given id: " + departmentId));
        return department;
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDto(department))
            .collect(Collectors.toList());
    }
}
