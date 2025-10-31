package org.raflab.studsluzba.services;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.DrziPredmetNewRequest;
import org.raflab.studsluzba.controllers.request.DrziPredmetRequest;
import org.raflab.studsluzba.model.DrziPredmet;
import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.repositories.DrziPredmetRepository;
import org.raflab.studsluzba.repositories.NastavnikRepository;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DrziPredmetService {

    final DrziPredmetRepository drziPredmetRepository;
    final PredmetRepository predmetRepository;
    final NastavnikRepository nastavnikRepository;

    @Transactional
    public void saveDrziPredmet(DrziPredmetRequest request) {

        List<DrziPredmetNewRequest> drziPredmetList = request.getDrziPredmet();
        List<DrziPredmetNewRequest> newDrziPredmetList = request.getNewDrziPredmet();

        // izvuci sve predmete iz baze po predmetId
        Map<Long, Predmet> predmetMap = predmetRepository.findByIdIn(
                drziPredmetList.stream().map(DrziPredmetNewRequest::getPredmetId).collect(Collectors.toList())
        ).stream().collect(Collectors.toMap(Predmet::getId, Function.identity()));

        // izvuci sve predmete iz baze po predmetNaziv za nove unose
        Map<String, Predmet> newPredmetMap = predmetRepository.findByNazivIn(
                newDrziPredmetList.stream().map(DrziPredmetNewRequest::getPredmetNaziv).collect(Collectors.toList())
        ).stream().collect(Collectors.toMap(Predmet::getNaziv, Function.identity()));

        // izvuci sve nastavnike iz baze po email
        List<String> allEmails = Stream.concat(
                drziPredmetList.stream().map(DrziPredmetNewRequest::getEmailNastavnik),
                newDrziPredmetList.stream().map(DrziPredmetNewRequest::getEmailNastavnik)
        ).distinct().collect(Collectors.toList());

        Map<String, Nastavnik> nastavnikMap = nastavnikRepository.findByEmailIn(allEmails)
                .stream().collect(Collectors.toMap(Nastavnik::getEmail, Function.identity()));

        // kreiranje liste DrziPredmet objekata za cuvanje
        List<DrziPredmet> drziPredmetEntities = new ArrayList<>();

        for (DrziPredmetNewRequest drziPredmetRequest : drziPredmetList) {
            Predmet predmet = predmetMap.get(drziPredmetRequest.getPredmetId());
            Nastavnik nastavnik = nastavnikMap.get(drziPredmetRequest.getEmailNastavnik());

            if (predmet != null && nastavnik != null) {
                DrziPredmet drziPredmet = new DrziPredmet();
                drziPredmet.setPredmet(predmet);
                drziPredmet.setNastavnik(nastavnik);
                drziPredmetEntities.add(drziPredmet);
            }
        }

        for (DrziPredmetNewRequest newDrziPredmetRequest : newDrziPredmetList) {
            Predmet predmet = newPredmetMap.get(newDrziPredmetRequest.getPredmetNaziv());
            Nastavnik nastavnik = nastavnikMap.get(newDrziPredmetRequest.getEmailNastavnik());

            if (predmet != null && nastavnik != null) {
                DrziPredmet drziPredmet = new DrziPredmet();
                drziPredmet.setPredmet(predmet);
                drziPredmet.setNastavnik(nastavnik);
                drziPredmetEntities.add(drziPredmet);
            }
        }
        System.out.println("BROJ DrziPredmet za cuvanje: " + drziPredmetEntities.size());

        drziPredmetRepository.saveAll(drziPredmetEntities);
    }
}