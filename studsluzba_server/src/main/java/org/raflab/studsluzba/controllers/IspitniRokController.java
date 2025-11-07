package org.raflab.studsluzba.controllers;

import org.raflab.studsluzba.controllers.request.IspitniRokRequest;
import org.raflab.studsluzba.controllers.response.IspitniRokResponse;
import org.raflab.studsluzba.mappers.IspitniRokMapper;
import org.raflab.studsluzba.model.IspitniRok;
import org.raflab.studsluzba.services.IspitniRokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/ispitni-rok")
public class IspitniRokController {
    @Autowired
    private IspitniRokMapper ispitniRokMapper;

    @Autowired
    private IspitniRokService ispitniRokService;

    @GetMapping(path = "/")
    public List<IspitniRokResponse> getAllIspitniRok() {
        return ispitniRokMapper.toResponseList(ispitniRokService.getAllIspitniRok());
    }

    @GetMapping(path = "/{id}")
    public IspitniRokResponse getIspitniRokById(@PathVariable Long id) {
        return ispitniRokMapper.toResponse(ispitniRokService.getIspitniRok(id));
    }

    @PostMapping(path = "/")
    public Long getAllIspitniRok(@Valid @RequestBody IspitniRokRequest request) {
        IspitniRok ispitniRok = ispitniRokMapper.toEntity(request);
        return ispitniRokService.saveIspitniRok(ispitniRok).getId();
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteIspitniRok(@PathVariable Long id) {
        ispitniRokService.deleteIspitniRok(id);
        return true;
    }

    @PatchMapping(path = "/{id}")
    public IspitniRokResponse updateIspitniRok(@PathVariable Long id, @Valid @RequestBody IspitniRokRequest request) {
        IspitniRok ispitniRok = ispitniRokService.updateIspitniRok(id, ispitniRokMapper.toEntity(request));
        return ispitniRokMapper.toResponse(ispitniRok);
    }
}
