package org.raflab.studsluzba.controllers;

import org.raflab.studsluzba.model.SrednjaSkola;
import org.raflab.studsluzba.services.SrednjaSkolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="/api/srednja-skola")
public class SrednjaSkolaController {
    @Autowired
    private SrednjaSkolaService srednjaSkolaService;

    @PostMapping(path = "")
    public Long addNewSrednjaSkola(@Valid @RequestBody SrednjaSkola srednjaSkola) {
        return srednjaSkolaService.createSrednjaSkola(srednjaSkola).getId();
    }

    @GetMapping(path = "")
    public List<SrednjaSkola> getAllSrednjaSkola() {
        return srednjaSkolaService.getAllSrednjaSkola();
    }

    @GetMapping(path = "/{id}")
    public SrednjaSkola getSrednjaSkolaById(@PathVariable Long id) {
        return srednjaSkolaService.getSrednjaSkola(id);
    }


    @DeleteMapping(path = "/{id}")
    public boolean deleteSrednjaSkola(@PathVariable Long id){
        srednjaSkolaService.deleteSrednjaSkola(id);
        return true;
    }
}
