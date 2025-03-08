package com.example.emp.mapper;

import com.example.emp.entity.Position;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 职位Mapper接口
 */
@Mapper
public interface PositionMapper {
    /**
     * 查询所有职位
     */
    List<Position> selectAll();
    
    /**
     * 根据ID查询职位
     */
    Position selectById(@Param("id") Integer id);
    
    /**
     * 新增职位
     */
    int insert(Position position);
    
    /**
     * 更新职位
     */
    int update(Position position);
    
    /**
     * 删除职位
     */
    int deleteById(@Param("id") Integer id);
}