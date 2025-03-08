package com.example.emp.service;

import com.example.emp.entity.Department;

import java.util.List;

/**
 * 部门服务接口
 */
public interface DepartmentService {
    /**
     * 查询所有部门
     */
    List<Department> findAll();
    
    /**
     * 根据ID查询部门
     */
    Department findById(Integer id);
    
    /**
     * 新增部门
     */
    int add(Department department);
    
    /**
     * 更新部门
     */
    int update(Department department);
    
    /**
     * 删除部门
     */
    int deleteById(Integer id);
}