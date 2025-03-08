package com.example.emp.service.impl;

import com.example.emp.entity.Department;
import com.example.emp.mapper.DepartmentMapper;
import com.example.emp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门服务实现类
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> findAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public Department findById(Integer id) {
        return departmentMapper.selectById(id);
    }

    @Override
    public int add(Department department) {
        return departmentMapper.insert(department);
    }

    @Override
    public int update(Department department) {
        return departmentMapper.update(department);
    }

    @Override
    public int deleteById(Integer id) {
        return departmentMapper.deleteById(id);
    }
}