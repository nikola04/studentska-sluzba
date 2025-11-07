package org.raflab.studsluzba.controllers;

import org.raflab.studsluzba.controllers.request.StudentPodaciRequest;
import org.raflab.studsluzba.controllers.response.StudentPodaciResponse;
import org.raflab.studsluzba.mappers.StudentMapper;
import org.raflab.studsluzba.model.StudentPodaci;
import org.raflab.studsluzba.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="/api/student")
public class StudentController {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentService studentService;

    @PostMapping(path="/")
	public Long addNewStudentPodaci(@Valid @RequestBody StudentPodaciRequest studentPodaci) {
		StudentPodaci sp = studentService.saveStudentPodaci(studentMapper.toEntity(studentPodaci));
		return sp.getId();
	}

    @GetMapping(path="/")
    public List<StudentPodaciResponse> getAllStudentPodaci() {
        return studentMapper.toResponseList(studentService.getAllStudentPodaci());
    }

    @GetMapping(path="/{id}")
    public StudentPodaciResponse getStudentPodaciById(@PathVariable Long id) {
        return studentMapper.toResponse(studentService.getStudentPodaci(id));
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteStudentPodaci(@PathVariable Long id) {
        studentService.deleteStudentPodaci(id);
        return true;
    }

    @PatchMapping(path = "/{id}")
    public StudentPodaciResponse updateStudentPodaci(@PathVariable Long id, @Valid @RequestBody StudentPodaciRequest request) {
        StudentPodaci studentPodaci = studentMapper.toEntity(request);
        StudentPodaci updated = studentService.updateStudentPodaci(id, studentPodaci);
        return studentMapper.toResponse(updated);
    }
}
