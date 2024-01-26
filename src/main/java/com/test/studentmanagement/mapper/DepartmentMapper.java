package com.test.studentmanagement.mapper;

import com.test.studentmanagement.dto.DepartmentDTO;
import com.test.studentmanagement.entity.Department;

public class DepartmentMapper {
    public static DepartmentDTO mapToDepartmentDto(Department department) {
        return new DepartmentDTO(
            department.getId(),
            department.getDepartmentName()
        );
    }

    public static Department mapToDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setId(departmentDTO.getId());
        department.setDepartmentName(departmentDTO.getDepartmentName());
        return department;
    }
}
