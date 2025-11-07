package org.raflab.studsluzba.controllers;

import org.raflab.studsluzba.mappers.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(path="/api/student")
public class StudentController {
    @Autowired
    private StudentMapper studentMapper;
}
