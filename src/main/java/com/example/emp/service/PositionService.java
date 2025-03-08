package com.example.emp.service;

import com.example.emp.entity.Position;

import java.util.List;

/**
 * 职位服务接口
 */
public interface PositionService {
    /**
     * 查询所有职位
     */
    List<Position> findAll();
    
    /**
     * 根据ID查询职位
     */
    Position findById(Integer id);
    
    /**
     * 新增职位
     */
    int add(Position position);
    
    /**
     * 更新职位
     */
    int update(Position position);
    
    /**
     * 删除职位
     */
    int deleteById(Integer id);
}