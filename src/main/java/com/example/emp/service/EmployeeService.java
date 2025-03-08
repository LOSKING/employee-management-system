package com.example.emp.service;

import com.example.emp.entity.Employee;

import java.util.List;

/**
 * 员工服务接口
 */
public interface EmployeeService {
    /**
     * 查询所有员工
     */
    List<Employee> findAll();
    
    /**
     * 根据ID查询员工
     */
    Employee findById(Integer id);
    
    /**
     * 根据条件查询员工列表
     */
    List<Employee> findByCondition(Employee employee);
    
    /**
     * 新增员工
     */
    int add(Employee employee);
    
    /**
     * 更新员工
     */
    int update(Employee employee);
    
    /**
     * 删除员工
     */
    int deleteById(Integer id);
}