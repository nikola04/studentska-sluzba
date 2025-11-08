package org.raflab.studsluzba.controllers;

import org.raflab.studsluzba.controllers.request.IspitIzlazakRequest;
import org.raflab.studsluzba.controllers.request.StudentIndeksRequest;
import org.raflab.studsluzba.controllers.request.StudentPodaciRequest;
import org.raflab.studsluzba.controllers.response.*;
import org.raflab.studsluzba.mappers.*;
import org.raflab.studsluzba.model.IspitIzlazak;
import org.raflab.studsluzba.model.PredmetSlusa;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.model.StudentPodaci;
import org.raflab.studsluzba.services.*;
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
    private StudentIndeksMapper studentIndeksMapper;
    @Autowired
    private SlusaPredmetMapper slusaPredmetMapper;
    @Autowired
    private IspitPrijavaMapper ispitPrijavaMapper;
    @Autowired
    private IspitIzlazakMapper ispitIzlazakMapper;

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentIndeksService studentIndeksService;
    @Autowired
    private SlusaPredmetService slusaPredmetService;
    @Autowired
    private IspitPrijavaService ispitPrijavaService;
    @Autowired
    private IspitIzlazakService ispitIzlazakService;

    /* StudentPodaci */
    @PostMapping(path="/podaci")
	public Long addNewStudentPodaci(@Valid @RequestBody StudentPodaciRequest studentPodaci) {
		StudentPodaci sp = studentService.saveStudentPodaci(studentMapper.toEntity(studentPodaci));
		return sp.getId();
	}

    @GetMapping(path="/podaci")
    public List<StudentPodaciResponse> getAllStudentPodaci() {
        return studentMapper.toResponseList(studentService.getAllStudentPodaci());
    }

    @GetMapping(path="/podaci/{id}")
    public StudentPodaciResponse getStudentPodaciById(@PathVariable Long id) {
        return studentMapper.toResponse(studentService.getStudentPodaci(id));
    }

    @DeleteMapping(path = "/podaci/{id}")
    public boolean deleteStudentPodaci(@PathVariable Long id) {
        studentService.deleteStudentPodaci(id);
        return true;
    }

    @PatchMapping(path = "/podaci/{id}")
    public StudentPodaciResponse updateStudentPodaci(@PathVariable Long id, @Valid @RequestBody StudentPodaciRequest request) {
        StudentPodaci studentPodaci = studentMapper.toEntity(request);
        StudentPodaci updated = studentService.updateStudentPodaci(id, studentPodaci);
        return studentMapper.toResponse(updated);
    }

    /* Student Index */
    @GetMapping(path = "/podaci/{studentId}/indeks")
    public List<StudentIndeksResponse> getStudentIndeksi(@PathVariable Long studentId) {
        List<StudentIndeks> indeksList = studentIndeksService.getAllStudentIndeks(studentId);
        return studentIndeksMapper.toResponseList(indeksList);
    }

    @GetMapping(path = "/indeks/{indeksId}")
    public StudentIndeksResponse getStudentIndeksById(@PathVariable Long indeksId) {
        StudentIndeks indeks = studentIndeksService.getStudentIndeks(indeksId);
        return studentIndeksMapper.toResponse(indeks);
    }


    @PostMapping(path = "/podaci/{studentId}/indeks")
    public Long addNewStudentIndeks(@PathVariable Long studentId, @Valid @RequestBody StudentIndeksRequest request) {
        StudentIndeks indeks = studentIndeksMapper.toEntity(request);
        return studentIndeksService.saveStudentIndeks(indeks, studentId, request.getStudProgramId()).getId();
    }


    @DeleteMapping(path = "/indeks/{indeksId}")
    public boolean deleteStudentIndeks(@PathVariable Long indeksId) {
        studentIndeksService.deleteStudentIndeks(indeksId);
        return true;
    }

    /* PredmetSlusa */
    @GetMapping(path = "/indeks/{studentId}/predmet")
    public List<SlusaPredmetResponse> getSlusaPredmet(@PathVariable Long studentId) {
        List<PredmetSlusa> predmetSlusaList = slusaPredmetService.getAllSlusaPredmet(studentId);
        return slusaPredmetMapper.toResponseList(predmetSlusaList);
    }

    @PostMapping(path = "/indeks/{studentId}/predmet/{drziPredmetId}")
    public Long addNewStudentPredmet(@PathVariable Long studentId, @PathVariable Long drziPredmetId){
        return slusaPredmetService.saveSlusaPredmet(studentId, drziPredmetId).getId();
    }

    @DeleteMapping(path = "/indeks/{studentId}/predmet/{drziPredmetId}")
    public boolean deleteStudentPredmet(@PathVariable Long studentId, @PathVariable Long drziPredmetId){
        slusaPredmetService.deleteSlusaPredmet(studentId, drziPredmetId);
        return true;
    }

    /* IspitPrijava */
    @GetMapping(path = "/indeks/{studentId}/ispit/{ispitId}")
    public IspitPrijavaResponse getIspitPrijava(@PathVariable Long studentId, @PathVariable Long ispitId){
        return ispitPrijavaMapper.toResponse(ispitPrijavaService.getIspitPrijavaByStudentIndeksIdAndIspitId(ispitId, studentId));
    }

    @GetMapping(path = "/indeks/{studentId}/ispit/")
    public List<IspitPrijavaResponse> getAllIspitPrijava(@PathVariable Long studentId){
        return ispitPrijavaMapper.toResponseList(ispitPrijavaService.getAllIspitPrijavaByStudentIndeksId(studentId));
    }

    @PostMapping(path = "/indeks/{studentId}/ispit/{ispitId}/prijava")
    public IspitPrijavaResponse saveIspitPrijava(@PathVariable Long studentId, @PathVariable Long ispitId) {
        return ispitPrijavaMapper.toResponse(ispitPrijavaService.saveIspitPrijava(ispitId, studentId));
    }

    @DeleteMapping(path = "/indeks/{studentId}/ispit/{ispitId}/prijava")
    public boolean deleteIspitPrijava(@PathVariable Long studentId, @PathVariable Long ispitId) {
        ispitPrijavaService.deleteIspitPrijava(ispitId, studentId);
        return true;
    }

    /* IspitIzlazak */
    @PostMapping(path = "/indeks/{studentId}/ispit/{ispitId}/izlazak")
    public Long saveIspitIzlazak(@PathVariable Long studentId, @PathVariable Long ispitId, @Valid @RequestBody IspitIzlazakRequest request) {
        IspitIzlazak ispitIzlazak = ispitIzlazakMapper.toEntity(request);
        return ispitIzlazakService.saveIspitIzlazak(ispitIzlazak, studentId, ispitId).getId();
    }

    @DeleteMapping(path = "/indeks/{studentId}/ispit/{ispitId}/izlazak")
    public boolean deleteIspitIzlazak(@PathVariable Long studentId, @PathVariable Long ispitId) {
        ispitIzlazakService.deleteIspitIzlazak(ispitId, studentId);
        return true;
    }
}
