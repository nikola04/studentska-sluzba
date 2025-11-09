package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.controllers.request.NastavnikObrazovanjeRequest;
import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Component
public class Seeder implements CommandLineRunner {

    @Autowired private VrstaStudijaService vrstaStudijaService;
    @Autowired private VisokoskolskaUstanovaService visokoskolskaUstanovaService;
    @Autowired private SrednjaSkolaService srednjaSkolaService;
    @Autowired private SkolskaGodinaService skolskaGodinaService;
    @Autowired private StudijskiProgramService studijskiProgramService;
    @Autowired private PredmetService predmetService;
    @Autowired private NastavnikService nastavnikService;
    @Autowired private StudentService studentService;
    @Autowired private StudentIndeksService studentIndeksService;
    @Autowired private DrziPredmetService drziPredmetService;
    @Autowired private SlusaPredmetService slusaPredmetService;
    @Autowired private VrstaStudijaRepository vrstaStudijaRepo;
    @Autowired private DrziPredmetRepository drziPredmetRepo; // Potreban za dohvatanje posle save-a
    @Autowired private GrupaRepository grupaRepository;
    @Autowired private IspitniRokService ispitniRokService;
    @Autowired private IspitService ispitService;
    @Autowired private IspitPrijavaService ispitPrijavaService;
    @Autowired private PredispitnaObavezaService predispitnaObavezaService;
    @Autowired private UplataService uplataService;

    private VrstaStudija oas;
    private VisokoskolskaUstanova fon;
    private SrednjaSkola gim;
    private SkolskaGodina sgAktivna;
    private static final Logger logger = LoggerFactory.getLogger(Seeder.class);
    @Override
    public void run(String... args) throws Exception {
        if (vrstaStudijaRepo.count() == 0) {
            logger.info("Baza je prazna unosim podatke");
            try {
                seedData();
                logger.info("--- Seed uspesno zavrsen ---");
            } catch (Exception e) {
                logger.error("!!! Greska tokom seed-a ", e);
            }
        } else {
            logger.info("Baza ima podatke seeder se preskace");
        }

    }

    private void seedData() {
        logger.info("Seedujem prvi sloj podataka");
        VrstaStudija vOas = new VrstaStudija();
        vOas.setNaziv("Osnovne akademske studije");
        vOas.setOznaka("OAS");
        oas = vrstaStudijaService.createVrstaStudija(vOas);
        VisokoskolskaUstanova vFon = new VisokoskolskaUstanova();
        vFon.setNaziv("Univerzitet u Beogradu - Fakultet organizacionih nauka");
        fon = visokoskolskaUstanovaService.createVisokoskolskaUstanova(vFon);
        SrednjaSkola sGim = new SrednjaSkola();
        sGim.setNaziv("Gimnazija Smederevska Palanka");
        sGim.setMesto("Smederevska Palanka");
        sGim.setTipSkole(TipSkole.GIMNAZIJA);
        gim = srednjaSkolaService.createSrednjaSkola(sGim);
        SkolskaGodina sSg2023 = new SkolskaGodina();
        sSg2023.setGodina(2023);
        sSg2023.setAktivan(false);
        skolskaGodinaService.saveSkolskaGodina(sSg2023);
        SkolskaGodina sSg2024 = new SkolskaGodina();
        sSg2024.setGodina(2024);
        sSg2024.setAktivan(true);
        sgAktivna = skolskaGodinaService.saveSkolskaGodina(sSg2024);

        logger.info("Seedujem drugi sloj.");
        List<StudijskiProgram> spList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            StudijskiProgram sp = new StudijskiProgram();
            sp.setOznaka("SP" + i);
            sp.setNaziv("Program " + i);
            sp.setGodinaAkreditacije(2020 + i);
            sp.setZvanje("Zvanje " + i);
            sp.setTrajanjeGodina(4);
            sp.setTrajanjeSemestara(8);
            sp.setUkupnoEspb(240);
            StudijskiProgram savedSp = studijskiProgramService.saveStudijskiProgram(sp, oas.getId());
            spList.add(savedSp);
        }
        logger.info("Seedujem treci sloj podataka");
        List<Predmet> predmetList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Predmet p = new Predmet();
            p.setSifra("PR" + i);
            p.setNaziv("Predmet " + i);
            p.setOpis("Opis predmeta " + i);
            p.setEspb(6 + i);
            p.setObavezan(i % 2 == 0);


