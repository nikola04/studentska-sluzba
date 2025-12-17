package org.raflab.studsluzba.controllers;

import org.raflab.studsluzba.controllers.request.*;
import org.raflab.studsluzba.controllers.response.*;
import org.raflab.studsluzba.mappers.*;
import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private StudentPredispitnaObavezaMapper studentPredispitnaObavezaMapper;
    @Autowired
    private PolozenPredmetMapper polozenPredmetMapper;
    @Autowired
    private UplataMapper uplataMapper;
    @Autowired
    private IspitMapper ispitMapper;
    @Autowired
    private UpisGodineMapper upisGodineMapper;
    @Autowired
    private ObnovaGodineMapper obnovaGodineMapper;

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
    @Autowired
    private StudentPredispitnaObavezaService studPredObavezaService;
    @Autowired
    private PolozenPredmetService polozenPredmetService;
    @Autowired
    private UplataService uplataService;
    @Autowired
    private IspitService ispitService;
    @Autowired
    private PredispitnaObavezaService predispitnaObavezaService;
    @Autowired
    private UpisGodineService upisGodineService;
    @Autowired
    private ObnovaGodineService obnovaGodineService;

    /* StudentPodaci */
    @PostMapping(path="/podaci")
	public Long addNewStudentPodaci(@Valid @RequestBody StudentPodaciRequest request) {
		StudentPodaci sp = studentService.saveStudentPodaci(studentMapper.toEntity(request), request.getSrednjaSkolaId(), request.getVisokoskolskaUstanovaId());
		return sp.getId();
	}

    @GetMapping(path="/podaci")
    public PagedResponse<StudentPodaciResponse> getAllStudentPodaci(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "prezime") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return studentMapper.toPagedResponse(studentService.getAllStudentPodaci(pageable));
    }

    @GetMapping(path="/podaci/search")
    public PagedResponse<StudentPodaciResponse> getAllStudentPodaciSearch(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String lastName,
        @RequestParam(required = false) String highSchoolName,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "prezime") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<StudentPodaci> studentPodaciPage = studentService.searchStudentPodaci(name, lastName, highSchoolName, pageable);

        return studentMapper.toPagedResponse(studentPodaciPage);
    }

    @GetMapping(path="/podaci/{id}")
    public StudentPodaciResponse getStudentPodaciById(@PathVariable Long id) {
        return studentMapper.toResponse(studentService.getStudentPodaci(id));
    }

    @GetMapping(path = "/podaci/indeks/{studentIndeksBroj}")
    public StudentPodaciResponse getStudentPodaciByIndeks(@PathVariable String studentIndeksBroj) {
        StudentIndeks studentIndeks = studentIndeksService.getStudentIndeksByBroj(studentIndeksBroj);
        return studentMapper.toResponse(studentIndeks.getStudent());
    }

    @DeleteMapping(path = "/podaci/{id}")
    public boolean deleteStudentPodaci(@PathVariable Long id) {
        studentService.deleteStudentPodaci(id);
        return true;
    }

    @PatchMapping(path = "/podaci/{id}")
    public StudentPodaciResponse updateStudentPodaci(@PathVariable Long id, @Valid @RequestBody StudentPodaciRequest request) {
        StudentPodaci studentPodaci = studentMapper.toEntity(request);
        StudentPodaci updated = studentService.updateStudentPodaci(id, studentPodaci, request.getSrednjaSkolaId(), request.getVisokoskolskaUstanovaId());
        return studentMapper.toResponse(updated);
    }

    /* Student Index */
    @GetMapping(path = "/podaci/{studentId}/indeks")
    public List<StudentIndeksResponse> getStudentIndeksi(@PathVariable Long studentId) {
        List<StudentIndeks> indeksList = studentIndeksService.getAllStudentIndeks(studentId);
        return studentIndeksMapper.toResponseList(indeksList);
    }

    @GetMapping(path = "/indeks/{studentIndeksBroj}")
    public StudentIndeksResponse getStudentIndeksById(@PathVariable String studentIndeksBroj) {
        Long studentIndeksId = studentIndeksService.getStudentIndeksByBroj(studentIndeksBroj).getId();
        StudentIndeks indeks = studentIndeksService.getStudentIndeks(studentIndeksId);
        return studentIndeksMapper.toResponse(indeks);
    }


    @PostMapping(path = "/podaci/{studentId}/indeks")
    public Long addNewStudentIndeks(@PathVariable Long studentId, @Valid @RequestBody StudentIndeksRequest request) {
        StudentIndeks indeks = studentIndeksMapper.toEntity(request);
        return studentIndeksService.saveStudentIndeks(indeks, studentId, request.getStudProgramId(), request.getNacinFinansiranjaId()).getId();
    }


    @DeleteMapping(path = "/indeks/{indeksId}")
    public boolean deleteStudentIndeks(@PathVariable Long indeksId) {
        studentIndeksService.deleteStudentIndeks(indeksId);
        return true;
    }

    /* UpisGodine */
    @PostMapping(path = "/indeks/{studentIndeksBroj}/upis/godina/{skolskaGodinaId}")
    public Long addNewUpisGodine(@PathVariable String studentIndeksBroj, @PathVariable Long skolskaGodinaId, @RequestParam(defaultValue = "") String napomena){
        Long studentIndeksId = studentIndeksService.getStudentIndeksByBroj(studentIndeksBroj).getId();
        return upisGodineService.saveUpisGodine(studentIndeksId, skolskaGodinaId, napomena).getId();
    }

    @GetMapping(path = "/indeks/{studentIndeksBroj}/upis")
    public List<UpisGodineResponse> getUpisGodine(@PathVariable String studentIndeksBroj){
        Long studentIndeksId = studentIndeksService.getStudentIndeksByBroj(studentIndeksBroj).getId();
        return upisGodineMapper.toResponseList(upisGodineService.getAllUpisGodineByStudentIndeksId(studentIndeksId));
    }

    /* ObnovaGodine */
    @PostMapping(path = "/indeks/{studentIndeksBroj}/obnova/godina/{skolskaGodinaId}")
    public Long addNewObnovaGodine(@PathVariable String studentIndeksBroj, @PathVariable Long skolskaGodinaId, @RequestParam(defaultValue = "") String napomena){
        Long studentIndeksId = studentIndeksService.getStudentIndeksByBroj(studentIndeksBroj).getId();
        return obnovaGodineService.saveObnovaGodine(studentIndeksId, skolskaGodinaId, napomena).getId();
    }

    @GetMapping(path = "/indeks/{studentIndeksBroj}/obnova")
    public List<ObnovaGodineResponse> getObnovaGodine(@PathVariable String studentIndeksBroj){
        Long studentIndeksId = studentIndeksService.getStudentIndeksByBroj(studentIndeksBroj).getId();
        return obnovaGodineMapper.toResponseList(obnovaGodineService.getAllObnovljenaGodinaByStudentIndeks(studentIndeksId));
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

    /* Ispit */
    @GetMapping(path = "/indeks/{studentIndeksBroj}/ispit/nepolozen")
    public PagedResponse<IspitResponse> getAllIspitNepolozen(@PathVariable String studentIndeksBroj,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Long studentIndeksId = studentIndeksService.getStudentIndeksByBroj(studentIndeksBroj).getId();
        return ispitMapper.toPagedResponse(ispitService.getNepolozeniPageByStudentId(studentIndeksId, pageable));
    }

    @GetMapping(path = "/indeks/{studentIndeksBroj}/ispit/polozen")
    public PagedResponse<IspitResponse> getAllIspitPolozen(@PathVariable String studentIndeksBroj,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Long studentIndeksId = studentIndeksService.getStudentIndeksByBroj(studentIndeksBroj).getId();
        return ispitMapper.toPagedResponse(ispitService.getPolozeniPageByStudentId(studentIndeksId, pageable));
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

    @PostMapping(path = "/indeks/{studentId}/ispit/rok/{ispitniRokId}/predmet/{predmetId}/prijava")
    public IspitPrijavaResponse saveIspitPrijavaByIspitniRok(@PathVariable Long studentId, @PathVariable Long ispitniRokId, @PathVariable Long predmetId) {
        return ispitPrijavaMapper.toResponse(ispitPrijavaService.saveIspitPrijava(studentId, predmetId, ispitniRokId));
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

    @GetMapping(path = "/indeks/{studentId}/ispit/predmet/{predmetId}/izlazak")
    public Integer getIspitIzlazak(@PathVariable Long studentId, @PathVariable Long predmetId){
        return ispitIzlazakService.getIzlazakCountForStudentByPredmetId(studentId, predmetId);
    }

    /* StudentPredispitnaObaveza */
    @GetMapping(path = "/indeks/{studentId}/predispitna")
    public List<StudentPredispitnaObavezaResponse> getAllStudentPredispitnaObaveza(@PathVariable Long studentId) {
        return studentPredispitnaObavezaMapper.toResponseList(studPredObavezaService.getAllStudentPredispitnaObaveza(studentId));
    }

    @GetMapping(path = "/indeks/{studentId}/predispitna/predmet/{predmetId}/godina/{godinaId}")
    public Double getAllStudentPredispitnaObaveza(@PathVariable Long studentId, @PathVariable Long predmetId, @PathVariable Long godinaId) {
        return predispitnaObavezaService.getPoeniForStudentIndeksByPredmetId(studentId, predmetId, godinaId);
    }

    @PostMapping(path = "/indeks/{studentId}/predispitna/{predispitnaId}")
    public Long newStudentPredispitnaObaveza(@PathVariable Long studentId, @PathVariable Long predispitnaId, @Valid @RequestBody StudentPredispitnaObavezaRequest request) {
        StudentPredispitnaObaveza predispitnaObaveza = studentPredispitnaObavezaMapper.toEntity(request);
        return studPredObavezaService.saveStudentPredispitnaObaveza(predispitnaObaveza, studentId, predispitnaId).getId();
    }

    @DeleteMapping(path = "/indeks/{studentId}/predispitna/{predispitnaId}")
    public boolean deleteStudentPredispitnaObaveza(@PathVariable Long studentId, @PathVariable Long predispitnaId) {
        studPredObavezaService.deleteStudentPredispitnaObaveza(studentId, predispitnaId);
        return true;
    }

    /* PolozenPredmet */
    @GetMapping(path = "/indeks/{studentId}/predmet/polozen")
    public List<PolozenPredmetResponse> getAllStudentPolozenPredmet(@PathVariable Long studentId) {
        return polozenPredmetMapper.toResponseList(polozenPredmetService.getAllPolozenPredmet(studentId));
    }

    @GetMapping(path = "/indeks/{studentId}/predmet/polozen/average-ocena")
    public Double getStudentAverageOcena(@PathVariable Long studentId) {
        Double avg = polozenPredmetService.getStudentAverageOcena(studentId);
        return avg == null ? 0 : avg;
    }

    @PostMapping(path = "/indeks/{studentId}/predmet/{predmetId}/polozen")
    public Long newStudentPolozenPredmet(@PathVariable Long studentId, @PathVariable Long predmetId, @Valid @RequestBody PolozenPredmetRequest request) {
        PolozenPredmet polozenPredmet = polozenPredmetMapper.toEntity(request);
        return polozenPredmetService.savePolozenPredmet(polozenPredmet, studentId, predmetId, request.getIspitIzlazakId()).getId();
    }

    @DeleteMapping(path = "/indeks/{studentIndeksId}/predmet/{predmetId}/polozen")
    public boolean deleteStudentPolozenPredmet(@PathVariable Long studentIndeksId, @PathVariable Long predmetId) {
        polozenPredmetService.deletePolozenPredmet(studentIndeksId, predmetId);
        return true;
    }

    /* Uplata */
    @PostMapping(path = "/indeks/{id}/uplata")
    public Long newUplata(@PathVariable Long id, @Valid @RequestBody UplataRequest request){
        Uplata uplata = uplataMapper.toEntity(request);
        return uplataService.saveUplata(uplata, id).getId();
    }

    @GetMapping(path = "/indeks/{id}/uplata")
    public List<UplataResponse> getAllUplata(@PathVariable Long id){
        return uplataMapper.toResponseList(uplataService.getAllUplata(id));
    }

    @GetMapping(path = "/indeks/{id}/uplata/preostalo")
    public Iznos getPreostaliIznos(@PathVariable Long id){
        return uplataService.getMissingAmount(id);
    }

    @GetMapping(path = "/indeks/{id}/uplata/{uplataId}")
    public UplataResponse getUplata(@PathVariable Long id, @PathVariable Long uplataId){
        return uplataMapper.toResponse(uplataService.getUplata(id, uplataId));
    }

    @DeleteMapping(path = "/indeks/{id}/uplata/{uplataId}")
    public boolean deleteUplata(@PathVariable Long id, @PathVariable Long uplataId){
        uplataService.deleteUplata(id, uplataId);
        return true;
    }
}
