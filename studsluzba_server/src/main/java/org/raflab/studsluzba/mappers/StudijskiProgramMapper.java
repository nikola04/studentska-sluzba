package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.StudijskiProgramRequest;
import org.raflab.studsluzba.controllers.response.StudijskiProgramResponse;
import org.raflab.studsluzba.model.StudijskiProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudijskiProgramMapper {
    @Autowired
    private PredmetMapper predmetMapper;

    public StudijskiProgram toEntity(StudijskiProgramRequest request) {
        StudijskiProgram studijskiProgram = new StudijskiProgram();

        studijskiProgram.setOznaka(request.getOznaka());
        studijskiProgram.setNaziv(request.getNaziv());
        studijskiProgram.setGodinaAkreditacije(request.getGodinaAkreditacije());
        studijskiProgram.setZvanje(request.getZvanje());
        studijskiProgram.setTrajanjeGodina(request.getTrajanjeGodina());
        studijskiProgram.setTrajanjeSemestara(request.getTrajanjeSemestara());
        studijskiProgram.setVrstaStudija(request.getVrstaStudija());
        studijskiProgram.setUkupnoEspb(request.getUkupnoEspb());

        return studijskiProgram;
    }

    public StudijskiProgramResponse toResponse(StudijskiProgram studijskiProgram) {
        StudijskiProgramResponse studijskiProgramResponse = new StudijskiProgramResponse();

        studijskiProgramResponse.setOznaka(studijskiProgram.getOznaka());
        studijskiProgramResponse.setNaziv(studijskiProgram.getNaziv());
        studijskiProgramResponse.setGodinaAkreditacije(studijskiProgram.getGodinaAkreditacije());
        studijskiProgramResponse.setZvanje(studijskiProgram.getZvanje());
        studijskiProgramResponse.setTrajanjeGodina(studijskiProgram.getTrajanjeGodina());
        studijskiProgramResponse.setTrajanjeSemestara(studijskiProgram.getTrajanjeSemestara());
        studijskiProgramResponse.setVrstaStudija(studijskiProgram.getVrstaStudija());
        studijskiProgramResponse.setUkupnoEspb(studijskiProgram.getUkupnoEspb());

        return studijskiProgramResponse;
    }

    public StudijskiProgramResponse toResponse(StudijskiProgram studijskiProgram, Long predmetCount) {
        StudijskiProgramResponse response = toResponse(studijskiProgram);
        response.setPredmetiSize(predmetCount);

        return response;
    }

    public List<StudijskiProgramResponse> toResponseList(Iterable<StudijskiProgram> studijskiProgramIterable) {
        List<StudijskiProgramResponse> studijskiProgramResponses = new ArrayList<>();
        studijskiProgramIterable.forEach((studijskiProgram) -> {
            studijskiProgramResponses.add(toResponse(studijskiProgram));
        });
        return studijskiProgramResponses;
    }
}
