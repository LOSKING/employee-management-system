package com.example.emp.controller;

import com.example.emp.entity.Department;
import com.example.emp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理控制器
 */
@RestController
@RequestMapping("/api/departments")
@CrossOrigin
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询所有部门
     */
    @GetMapping
    public ResponseEntity<List<Department>> findAll() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    /**
     * 根据ID查询部门
     */
    @GetMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable Integer id) {
        Department department = departmentService.findById(id);
        return department != null ? ResponseEntity.ok(department) : ResponseEntity.notFound().build();
    }

    /**
     * 新增部门
     */
    @PostMapping
    public ResponseEntity<Void> add(@RequestBody Department department) {
        return departmentService.add(department) > 0 ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    /**
     * 更新部门
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Department department) {
        department.setId(id);
        return departmentService.update(department) > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    /**
     * 删除部门
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return departmentService.deleteById(id) > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}