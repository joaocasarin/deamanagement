package com.dea.management.app.student.service;

import com.dea.management.app.exceptions.NotFoundException;
import com.dea.management.app.student.domain.Student;
import com.dea.management.app.student.dto.CreateStudentRequestDto;
import com.dea.management.app.student.dto.UpdateStudentRequestDto;
import com.dea.management.app.student.repository.StudentRepository;
import com.dea.management.app.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Page<Student> findAllStudents(Integer page, Integer pageSize) {
        return this.studentRepository.findAllStudents(PageRequest.of(page, pageSize, Sort.by("user.name").ascending()));
    }

    public Student findStudentById(Long id) {
        return this.studentRepository.findById(id).orElseThrow(() -> new NotFoundException(Student.class, id));
    }

    public Student createStudent(CreateStudentRequestDto createStudentRequestDto) {
        User user = User.builder()
                .name(createStudentRequestDto.getName())
                .email(createStudentRequestDto.getEmail())
                .password(createStudentRequestDto.getPassword())
                .linkedin(createStudentRequestDto.getLinkedin())
                .build();

        Student student = Student.builder()
                .user(user)
                .finishDate(createStudentRequestDto.getFinishDate())
                .graduation(createStudentRequestDto.getGraduation())
                .university(createStudentRequestDto.getUniversity())
                .build();

        return this.studentRepository.save(student);
    }

    public Student updateStudent(Long studentId, UpdateStudentRequestDto updateStudentRequestDto) {
        Student student = this.findStudentById(studentId);
        User user = student.getUser();

        user.setName(updateStudentRequestDto.getName());
        user.setEmail(updateStudentRequestDto.getEmail());
        user.setPassword(updateStudentRequestDto.getPassword());
        user.setLinkedin(updateStudentRequestDto.getLinkedin());

        student.setUser(user);
        student.setFinishDate(updateStudentRequestDto.getFinishDate());
        student.setGraduation(updateStudentRequestDto.getGraduation());
        student.setUniversity(updateStudentRequestDto.getUniversity());

        return this.studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        Student student = this.findStudentById(studentId);
        this.studentRepository.delete(student);
    }
}
