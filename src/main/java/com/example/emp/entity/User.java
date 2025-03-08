package com.example.emp.entity;

import lombok.Data;
import java.util.Date;

/**
 * 用户实体类
 */
@Data
public class User {
    /**
     * 用户ID
     */
    private Integer id;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 角色：ADMIN-管理员，EMPLOYEE-员工
     */
    private String role;
    
    /**
     * 关联的员工ID
     */
    private Integer employeeId;
    
    /**
     * 关联的员工信息（非数据库字段）
     */
    private Employee employee;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date updateTime;
}