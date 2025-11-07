package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.StudentPodaciRequest;
import org.raflab.studsluzba.controllers.response.StudentPodaciResponse;
import org.raflab.studsluzba.model.StudentPodaci;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapper {
    public StudentPodaci toEntity(StudentPodaciRequest request) {
        StudentPodaci student = new StudentPodaci();

        student.setIme(request.getIme());
        student.setPrezime(request.getPrezime());
        student.setSrednjeIme(request.getSrednjeIme());
        student.setJmbg(request.getJmbg());
        student.setDatumRodjenja(request.getDatumRodjenja());
        student.setPol(request.getPol());
        student.setMestoRodjenja(request.getMestoRodjenja());
        student.setMestoPrebivalista(request.getMestoPrebivalista());
        student.setAdresa(request.getAdresa());
        student.setMestoStanovanja(request.getMestoStanovanja());
        student.setAdresaStanovanja(request.getAdresaStanovanja());
        student.setDrzavaRodjenja(request.getDrzavaRodjenja());
        student.setDrzavljanstvo(request.getDrzavljanstvo());
        student.setNacionalnost(request.getNacionalnost());
        student.setFakultetEmail(request.getFakultetEmail());
        student.setPrivatniEmail(request.getPrivatniEmail());
        student.setBrojTelefonaMobilni(request.getBrojTelefonaMobilni());
        student.setBrojTelefonaFiksni(request.getBrojTelefonaFiksni());
        student.setBrojLicneKarte(request.getBrojLicneKarte());
        student.setLicnuKartuIzdao(request.getLicnuKartuIzdao());

        return student;
    }

    public StudentPodaciResponse toResponse(StudentPodaci studentPodaci) {
        StudentPodaciResponse student = new StudentPodaciResponse();

        student.setIme(studentPodaci.getIme());
        student.setPrezime(studentPodaci.getPrezime());
        student.setSrednjeIme(studentPodaci.getSrednjeIme());
        student.setJmbg(studentPodaci.getJmbg());
        student.setDatumRodjenja(studentPodaci.getDatumRodjenja());
        student.setPol(studentPodaci.getPol());
        student.setMestoRodjenja(studentPodaci.getMestoRodjenja());
        student.setMestoPrebivalista(studentPodaci.getMestoPrebivalista());
        student.setAdresa(studentPodaci.getAdresa());
        student.setMestoStanovanja(studentPodaci.getMestoStanovanja());
        student.setAdresaStanovanja(studentPodaci.getAdresaStanovanja());
        student.setDrzavaRodjenja(studentPodaci.getDrzavaRodjenja());
        student.setDrzavljanstvo(studentPodaci.getDrzavljanstvo());
        student.setNacionalnost(studentPodaci.getNacionalnost());
        student.setFakultetEmail(studentPodaci.getFakultetEmail());
        student.setPrivatniEmail(studentPodaci.getPrivatniEmail());
        student.setBrojTelefonaMobilni(studentPodaci.getBrojTelefonaMobilni());
        student.setBrojTelefonaFiksni(studentPodaci.getBrojTelefonaFiksni());
        student.setBrojLicneKarte(studentPodaci.getBrojLicneKarte());
        student.setLicnuKartuIzdao(studentPodaci.getLicnuKartuIzdao());

        return student;
    }

    public List<StudentPodaciResponse> toResponseList(Iterable<StudentPodaci> studentPodaciIterable) {
        List<StudentPodaciResponse> skolskaGodinaResponses = new ArrayList<>();
        studentPodaciIterable.forEach((skolskaGodina) -> {
            skolskaGodinaResponses.add(toResponse(skolskaGodina));
        });
        return skolskaGodinaResponses;
    }
}
