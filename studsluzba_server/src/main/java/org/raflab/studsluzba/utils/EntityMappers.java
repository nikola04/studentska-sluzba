package org.raflab.studsluzba.utils;

import org.raflab.studsluzba.controllers.response.StudentIndeksResponse;
import org.raflab.studsluzba.controllers.response.StudentPodaciResponse;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.model.StudentPodaci;
import org.raflab.studsluzba.model.dtos.StudentDTO;
import org.springframework.stereotype.Component;

@Component
public class EntityMappers {

	public static StudentDTO fromStudentPodaciToDTO(StudentPodaci sp) {
		StudentDTO s = new StudentDTO();
		s.setIdStudentPodaci(sp.getId());
		s.setIme(sp.getIme());
		s.setPrezime(sp.getPrezime());
		return s;

	}

	public static StudentDTO fromStudentIndeksToDTO(StudentIndeks si) {
		StudentDTO s = fromStudentPodaciToDTO(si.getStudent());
		s.setIdIndeks(si.getId());
		s.setGodinaUpisa(si.getGodina());
		s.setBroj(si.getBroj());
		s.setStudProgramOznaka(si.getStudProgramOznaka());
		s.setAktivanIndeks(si.isAktivan());
		return s;

	}

	public StudentIndeksResponse fromStudentIndexToResponse(StudentIndeks si) {
		if (si == null) {
			return null;
		}
		StudentIndeksResponse response = new StudentIndeksResponse();
		response.setId(si.getId());
		response.setBroj(si.getBroj());
		response.setGodina(si.getGodina());
		response.setStudProgramOznaka(si.getStudProgramOznaka());
		response.setNacinFinansiranja(si.getNacinFinansiranja());
		response.setAktivan(si.isAktivan());
		response.setVaziOd(si.getVaziOd());
		response.setOstvarenoEspb(si.getOstvarenoEspb());
		response.setStudijskiProgram(si.getStudijskiProgram());
		response.setStudent(si.getStudent());
		return response;
	}

	public StudentPodaciResponse fromStudentPodaciToResponse(StudentPodaci sp) {
		if (sp == null) {
			return null;
		}

		StudentPodaciResponse response = new StudentPodaciResponse();
		response.setId(sp.getId());
		response.setIme(sp.getIme());
		response.setPrezime(sp.getPrezime());
		response.setSrednjeIme(sp.getSrednjeIme());
		response.setJmbg(sp.getJmbg());
		response.setDatumRodjenja(sp.getDatumRodjenja());
		response.setMestoRodjenja(sp.getMestoRodjenja());
		response.setMestoPrebivalista(sp.getMestoPrebivalista());
		response.setDrzavaRodjenja(sp.getDrzavaRodjenja());
		response.setDrzavljanstvo(sp.getDrzavljanstvo());
		response.setNacionalnost(sp.getNacionalnost());
		response.setPol(sp.getPol());
		response.setAdresa(sp.getAdresa());
		response.setBrojTelefonaMobilni(sp.getBrojTelefonaMobilni());
		response.setBrojTelefonaFiksni(sp.getBrojTelefonaFiksni());
		response.setEmail(sp.getEmail());
		response.setBrojLicneKarte(sp.getBrojLicneKarte());
		response.setLicnuKartuIzdao(sp.getLicnuKartuIzdao());
		response.setMestoStanovanja(sp.getMestoStanovanja());
		response.setAdresaStanovanja(sp.getAdresaStanovanja());

		return response;
	}
}
