package com.example.emp.mapper;

import com.example.emp.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门Mapper接口
 */
@Mapper
public interface DepartmentMapper {
    /**
     * 查询所有部门
     */
    List<Department> selectAll();
    
    /**
     * 根据ID查询部门
     */
    Department selectById(@Param("id") Integer id);
    
    /**
     * 新增部门
     */
    int insert(Department department);
    
    /**
     * 更新部门
     */
    int update(Department department);
    
    /**
     * 删除部门
     */
    int deleteById(@Param("id") Integer id);
}