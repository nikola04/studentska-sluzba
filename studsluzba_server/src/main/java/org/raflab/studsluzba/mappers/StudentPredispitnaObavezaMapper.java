package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.StudentPredispitnaObavezaRequest;
import org.raflab.studsluzba.controllers.response.StudentPredispitnaObavezaResponse;
import org.raflab.studsluzba.model.StudentPredispitnaObaveza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentPredispitnaObavezaMapper {
    @Autowired
    private PredispitnaObavezaMapper predispitnaObavezaMapper;

    public StudentPredispitnaObaveza toEntity(StudentPredispitnaObavezaRequest request) {
        StudentPredispitnaObaveza studentPredispitnaObaveza = new StudentPredispitnaObaveza();

        studentPredispitnaObaveza.setBrojPoena(request.getBrojPoena());

        return studentPredispitnaObaveza;
    }

    public StudentPredispitnaObavezaResponse toResponse(StudentPredispitnaObaveza entity) {
        StudentPredispitnaObavezaResponse response = new StudentPredispitnaObavezaResponse();

        response.setId(entity.getId());
        response.setBrojPoena(entity.getBrojPoena());
        response.setPredispitnaObaveza(predispitnaObavezaMapper.toResponse(entity.getPredispitnaObaveza()));

        return response;
    }

    public List<StudentPredispitnaObavezaResponse> toResponseList(List<StudentPredispitnaObaveza> studentPredispitnaObavezaList){
        return studentPredispitnaObavezaList.stream().map(this::toResponse).collect(java.util.stream.Collectors.toList());
    }
}
