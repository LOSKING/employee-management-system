package com.example.emp.service.impl;

import com.example.emp.entity.Employee;
import com.example.emp.mapper.EmployeeMapper;
import com.example.emp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工服务实现类
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> findAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public Employee findById(Integer id) {
        return employeeMapper.selectById(id);
    }

    @Override
    public List<Employee> findByCondition(Employee employee) {
        return employeeMapper.selectByCondition(employee);
    }

    @Override
    public int add(Employee employee) {
        return employeeMapper.insert(employee);
    }

    @Override
    public int update(Employee employee) {
        return employeeMapper.update(employee);
    }

    @Override
    public int deleteById(Integer id) {
        return employeeMapper.deleteById(id);
    }
}