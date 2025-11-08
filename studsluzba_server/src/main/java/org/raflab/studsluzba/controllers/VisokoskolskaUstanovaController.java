package org.raflab.studsluzba.controllers;

import org.raflab.studsluzba.model.VisokoskolskaUstanova;
import org.raflab.studsluzba.services.VisokoskolskaUstanovaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="/api/visokoskolska-ustanova")
public class VisokoskolskaUstanovaController {
    @Autowired
    private VisokoskolskaUstanovaService visokoskolskaUstanovaService;

    @PostMapping(path = "")
    public Long addNewVisokoskolskaUstanova(@Valid @RequestBody VisokoskolskaUstanova visokoskolskaUstanova) {
        return visokoskolskaUstanovaService.createVisokoskolskaUstanova(visokoskolskaUstanova).getId();
    }

    @GetMapping(path = "")
    public List<VisokoskolskaUstanova> getAllVisokoskolskaUstanova() {
        return visokoskolskaUstanovaService.getAllVisokoskolskaUstanova();
    }

    @GetMapping(path = "/{id}")
    public VisokoskolskaUstanova getVisokoskolskaUstanovaById(@PathVariable Long id) {
        return visokoskolskaUstanovaService.getVisokoskolskaUstanova(id);
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteVisokoskolskaUstanova(@PathVariable Long id){
        visokoskolskaUstanovaService.deleteVisokoskolskaUstanova(id);
        return true;
    }
}
