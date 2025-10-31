package org.raflab.studsluzba.model.dtos;

import lombok.Data;

@Data
public class SubjectDTO {
    private Integer id;
    private String name;
    private String studyProgram;
    private Integer semester;
    private Integer lectureHours;
    private Integer exerciseHours;
    private Integer practicumHours;
    private String mandatory;
    private Integer lectureSessions;
    private Integer exerciseSessions;
}