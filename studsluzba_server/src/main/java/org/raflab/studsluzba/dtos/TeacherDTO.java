package org.raflab.studsluzba.dtos;

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