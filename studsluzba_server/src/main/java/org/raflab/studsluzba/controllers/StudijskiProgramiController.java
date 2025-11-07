package org.raflab.studsluzba.controllers;

import java.util.List;

import org.raflab.studsluzba.controllers.request.StudijskiProgramRequest;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.controllers.response.StudijskiProgramResponse;
import org.raflab.studsluzba.mappers.PredmetMapper;
import org.raflab.studsluzba.mappers.StudijskiProgramMapper;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.StudijskiProgram;
import org.raflab.studsluzba.services.PredmetService;
import org.raflab.studsluzba.services.StudijskiProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path="/api/studijski-program")
public class StudijskiProgramiController {
	@Autowired
    private StudijskiProgramService studijskiProgramService;
    @Autowired
    private PredmetService predmetService;
    @Autowired
    private StudijskiProgramMapper studijskiProgramMapper;
    @Autowired
    private PredmetMapper predmetMapper;

    @PostMapping(path="/")
    public Long createStudijskiProgram(@Valid @RequestBody StudijskiProgramRequest request) {
        StudijskiProgram studijskiProgram = studijskiProgramService.saveStudijskiProgram(studijskiProgramMapper.toEntity(request));
        return studijskiProgram.getId();
    }

    @GetMapping(path = "/")
    public List<StudijskiProgramResponse> getAllStudijskiProgram(){
        List<StudijskiProgram> studijskiProgramList = studijskiProgramService.getAllStudijskiProgram();
        return studijskiProgramMapper.toResponseList(studijskiProgramList);
    }

    @GetMapping(path = "/{id}")
    public StudijskiProgramResponse getStudijskiProgram(@PathVariable Long id){
        StudijskiProgram studijskiProgram = studijskiProgramService.getStudijskiProgram(id);
        Long predmetCount = predmetService.countByStudijskiProgramId(id);

        return studijskiProgramMapper.toResponse(studijskiProgram, predmetCount);
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteStudijskiProgram(@PathVariable Long id){
        studijskiProgramService.deleteStudijskiProgram(id);
        return true;
    }

    @PatchMapping(path = "/{id}")
    public StudijskiProgramResponse updateStudijskiProgram(@PathVariable Long id, @Valid @RequestBody StudijskiProgramRequest request){
        StudijskiProgram studijskiProgram = studijskiProgramMapper.toEntity(request);

        StudijskiProgram updated = studijskiProgramService.updateStudijskiProgram(id, studijskiProgram);
        Long predmetCount = predmetService.countByStudijskiProgramId(id);
        return studijskiProgramMapper.toResponse(updated, predmetCount);
    }

    @GetMapping(path = "/{id}/predmet")
    public List<PredmetResponse> getPredmetiForStudijskiProgram(@PathVariable Long id){
        studijskiProgramService.getStudijskiProgram(id); // check if exists

        return predmetMapper.toResponseList(predmetService.getPredmetForStudijskiProgram(id));
    }
}
