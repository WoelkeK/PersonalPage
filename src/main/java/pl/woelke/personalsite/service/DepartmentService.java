package pl.woelke.personalsite.service;


import pl.woelke.personalsite.exception.DepartmentNotFoundException;
import pl.woelke.personalsite.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> findAllDepartments();

    Department createDepartment(Department department);

    Department findDepartmentById(Long id) throws DepartmentNotFoundException;

    Department updateDepartment(Department department, Long id) throws DepartmentNotFoundException;

    void deleteDepartment(Long id);

    Department findDepartmentByName(String departmentName);
}
