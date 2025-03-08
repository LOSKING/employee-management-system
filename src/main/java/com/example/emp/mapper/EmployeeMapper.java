package com.example.emp.mapper;

import com.example.emp.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工Mapper接口
 */
@Mapper
public interface EmployeeMapper {
    /**
     * 查询所有员工
     */
    List<Employee> selectAll();
    
    /**
     * 根据ID查询员工
     */
    Employee selectById(@Param("id") Integer id);
    
    /**
     * 根据条件查询员工列表
     */
    List<Employee> selectByCondition(Employee employee);
    
    /**
     * 新增员工
     */
    int insert(Employee employee);
    
    /**
     * 更新员工
     */
    int update(Employee employee);
    
    /**
     * 删除员工
     */
    int deleteById(@Param("id") Integer id);
}