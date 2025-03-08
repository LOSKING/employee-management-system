package com.example.emp.service.impl;

import com.example.emp.entity.Position;
import com.example.emp.mapper.PositionMapper;
import com.example.emp.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 职位服务实现类
 */
@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionMapper positionMapper;

    @Override
    public List<Position> findAll() {
        return positionMapper.selectAll();
    }

    @Override
    public Position findById(Integer id) {
        return positionMapper.selectById(id);
    }

    @Override
    public int add(Position position) {
        return positionMapper.insert(position);
    }

    @Override
    public int update(Position position) {
        return positionMapper.update(position);
    }

    @Override
    public int deleteById(Integer id) {
        return positionMapper.deleteById(id);
    }
}