package com.dea.management.app.student.controller;

import com.dea.management.app.student.domain.Student;
import com.dea.management.app.student.dto.CreateStudentRequestDto;
import com.dea.management.app.student.dto.StudentDto;
import com.dea.management.app.student.dto.UpdateStudentRequestDto;
import com.dea.management.app.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@Tag(name = "Student", description = "The Student API")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Operation(summary = "Load the list of students paginated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Page or Page Size params not valid"),
            @ApiResponse(responseCode = "500", description = "Error fetching student list"),
    })
    @GetMapping("/students")
    public Page<StudentDto> getAllStudents(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> pageSize) {
        Integer newPage = page.orElse(0);
        Integer newPageSize = pageSize.orElse(1);

        log.info(String.format("Fetching students : page : %s : pageSize", newPage, newPageSize));
        Page<Student> studentsPaginated = this.studentService.findAllStudents(newPage, newPageSize);
        log.info(String.format("Students loaded successfully : Students : %s : pageSize", studentsPaginated.getContent()));

        return studentsPaginated.map(student -> StudentDto.fromStudent(student));
    }

    @Operation(summary = "Load the student by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Student Id invalid"),
            @ApiResponse(responseCode = "404", description = "Student Not found"),
            @ApiResponse(responseCode = "500", description = "Error fetching student list"),
    })
    @GetMapping("/students/{id}")
    public StudentDto getStudent(@PathVariable("id") Long id) {
        log.info(String.format("Fetching student by id : Id : %s", id));
        Student student = this.studentService.findStudentById(id);
        log.info(String.format("Student loaded successfully : Student : %s", student));

        return StudentDto.fromStudent(student);
    }

    @Operation(summary = "Create a new Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Payload not valid"),
            @ApiResponse(responseCode = "500", description = "Error creating student"),
    })
    @PostMapping("/students")
    public void createStudent(@Valid @RequestBody CreateStudentRequestDto createStudentRequestDto) {
        log.info(String.format("Creating Student : Payload : %s", createStudentRequestDto));

        Student student = studentService.createStudent(createStudentRequestDto);

        log.info(String.format("Student created successfully : id : %s", student.getId()));
    }

    @Operation(summary = "Update a Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Payload not valid"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "500", description = "Error updating student"),
    })
    @PutMapping("/students/{studentId}")
    public void updateStudent(@PathVariable Long studentId, @Valid @RequestBody UpdateStudentRequestDto updateStudentRequestDto) {
        log.info(String.format("Updating Student : Payload : %s", updateStudentRequestDto));

        Student student = studentService.updateStudent(studentId, updateStudentRequestDto);

        log.info(String.format("Student created successfully : id : %s", student.getId()));
    }

    @Operation(summary = "Delete a Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Payload not valid"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "500", description = "Error deleting student"),
    })
    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable Long studentId) {
        log.info(String.format("Removing Student : Id : %s", studentId));

        studentService.deleteStudent(studentId);

        log.info(String.format("Student removed successfully : id : %s", studentId));
    }
}
