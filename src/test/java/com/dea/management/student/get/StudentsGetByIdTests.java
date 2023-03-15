package com.dea.management.student.get;

import com.dea.management.app.student.domain.Student;
import com.dea.management.app.student.repository.StudentRepository;
import com.dea.management.app.user.domain.User;
import com.dea.management.student.StudentTestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class StudentsGetByIdTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentTestUtils studentTestUtils;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void beforeEach() {
        log.info("Before each test in " + StudentsGetByIdTests.class.getSimpleName());
    }

    @BeforeAll
    void beforeSuiteTest() {
        log.info("Before all tests in " + StudentsGetByIdTests.class.getSimpleName());
    }

    @Test
    void whenRequestingAnExistentStudentById_thenReturnTheStudentSuccessfully() throws Exception {
        this.studentRepository.deleteAll();
        this.studentTestUtils.createFakeStudents(10);

        Student student = this.studentRepository.findAll().get(0);

        mockMvc.perform(get("/students/" + student.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(student.getUser().getName())))
                .andExpect(jsonPath("$.email", is(student.getUser().getEmail())))
                .andExpect(jsonPath("$.linkedin", is(student.getUser().getLinkedin())))
                .andExpect(jsonPath("$.university", is(student.getUniversity())))
                .andExpect(jsonPath("$.graduation", is(student.getGraduation())));

    }

    @Test
    void whenRequestingByIdAndIdIsNotANumber_thenReturnTheBadRequestError() throws Exception {

        mockMvc.perform(get("/students/xx"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(1)));
    }

    @Test
    void whenRequestingAnNonExistentStudentById_thenReturnTheNotFoundError() throws Exception {

        mockMvc.perform(get("/students/5000"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(1)));
    }
}
