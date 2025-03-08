package com.example.emp.controller;

import com.example.emp.entity.Employee;
import com.example.emp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工管理控制器
 */
@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 查询所有员工
     */
    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    /**
     * 根据ID查询员工
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Integer id) {
        Employee employee = employeeService.findById(id);
        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    /**
     * 根据条件查询员工
     */
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> search(Employee employee) {
        return ResponseEntity.ok(employeeService.findByCondition(employee));
    }

    /**
     * 新增员工
     */
    @PostMapping
    public ResponseEntity<Void> add(@RequestBody Employee employee) {
        return employeeService.add(employee) > 0 ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    /**
     * 更新员工
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Employee employee) {
        employee.setId(id);
        return employeeService.update(employee) > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    /**
     * 删除员工
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return employeeService.deleteById(id) > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}