package com.dea.management.app.student.controller;

import com.dea.management.app.student.domain.Student;
import com.dea.management.app.student.dto.StudentDto;
import com.dea.management.app.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public Page<StudentDto> getAllStudents(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> pageSize) {
        Integer newPage = page.orElse(0);
        Integer newPageSize = pageSize.orElse(1);

        Page<Student> studentsPaginated = this.studentService.findAllStudents(newPage, newPageSize);

        return studentsPaginated.map(student -> StudentDto.fromStudent(student));
    }

    @GetMapping("/students/{id}")
    public StudentDto getStudent(@PathVariable("id") Long id) {
        Student student = this.studentService.findStudentById(id);

        return StudentDto.fromStudent(student);
    }
}
