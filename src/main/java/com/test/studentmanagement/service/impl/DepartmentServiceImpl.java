package com.test.studentmanagement.service.impl;

import com.test.studentmanagement.dto.DepartmentDto;
import com.test.studentmanagement.entity.Department;
import com.test.studentmanagement.exception.ResourceNotFoundException;
import com.test.studentmanagement.mapper.DepartmentMapper;
import com.test.studentmanagement.repository.DepartmentRepository;
import com.test.studentmanagement.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }
    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Department doesn't exists with given id :" + departmentId));
        return DepartmentMapper.mapToDepartmentDto(department);
    }
    @Override
    public Department getDepartmentEntityById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Department doesn't exist with given id: " + departmentId));
        return department;
    }
    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDto(department))
            .collect(Collectors.toList());
    }
}
