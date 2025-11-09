package org.raflab.studsluzba.controllers;

import org.raflab.studsluzba.controllers.request.IspitRequest;
import org.raflab.studsluzba.controllers.response.IspitResponse;
import org.raflab.studsluzba.controllers.response.StudentIndeksResponse;
import org.raflab.studsluzba.mappers.IspitMapper;
import org.raflab.studsluzba.mappers.StudentIndeksMapper;
import org.raflab.studsluzba.model.Ispit;
import org.raflab.studsluzba.services.IspitService;
import org.raflab.studsluzba.services.StudentIndeksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/ispit")
public class IspitController {
    @Autowired
    private IspitMapper ispitMapper;
    @Autowired
    private StudentIndeksMapper studentIndeksMapper;

    @Autowired
    private StudentIndeksService studentIndeksService;
    @Autowired
    private IspitService ispitService;

    @PostMapping(path = "/")
    public Long addNewIspit(@Valid @RequestBody IspitRequest request) {
        Ispit ispit = ispitMapper.toEntity(request);
        return ispitService.saveIspit(ispit, request.getPredmetId(), request.getNastavnikId(), request.getIspitniRokId()).getId();
    }

    @GetMapping(path = "/")
    public List<IspitResponse> getAllIspiti(){
        return ispitMapper.toResponseList(ispitService.getAllIspit());
    }

    @GetMapping(path = "/{id}")
    public IspitResponse getIspitById(@PathVariable Long id){
        return ispitMapper.toResponse(ispitService.getIspit(id));
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteIspit(@PathVariable Long id){
        ispitService.deleteIspit(id);
        return true;
    }

    @PatchMapping(path = "/{id}")
    public IspitResponse updateIspit(@PathVariable Long id, @Valid @RequestBody IspitRequest request){
        Ispit ispit = ispitMapper.toEntity(request);
        return ispitMapper.toResponse(ispitService.updateIspit(id, ispit, request.getPredmetId(), request.getNastavnikId(), request.getIspitniRokId()));
    }

    @GetMapping(path = "/{id}/student")
    public List<StudentIndeksResponse> getStudentsByIspitId(@PathVariable Long id){
        return studentIndeksMapper.toResponseList(studentIndeksService.getAlStudentIndeksByIspitPrijava(id));
    }

    @GetMapping(path = "/{id}/average-ocena")
    public Double getAverageOcenaByIspitId(@PathVariable Long id){
        return ispitService.getAverageOcega(id);
    }
}
