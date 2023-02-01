package com.dea.management.app.student.dto;

import java.time.LocalDate;

public class StudentBaseDto {

    private Long id;
    private String university;
    private String graduation;
    private LocalDate finishDate;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }
}
