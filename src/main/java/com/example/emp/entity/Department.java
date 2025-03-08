package com.example.emp.entity;

import lombok.Data;
import java.util.Date;

/**
 * 部门实体类
 */
@Data
public class Department {
    /**
     * 部门ID
     */
    private Integer id;
    
    /**
     * 部门名称
     */
    private String name;
    
    /**
     * 部门描述
     */
    private String description;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date updateTime;
}