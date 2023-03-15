package com.dea.management.app.student.dto;

import com.dea.management.app.student.domain.Student;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
}
