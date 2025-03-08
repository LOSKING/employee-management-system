package com.example.emp.controller;

import com.example.emp.entity.Position;
import com.example.emp.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 职位管理控制器
 */
@RestController
@RequestMapping("/api/positions")
@CrossOrigin
public class PositionController {

    @Autowired
    private PositionService positionService;

    /**
     * 查询所有职位
     */
    @GetMapping
    public ResponseEntity<List<Position>> findAll() {
        return ResponseEntity.ok(positionService.findAll());
    }

    /**
     * 根据ID查询职位
     */
    @GetMapping("/{id}")
    public ResponseEntity<Position> findById(@PathVariable Integer id) {
        Position position = positionService.findById(id);
        return position != null ? ResponseEntity.ok(position) : ResponseEntity.notFound().build();
    }

    /**
     * 新增职位
     */
    @PostMapping
    public ResponseEntity<Void> add(@RequestBody Position position) {
        return positionService.add(position) > 0 ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    /**
     * 更新职位
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Position position) {
        position.setId(id);
        return positionService.update(position) > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    /**
     * 删除职位
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return positionService.deleteById(id) > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}