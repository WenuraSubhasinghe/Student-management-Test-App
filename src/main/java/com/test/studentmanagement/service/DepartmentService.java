package com.test.studentmanagement.service;

import com.test.studentmanagement.dto.DepartmentDto;
import com.test.studentmanagement.entity.Department;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(Long departmentId);
    Department getDepartmentEntityById(Long departmentId);
    List<DepartmentDto> getAllDepartments();
}
