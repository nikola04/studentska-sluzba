package org.raflab.studsluzba.controllers;

import org.raflab.studsluzba.model.VrstaStudija;
import org.raflab.studsluzba.services.VrstaStudijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="/api/vrsta-studija")
public class VrstaStudijaController {
    @Autowired
    private VrstaStudijaService vrstaStudijaService;

    @PostMapping(path = "")
    public Long addNewVrstaStudija(@Valid @RequestBody VrstaStudija vrstaStudija) {
        return vrstaStudijaService.createVrstaStudija(vrstaStudija).getId();
    }

    @GetMapping(path = "")
    public List<VrstaStudija> getAllVrstaStudija() {
        return vrstaStudijaService.getAllVrstaStudija();
    }

    @GetMapping(path = "/{id}")
    public VrstaStudija getVrstaStudijaById(@PathVariable Long id) {
        return vrstaStudijaService.getVrstaStudija(id);
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteVrstaStudija(@PathVariable Long id){
        vrstaStudijaService.deleteVrstaStudija(id);
        return true;
    }
}