            Long spId = spList.get((i - 1) % spList.size()).getId();
            Predmet savedP = predmetService.savePredmet(p, spId);
            predmetList.add(savedP);
        }
        logger.info("Seedujem cetvrti sloj podataka");
        List<Nastavnik> nastavnikList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Nastavnik n = new Nastavnik();
            n.setIme("Nastavnik" + i);
            n.setPrezime("Prezime" + i);
            n.setSrednjeIme("Srednje" + i);
            n.setEmail("nastavnik" + i + "@example.com");
            n.setBrojTelefona("06012345" + i);
            n.setAdresa("Adresa " + i);
            n.setDatumRodjenja(LocalDate.of(1980 + i, 1, i)); // Popravljen mesec (i ne može biti > 12)
            n.setPol(i % 2 == 0 ? 'M' : 'F');
            n.setJmbg("80010123456" + i);

            // KLJUČNA IZMENA: Pripremamo Zvanje iz starog koda
            NastavnikZvanje nz = new NastavnikZvanje();
            nz.setDatumIzbora(LocalDate.of(2020 + i, 1, i)); // Popravljen mesec
            nz.setNaucnaOblast("Oblast " + i);
            nz.setUzaNaucnaOblast("Uza oblast " + i);
            nz.setZvanje("Zvanje " + i);
            nz.setAktivno(i % 2 == 0);
            Set<NastavnikZvanje> zvanja = new HashSet<>(Collections.singletonList(nz));

            // KLJUČNA IZMENA: Pripremamo Obrazovanje (koje je nedostajalo) [cite: 156-158]
            NastavnikObrazovanjeRequest obr = new NastavnikObrazovanjeRequest();
            obr.setVisokoskolskaUstanovaId(fon.getId());
            obr.setVrstaStudijaId(oas.getId());
            Set<NastavnikObrazovanjeRequest> obrazovanja = new HashSet<>(Collections.singletonList(obr));

            // KLJUČNA IZMENA: Servis čuva Nastavnika, Zvanja i Obrazovanja odjednom [cite: 159]
            Nastavnik savedN = nastavnikService.saveNastavnik(n, zvanja, obrazovanja);
            nastavnikList.add(savedN);
        }
        logger.info("Seedujem 5 sloj podataka");
        List<StudentPodaci> studentPodaciList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            StudentPodaci s = new StudentPodaci();
            s.setIme("Student" + i);
            s.setPrezime("Prezime" + i);
            s.setSrednjeIme("Srednje" + i);
            s.setJmbg("00101012345" + i);
            s.setDatumRodjenja(LocalDate.of(2000 + i, 1, i)); // Popravljen mesec
            s.setMestoRodjenja("Mesto" + i);
            s.setMestoPrebivalista("Prebivaliste" + i);
            s.setDrzavaRodjenja("Srbija");
            s.setDrzavljanstvo("Srbija");
            s.setNacionalnost("Srpska");
            s.setPol(i % 2 == 0 ? 'F' : 'M');
            s.setAdresa("Adresa " + i);
            s.setBrojTelefonaMobilni("06123456" + i);
            s.setPrivatniEmail("student" + i + "@example.com");
            s.setFakultetEmail("student" + i + "@fakultet.com");

            // KLJUČNA IZMENA: Koristimo servis i prosleđujemo ID-jeve zavisnosti [cite: 266]
            // Koristimo 'gim' i 'fon' koje smo kreirali u Sloju 1
            StudentPodaci savedS = studentService.saveStudentPodaci(s, gim.getId(), fon.getId());
            studentPodaciList.add(savedS);
        }


        logger.info("Seedujem 6 sloj podataka");
        List<StudentIndeks> indeksList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            StudentIndeks si = new StudentIndeks();

            si.setNacinFinansiranja(i % 2 == 0 ? NacinFinansiranja.BUDZET : NacinFinansiranja.SAMOFINANSIRANJE);
            si.setGodina(1); // Postavljamo da su svi brucoši (upisali 1. godinu)
            si.setAktivan(true);
            si.setVaziOd(LocalDate.of(2023, 10, i));
            si.setOstvarenoEspb(0);

            Long studentId = studentPodaciList.get(i - 1).getId();
            Long spId = spList.get(i - 1).getId();

            StudentIndeks savedSi = studentIndeksService.saveStudentIndeks(si, studentId, spId);
            indeksList.add(savedSi);
        }

        logger.info("Seedujem 7 sloj podataka");
        List<DrziPredmet> drziPredmetList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Long nastavnikId = nastavnikList.get(i - 1).getId();
            Long predmetId = predmetList.get(i - 1).getId();

            Long skolaGodinaId = sgAktivna.getId();

            drziPredmetService.saveDrziPredmet(nastavnikId, predmetId, skolaGodinaId);

            DrziPredmet savedDp = drziPredmetRepo
                    .getDrziPredmetByPredmetIdNastavnikIdSkolskaGodinaId(predmetId, nastavnikId, skolaGodinaId);

            if (savedDp != null) {
                drziPredmetList.add(savedDp);
            }
        }

        logger.info("Seedujem  8 sloj podataka");
        for (int i = 1; i <= 5; i++) {
            Long studentIndeksId = indeksList.get(i - 1).getId();
            Long drziPredmetId = drziPredmetList.get(i - 1).getId();

            PredmetSlusa savedSl = slusaPredmetService.saveSlusaPredmet(studentIndeksId, drziPredmetId);
            // slusaPredmetList.add(savedSl);
        }


        logger.info("Seeding Sloj 9: Grupa (preko repoa - nema servisa)...");
        for (int i = 1; i <= 5; i++) {
            Grupa g = new Grupa();
            g.setStudijskiProgram(spList.get(i - 1));
            g.setPredmeti(Collections.singletonList(predmetList.get(i - 1)));
            grupaRepository.save(g);
        }
        // SLOJ 10: ISPITNI ROK
        logger.info("Seedujem Sloj 10: IspitniRok...");
        List<IspitniRok> ispitniRokList = new ArrayList<>();

        // AKTIVAN rok - TEKUĆA godina + BUDUĆI datumi
        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();

        IspitniRok aktivniRok = new IspitniRok();
        // Postavite BUDUĆE datume koji sigurno nisu prošli
        if (currentMonth <= 6) {
            // Ako smo u prvoj polovini godine - Junski rok
            aktivniRok.setPocetak(LocalDate.of(currentYear, 6, 1));
            aktivniRok.setKraj(LocalDate.of(currentYear, 6, 30));
        } else {
            // Ako smo u drugoj polovini godine - Januarski rok sledeće godine
            aktivniRok.setPocetak(LocalDate.of(currentYear + 1, 1, 10));
            aktivniRok.setKraj(LocalDate.of(currentYear + 1, 1, 30));
        }

        IspitniRok savedAktivni = ispitniRokService.saveIspitniRok(aktivniRok, sgAktivna.getId());
        ispitniRokList.add(savedAktivni);

        logger.info("Seedujem Sloj 11: Ispit...");
        List<Ispit> ispitList = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Ispit ispit = new Ispit();

            // Postavite datum između početka i kraja aktivnog roka
            ispit.setDatumOdrzavanja(savedAktivni.getPocetak().plusDays(5 + i)); // 6-10 dan roka
            ispit.setVremePocetka(LocalTime.of(9, 0));
            ispit.setZakljucen(false);

            Long predmetId = predmetList.get(i - 1).getId();
            Long nastavnikId = nastavnikList.get(i - 1).getId();
            Long ispitniRokId = savedAktivni.getId();

            Ispit savedIspit = ispitService.saveIspit(ispit, predmetId, nastavnikId, ispitniRokId);
            ispitList.add(savedIspit);
        }

        logger.info("Seedujem Sloj 12: IspitPrijava...");
        for (int i = 1; i <= 5; i++) {
            Long studentIndeksId = indeksList.get(i - 1).getId();
            Long ispitId = ispitList.get(i - 1).getId();
            ispitPrijavaService.saveIspitPrijava(ispitId, studentIndeksId);
        }

        logger.info("Seedujem Sloj 13: PredispitnaObaveza...");
        for (int i = 1; i <= 5; i++) {
            PredispitnaObaveza po = new PredispitnaObaveza();
            po.setVrsta(i % 2 == 0 ? PredispitneObavezeVrsta.TEST : PredispitneObavezeVrsta.KOLOKVIJUM);
            po.setMaxBrojPoena(20.0 + i);

            Long predmetId = predmetList.get(i - 1).getId();
            Long skolskaGodinaId = sgAktivna.getId();

            predispitnaObavezaService.savePredispitnaObaveza(po, predmetId, skolskaGodinaId);
        }

