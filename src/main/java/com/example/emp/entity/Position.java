package com.example.emp.entity;

import lombok.Data;
import java.util.Date;

/**
 * 职位实体类
 */
@Data
public class Position {
    /**
     * 职位ID
     */
    private Integer id;
    
    /**
     * 职位名称
     */
    private String name;
    
    /**
     * 职位描述
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