package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Component
public class Seeder implements CommandLineRunner {

    @Autowired
    private VrstaStudijaRepository vrstaStudijaRepository;

    @Autowired
    private StudijskiProgramRepository studijskiProgramRepository;

    @Autowired
    private PredmetRepository predmetRepository;

    @Autowired
    private NastavnikRepository nastavnikRepository;

    @Autowired
    private StudentPodaciRepository studentPodaciRepository;

    @Autowired
    private NacinFinansiranjaRepository nacinFinansiranjaRepository;

    @Autowired
    private StudentIndeksRepository studentIndeksRepository;

    @Autowired
    private SkolskaGodinaRepository skolskaGodinaRepository;

    @Autowired
    private DrziPredmetRepository drziPredmetRepository;

    @Autowired
    private SlusaPredmetRepository slusaPredmetRepository;

    @Autowired
    private GrupaRepository grupaRepository;

    @Autowired
    private IspitniRokRepository ispitniRokRepository;

    @Autowired
    private IspitRepository ispitRepository;

    @Autowired
    private IspitPrijavaRepository ispitPrijavaRepository;

    @Autowired
    private ZvanjeRepository zvanjeRepository;

    @Autowired
    private NaucnaOblastRepository naucnaOblastRepository;

    @Autowired
    private UzaNaucnaOblastRepository uzaNaucnaOblastRepository;

    @Autowired
    private VisokoskolskaUstanovaRepository visokoskolskaUstanovaRepository;

    @Autowired
    private SrednjaSkolaRepository srednjaSkolaRepository;

    @Autowired
    private TipSkoleRepository tipSkoleRepository;

    @Autowired
    private PredispitneObavezeVrstaRepository predispitnaObavezaVrstaRepository;

    @Autowired
    private PredispitnaObavezaRepository predispitnaObavezaRepository;

    @Override
    public void run(String... args) throws Exception {
        // Kreiranje osnovnih podataka
        createVrsteStudija();
        createStudijskePrograme();
        createPredmete();
        createNastavnike();
        createStudente();
        createSkolskeGodine();
        createDrziPredmet();
        createSlusaPredmet();
        createGrupe();
        createIspitniRokove();
        createIspite();
        createIspitPrijave();
        createOstaliEntiteti();
    }

    private void createVrsteStudija() {
        if (vrstaStudijaRepository.count() == 0) {
            VrstaStudija vs1 = new VrstaStudija();
            vs1.setNaziv("Osnovne akademske studije");
            vs1.setOznaka("OAS");
            vrstaStudijaRepository.save(vs1);

            VrstaStudija vs2 = new VrstaStudija();
            vs2.setNaziv("Master akademske studije");
            vs2.setOznaka("MAS");
            vrstaStudijaRepository.save(vs2);

            VrstaStudija vs3 = new VrstaStudija();
            vs3.setNaziv("Doktorske studije");
            vs3.setOznaka("DS");
            vrstaStudijaRepository.save(vs3);
        }
    }

    private void createStudijskePrograme() {
        if (studijskiProgramRepository.count() == 0) {
            List<VrstaStudija> vrsteStudija = vrstaStudijaRepository.findAll();

            for (int i = 1; i <= 3; i++) {
                StudijskiProgram sp = new StudijskiProgram();
                sp.setOznaka("SP" + i);
                sp.setNaziv("Studijski program " + i);
                sp.setGodinaAkreditacije(2020 + i);
                sp.setZvanje("Diplomirani inženjer " + i);
                sp.setTrajanjeGodina(4);
                sp.setTrajanjeSemestara(8);
                sp.setUkupnoEspb(240);
                sp.setVrstaStudija(vrsteStudija.get(0));
                studijskiProgramRepository.save(sp);
            }
        }
    }

    private void createPredmete() {
        if (predmetRepository.count() == 0) {
            List<StudijskiProgram> studProgrami = studijskiProgramRepository.findAll();

            for (int i = 1; i <= 10; i++) {
                Predmet p = new Predmet();
                p.setSifra("PR" + i);
                p.setNaziv("Predmet " + i);
                p.setOpis("Opis predmeta " + i);
                p.setEspb(6);
                p.setObavezan(i % 2 == 0);
                p.setFondCasovaVezbe(30 + i);
                p.setFondCasovaPredavanja(45 + i);
                p.setSemestar((i % 6) + 1); // semestar od 1 do 6
                p.setStudProgram(studProgrami.get((i - 1) % studProgrami.size()));
                predmetRepository.save(p);
            }
        }
    }

