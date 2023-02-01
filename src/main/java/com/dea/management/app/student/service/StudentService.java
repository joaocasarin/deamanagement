package com.dea.management.app.student.service;

import com.dea.management.app.exceptions.NotFoundException;
import com.dea.management.app.student.domain.Student;
import com.dea.management.app.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Page<Student> findAllStudents(Integer page, Integer pageSize) {
        return this.studentRepository.findAllStudents(PageRequest.of(page, pageSize));
    }

    public Student findStudentById(Long id) {
        return this.studentRepository.findById(id).orElseThrow(() -> new NotFoundException(Student.class, id));
    }
}
