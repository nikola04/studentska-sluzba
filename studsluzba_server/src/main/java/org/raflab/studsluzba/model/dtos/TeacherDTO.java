package org.raflab.studsluzba.model.dtos;

import lombok.Data;

@Data
public class TeacherDTO {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String title;
    private boolean admin;
}