    private void createNastavnike() {
        if (nastavnikRepository.count() == 0) {
            // Prvo kreiramo potrebne entitete za nastavnike
            Zvanje zvanje = createZvanje();
            NaucnaOblast naucnaOblast = createNaucnaOblast();
            UzaNaucnaOblast uzaNaucnaOblast = createUzaNaucnaOblast();
            VisokoskolskaUstanova visokoskolskaUstanova = createVisokoskolskaUstanova();
            VrstaStudija vrstaStudija = vrstaStudijaRepository.findAll().get(0);

            for (int i = 1; i <= 5; i++) {
                Nastavnik n = new Nastavnik();
                n.setIme("Nastavnik" + i);
                n.setPrezime("Prezime" + i);
                n.setSrednjeIme("Srednje" + i);
                n.setEmail("nastavnik" + i + "@example.com");
                n.setBrojTelefona("06012345" + i);
                n.setAdresa("Adresa " + i);
                n.setDatumRodjenja(LocalDate.of(1980 + i, i, i));
                n.setPol(i % 2 == 0 ? 'M' : 'F');
                n.setJmbg("80010123456" + i);

                // Kreiranje obrazovanja
                NastavnikObrazovanje obrazovanje = new NastavnikObrazovanje();
                obrazovanje.setVisokoskolskaUstanova(visokoskolskaUstanova);
                obrazovanje.setVrstaStudija(vrstaStudija);
                obrazovanje.setNastavnik(n);

                // Kreiranje zvanja
                NastavnikZvanje nastavnikZvanje = new NastavnikZvanje();
                nastavnikZvanje.setDatumIzbora(LocalDate.of(2020 + i, i, i));
                nastavnikZvanje.setNaucnaOblast(naucnaOblast);
                nastavnikZvanje.setUzaNaucnaOblast(uzaNaucnaOblast);
                nastavnikZvanje.setZvanje(zvanje);
                nastavnikZvanje.setAktivno(true);
                nastavnikZvanje.setNastavnik(n);

                n.setObrazovanja(Set.of(obrazovanje));
                n.setZvanja(Set.of(nastavnikZvanje));

                nastavnikRepository.save(n);
            }
        }
    }

    private void createStudente() {
        if (studentPodaciRepository.count() == 0) {
            // Kreiranje srednje škole i tipa škole
            TipSkole tipSkole = createTipSkole();
            SrednjaSkola srednjaSkola = createSrednjaSkola(tipSkole);
            NacinFinansiranja nacinFinansiranja = createNacinFinansiranja();
            List<StudijskiProgram> studProgrami = studijskiProgramRepository.findAll();

            for (int i = 1; i <= 10; i++) {
                // Kreiranje osnovnih podataka o studentu
                StudentPodaci s = new StudentPodaci();
                s.setIme("Student" + i);
                s.setPrezime("Prezime" + i);
                s.setSrednjeIme("Srednje" + i);
                s.setJmbg("00101012345" + i);
                s.setDatumRodjenja(LocalDate.of(2000 + i, i, i));
                s.setMestoRodjenja("Mesto" + i);
                s.setMestoPrebivalista("Prebivaliste" + i);
                s.setDrzavaRodjenja("Srbija");
                s.setDrzavljanstvo("Srbija");
                s.setNacionalnost("Srpska");
                s.setPol(i % 2 == 0 ? 'F' : 'M');
                s.setAdresa("Adresa " + i);
                s.setBrojTelefonaMobilni("06123456" + i);
                s.setPrivatniEmail("student" + i + "@example.com");
                s.setFakultetEmail("student" + i + "@raf.rs");
                s.setSrednjaSkola(srednjaSkola);
                s.setUspehSrednjaSkola(4.5 + (i * 0.1));
                s.setUspehPrijemni(85.0 + i);

                studentPodaciRepository.save(s);

                // Kreiranje indeksa za studenta
                StudentIndeks si = new StudentIndeks();
                si.setBroj(i);
                si.setGodina(2023);
                si.setNacinFinansiranja(nacinFinansiranja);
                si.setAktivan(true);
                si.setVaziOd(LocalDate.of(2023, 10, i));
                si.setOstvarenoEspb(0);
                si.setStudent(s);
                si.setStudijskiProgram(studProgrami.get((i - 1) % studProgrami.size()));

                studentIndeksRepository.save(si);
            }
        }
    }