//        logger.info("Seedujem Sloj 14: UpisGodine i ObnovaGodine...");
//        for (int i = 1; i <= 5; i++) {
//            // UpisGodine
//            UpisGodine ug = new UpisGodine();
//            ug.setDatumUpisa(LocalDate.of(2023, 10, 1));
//            ug.setNapomena("Redovni upis " + i);
//            ug.setStudentIndeks(indeksList.get(i - 1));
//            ug.setSkolskaGodina(sgAktivna);
//            ug.setPredmeti(Collections.singletonList(predmetList.get(i - 1)));
//            upisGodineRepository.save(ug);
//
//            // ObnovaGodine (za neke studente)
//            if (i % 2 == 0) {
//                ObnovaGodine og = new ObnovaGodine();
//                og.setDatumObnove(LocalDate.of(2024, 2, 1));
//                og.setNapomena("Obnova godine " + i);
//                og.setStudentIndeks(indeksList.get(i - 1));
//                og.setSkolskaGodina(sgAktivna);
//                og.setPredmeti(Collections.singletonList(predmetList.get(i - 1)));
//                obnovaGodineRepository.save(og);
//            }
//     }
        logger.info("Seedujem Sloj 15: Uplata...");
        for (int i = 1; i <= 5; i++) {
            if (indeksList.get(i - 1).getNacinFinansiranja() == NacinFinansiranja.SAMOFINANSIRANJE) {
                Uplata uplata = new Uplata();
                uplata.setIznosDinari(50000.0 + (i * 10000));

                uplataService.saveUplata(uplata, indeksList.get(i - 1).getId());
            }

        }


    }
}