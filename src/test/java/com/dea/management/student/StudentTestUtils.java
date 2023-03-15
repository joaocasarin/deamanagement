package com.dea.management.student;

import com.dea.management.app.student.domain.Student;
import com.dea.management.app.student.repository.StudentRepository;
import com.dea.management.app.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class StudentTestUtils {

    @Autowired
    private StudentRepository studentRepository;

    public void createFakeStudents(int amount) {
        for (int i = 0; i < amount; i++) {
            User u = new User();
            u.setEmail("email " + i);
            u.setName("name " + i);
            u.setLinkedin("linkedin " + i);
            u.setPassword("password " + i);

            Student student = Student.builder()
                    .university("UNI " + i)
                    .graduation("Grad " + i)
                    .finishDate(LocalDate.now())
                    .user(u)
                    .build();

            this.studentRepository.save(student);
            List<Student> students = this.studentRepository.findAll();

        }
        List<Student> students = this.studentRepository.findAll();
    }

}