    private void createSkolskeGodine() {
        if (skolskaGodinaRepository.count() == 0) {
            for (int i = 2020; i <= 2024; i++) {
                SkolskaGodina sg = new SkolskaGodina();
                sg.setGodina(i);
                sg.setAktivan(i == 2024); // samo poslednja je aktivna
                skolskaGodinaRepository.save(sg);
            }
        }
    }

    private void createDrziPredmet() {
        if (drziPredmetRepository.count() == 0) {
            List<Nastavnik> nastavnici = nastavnikRepository.findAll();
            List<Predmet> predmeti = predmetRepository.findAll();
            List<SkolskaGodina> skolskeGodine = skolskaGodinaRepository.findAll();

            for (int i = 0; i < Math.min(nastavnici.size(), predmeti.size()); i++) {
                DrziPredmet dp = new DrziPredmet();
                dp.setNastavnik(nastavnici.get(i));
                dp.setPredmet(predmeti.get(i));
                dp.setSkolskaGodina(skolskeGodine.get(skolskeGodine.size() - 1)); // poslednja aktivna godina
                drziPredmetRepository.save(dp);
            }
        }
    }

    private void createSlusaPredmet() {
        if (slusaPredmetRepository.count() == 0) {
            List<StudentIndeks> indeksi = studentIndeksRepository.findAll();
            List<DrziPredmet> drziPredmeti = drziPredmetRepository.findAll();

            for (int i = 0; i < Math.min(indeksi.size(), drziPredmeti.size()); i++) {
                PredmetSlusa ps = new PredmetSlusa();
                ps.setStudentIndeks(indeksi.get(i));
                ps.setDrziPredmet(drziPredmeti.get(i));
                slusaPredmetRepository.save(ps);
            }
        }
    }

    private void createGrupe() {
        if (grupaRepository.count() == 0) {
            List<StudijskiProgram> studProgrami = studijskiProgramRepository.findAll();
            List<Predmet> predmeti = predmetRepository.findAll();

            for (int i = 0; i < Math.min(studProgrami.size(), predmeti.size()); i++) {
                Grupa g = new Grupa();
                g.setStudijskiProgram(studProgrami.get(i));
                g.setPredmeti(List.of(predmeti.get(i)));
                grupaRepository.save(g);
            }
        }
    }

    private void createIspitniRokove() {
        if (ispitniRokRepository.count() == 0) {
            List<SkolskaGodina> skolskeGodine = skolskaGodinaRepository.findAll();

            String[] rokovi = {"Januarski", "Februarski", "Aprilski", "Junski", "Septembarski", "Oktobarski"};

            for (SkolskaGodina sg : skolskeGodine) {
                for (int i = 0; i < rokovi.length; i++) {
                    IspitniRok ir = new IspitniRok();
                    ir.setPocetak(LocalDate.of(sg.getGodina() + (i >= 4 ? 1 : 0), (i % 6) + 1, 1));
                    ir.setKraj(LocalDate.of(sg.getGodina() + (i >= 4 ? 1 : 0), (i % 6) + 1, 15));
                    ir.setSkolskaGodina(sg);
                    ispitniRokRepository.save(ir);
                }
            }
        }
    }

    private void createIspite() {
        if (ispitRepository.count() == 0) {
            List<Predmet> predmeti = predmetRepository.findAll();
            List<Nastavnik> nastavnici = nastavnikRepository.findAll();
            List<IspitniRok> ispitniRokovi = ispitniRokRepository.findAll();

            for (int i = 0; i < Math.min(predmeti.size(), 5); i++) {
                Ispit ispit = new Ispit();
                ispit.setDatumOdrzavanja(LocalDate.of(2024, 1, 15 + i));
                ispit.setVremePocetka(LocalTime.of(9, 0));
                ispit.setZakljucen(false);
                ispit.setPredmet(predmeti.get(i));
                ispit.setNastavnik(nastavnici.get(i));
                ispit.setIspitniRok(ispitniRokovi.get(i % ispitniRokovi.size()));

                ispitRepository.save(ispit);
            }
        }
    }

