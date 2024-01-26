package com.test.studentmanagement.service;

import com.test.studentmanagement.dto.DepartmentDTO;
import com.test.studentmanagement.entity.Department;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);

    DepartmentDTO getDepartmentById(Long departmentId);

    Department getDepartmentEntityById(Long departmentId);

    List<DepartmentDTO> getAllDepartments();
}
