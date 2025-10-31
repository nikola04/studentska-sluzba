package org.raflab.studsluzba.controllers;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.DrziPredmetRequest;
import org.raflab.studsluzba.services.DrziPredmetService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/admin/drzi-predmet")
@AllArgsConstructor
public class DrziPredmetController {

    DrziPredmetService drziPredmetService;

    @PostMapping(path = "/add")
    public void saveDrziPredmet(@RequestBody DrziPredmetRequest request) {
        drziPredmetService.saveDrziPredmet(request);
    }
}