    private void createIspitPrijave() {
        if (ispitPrijavaRepository.count() == 0) {
            List<StudentIndeks> studenti = studentIndeksRepository.findAll();
            List<Ispit> ispiti = ispitRepository.findAll();

            for (int i = 0; i < Math.min(studenti.size(), ispiti.size()); i++) {
                IspitPrijava ip = new IspitPrijava();
                ip.setDatumPrijave(LocalDate.now().minusDays(i));
                ip.setIspit(ispiti.get(i));
                ip.setStudentIndeks(studenti.get(i));

                ispitPrijavaRepository.save(ip);
            }
        }
    }

    private void createOstaliEntiteti() {
        // Kreiranje predispitnih obaveza
        if (predispitnaObavezaVrstaRepository.count() == 0) {
            String[] vrste = {"Kolokvijum 1", "Kolokvijum 2", "Seminarski rad", "Projekat", "Aktivnost"};

            for (String vrsta : vrste) {
                PredispitnaObavezaVrsta pov = new PredispitnaObavezaVrsta();
                pov.setVrsta(vrsta);
                predispitnaObavezaVrstaRepository.save(pov);
            }
        }

        // Kreiranje predispitnih obaveza
        if (predispitnaObavezaRepository.count() == 0) {
            List<Predmet> predmeti = predmetRepository.findAll();
            List<SkolskaGodina> skolskeGodine = skolskaGodinaRepository.findAll();
            List<PredispitnaObavezaVrsta> vrste = predispitnaObavezaVrstaRepository.findAll();

            for (int i = 0; i < Math.min(predmeti.size(), 3); i++) {
                PredispitnaObaveza po = new PredispitnaObaveza();
                po.setVrsta(vrste.get(i % vrste.size()));
                po.setMaxBrojPoena(20.0 + (i * 5));
                po.setPredmet(predmeti.get(i));
                po.setSkolskaGodina(skolskeGodine.get(skolskeGodine.size() - 1));

                predispitnaObavezaRepository.save(po);
            }
        }
    }

    // Pomoćne metode za kreiranje osnovnih entiteta
    private Zvanje createZvanje() {
        Zvanje zvanje = new Zvanje();
        zvanje.setZvanje("Docent");
        return zvanjeRepository.save(zvanje);
    }

    private NaucnaOblast createNaucnaOblast() {
        NaucnaOblast no = new NaucnaOblast();
        no.setNaucnaOblast("Računarstvo i informatika");
        return naucnaOblastRepository.save(no);
    }

    private UzaNaucnaOblast createUzaNaucnaOblast() {
        UzaNaucnaOblast uno = new UzaNaucnaOblast();
        uno.setUzaNaucnaOblast("Softversko inženjerstvo");
        return uzaNaucnaOblastRepository.save(uno);
    }

    private VisokoskolskaUstanova createVisokoskolskaUstanova() {
        VisokoskolskaUstanova vu = new VisokoskolskaUstanova();
        vu.setNaziv("Univerzitet u Beogradu");
        return visokoskolskaUstanovaRepository.save(vu);
    }

    private TipSkole createTipSkole() {
        TipSkole ts = new TipSkole();
        ts.setTip("Gimnazija");
        return tipSkoleRepository.save(ts);
    }

    private SrednjaSkola createSrednjaSkola(TipSkole tipSkole) {
        SrednjaSkola ss = new SrednjaSkola();
        ss.setNaziv("Prva beogradska gimnazija");
        ss.setMesto("Beograd");
        ss.setTipSkole(tipSkole);
        return srednjaSkolaRepository.save(ss);
    }

    private NacinFinansiranja createNacinFinansiranja() {
        NacinFinansiranja nf1 = new NacinFinansiranja();
        nf1.setNacin("Budžet");
        nacinFinansiranjaRepository.save(nf1);

        NacinFinansiranja nf2 = new NacinFinansiranja();
        nf2.setNacin("Samofinansiranje");
        return nacinFinansiranjaRepository.save(nf2);
    }
}