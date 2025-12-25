package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.StudentPodaciRequest;
import org.raflab.studsluzba.controllers.response.PagedResponse;
import org.raflab.studsluzba.controllers.response.StudentPodaciResponse;
import org.raflab.studsluzba.model.StudentPodaci;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {
    @Autowired
    private SrednjaSkolaMapper srednjaSkolaMapper;

    public StudentPodaci toEntity(StudentPodaciRequest request) {
        StudentPodaci student = new StudentPodaci();

        student.setIme(request.getIme());
        student.setPrezime(request.getPrezime());
        student.setSrednjeIme(request.getSrednjeIme());
        student.setJmbg(request.getJmbg());
        student.setGodinaUpisa(request.getGodinaUpisa());
        student.setDatumRodjenja(request.getDatumRodjenja());
        student.setPol(request.getPol());
        student.setMestoRodjenja(request.getMestoRodjenja());
//        student.setMestoPrebivalista(request.getMestoPrebivalista());
//        student.setAdresa(request.getAdresa());
        student.setMestoStanovanja(request.getMestoStanovanja());
        student.setAdresaStanovanja(request.getAdresaStanovanja());
        student.setDrzavaRodjenja(request.getDrzavaRodjenja());
        student.setDrzavljanstvo(request.getDrzavljanstvo());
        student.setNacionalnost(request.getNacionalnost());
        student.setFakultetEmail(request.getFakultetEmail());
        student.setPrivatniEmail(request.getPrivatniEmail());
        student.setBrojTelefonaMobilni(request.getBrojTelefonaMobilni());
        student.setBrojLicneKarte(request.getBrojLicneKarte());
        student.setUspehPrijemni(request.getUspehPrijemni());
        student.setUspehSrednjaSkola(request.getUspehSrednjaSkola());

        return student;
    }

    public StudentPodaciResponse toResponse(StudentPodaci studentPodaci) {
        StudentPodaciResponse studentResponse = new StudentPodaciResponse();

        studentResponse.setId(studentPodaci.getId());
        studentResponse.setIme(studentPodaci.getIme());
        studentResponse.setPrezime(studentPodaci.getPrezime());
        studentResponse.setSrednjeIme(studentPodaci.getSrednjeIme());
        studentResponse.setJmbg(studentPodaci.getJmbg());
        studentResponse.setGodinaUpisa(studentPodaci.getGodinaUpisa());
        studentResponse.setDatumRodjenja(studentPodaci.getDatumRodjenja());
        studentResponse.setPol(studentPodaci.getPol());
        studentResponse.setMestoRodjenja(studentPodaci.getMestoRodjenja());
        studentResponse.setMestoStanovanja(studentPodaci.getMestoStanovanja());
        studentResponse.setAdresaStanovanja(studentPodaci.getAdresaStanovanja());
        studentResponse.setDrzavaRodjenja(studentPodaci.getDrzavaRodjenja());
        studentResponse.setDrzavljanstvo(studentPodaci.getDrzavljanstvo());
        studentResponse.setNacionalnost(studentPodaci.getNacionalnost());
        studentResponse.setFakultetEmail(studentPodaci.getFakultetEmail());
        studentResponse.setPrivatniEmail(studentPodaci.getPrivatniEmail());
        studentResponse.setBrojTelefonaMobilni(studentPodaci.getBrojTelefonaMobilni());
        studentResponse.setBrojLicneKarte(studentPodaci.getBrojLicneKarte());
        studentResponse.setLicnuKartuIzdao(studentPodaci.getLicnuKartuIzdao());
        studentResponse.setUspehPrijemni(studentPodaci.getUspehPrijemni());
        studentResponse.setUspehSrednjaSkola(studentPodaci.getUspehSrednjaSkola());
        studentResponse.setSrednjaSkola(srednjaSkolaMapper.toResponse(studentPodaci.getSrednjaSkola()));
        studentResponse.setVisokoskolskaUstanova(studentPodaci.getVisokoskolskaUstanova());

        return studentResponse;
    }

    public List<StudentPodaciResponse> toResponseList(List<StudentPodaci> studentPodaciList) {
        return studentPodaciList.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public PagedResponse<StudentPodaciResponse> toPagedResponse(Page<StudentPodaci> page) {
        List<StudentPodaciResponse> content = toResponseList(page.getContent());

        return new PagedResponse<>(
            content,
            page.getNumber(),
            page.getSize(),
            page.getTotalElements(),
            page.getTotalPages(),
            page.isLast()
        );
    }
}
