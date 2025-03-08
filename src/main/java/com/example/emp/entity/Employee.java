package com.example.emp.entity;

import lombok.Data;
import java.util.Date;

/**
 * 员工实体类
 */
@Data
public class Employee {
    /**
     * 员工ID
     */
    private Integer id;
    
    /**
     * 员工姓名
     */
    private String name;
    
    /**
     * 性别：0-未知，1-男，2-女
     */
    private Integer gender;
    
    /**
     * 年龄
     */
    private Integer age;
    
    /**
     * 身份证号
     */
    private String idCard;
    
    /**
     * 手机号
     */
    private String phone;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 地址
     */
    private String address;
    
    /**
     * 部门ID
     */
    private Integer departmentId;
    
    /**
     * 部门名称（非数据库字段）
     */
    private String departmentName;
    
    /**
     * 职位ID
     */
    private Integer positionId;
    
    /**
     * 职位名称（非数据库字段）
     */
    private String positionName;
    
    /**
     * 入职日期
     */
    private String entryDate;
    
    /**
     * 状态：0-离职，1-在职
     */
    private Integer status;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date updateTime;
}