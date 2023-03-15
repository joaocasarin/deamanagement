package com.dea.management.app.student.controller;

import com.dea.management.app.student.domain.Student;
import com.dea.management.app.student.dto.StudentDto;
import com.dea.management.app.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public Page<StudentDto> getAllStudents(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> pageSize) {
        Integer newPage = page.orElse(0);
        Integer newPageSize = pageSize.orElse(1);

        log.info(String.format("Fetching students : page : %s : pageSize", newPage, newPageSize));
        Page<Student> studentsPaginated = this.studentService.findAllStudents(newPage, newPageSize);
        log.info(String.format("Students loaded successfully : Students : %s : pageSize", studentsPaginated.getContent()));

        return studentsPaginated.map(student -> StudentDto.fromStudent(student));
    }

    @GetMapping("/students/{id}")
    public StudentDto getStudent(@PathVariable("id") Long id) {
        log.info(String.format("Fetching student by id : Id : %s", id));
        Student student = this.studentService.findStudentById(id);
        log.info(String.format("Student loaded successfully : Student : %s", student));

        return StudentDto.fromStudent(student);
    }
}
