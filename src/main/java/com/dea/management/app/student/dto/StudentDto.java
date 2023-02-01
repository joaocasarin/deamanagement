package com.dea.management.app.student.dto;

import com.dea.management.app.student.domain.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentDto extends StudentBaseDto{

    private String name;
    private String email;
    private String linkedin;

    public static List<StudentDto> fromStudents(List<Student> students) {
        return students.stream().map(student -> {
            StudentDto studentDto = StudentDto.fromStudent(student);
            return studentDto;
        }).collect(Collectors.toList());
    }

    public static StudentDto fromStudent(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getUser().getName());
        studentDto.setEmail(student.getUser().getEmail());
        studentDto.setLinkedin(student.getUser().getLinkedin());
        studentDto.setGraduation(student.getGraduation());
        studentDto.setUniversity(student.getUniversity());
        studentDto.setFinishDate(student.getFinishDate());

        return studentDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
}
