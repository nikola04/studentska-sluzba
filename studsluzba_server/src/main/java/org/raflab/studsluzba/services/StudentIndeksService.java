package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.model.StudentPodaci;
import org.raflab.studsluzba.model.StudijskiProgram;
import org.raflab.studsluzba.repositories.StudentIndeksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class StudentIndeksService {
    @Autowired
    private StudentIndeksRepository studentIndeksRepository;

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudijskiProgramService studijskiProgramService;

    public StudentIndeks getStudentIndeks(Long id) {
        return studentIndeksRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[StudentIndeks] Not found: " + id));
    }

    public List<StudentIndeks> getAllStudentIndeks(Long studentPodaciId) {
        return studentIndeksRepository.findStudentIndeksiForStudentPodaciId(studentPodaciId);
    }

    @Transactional
    public StudentIndeks saveStudentIndeks(StudentIndeks studentIndeks, Long studentId, Long studProgramId) {
        StudentPodaci studentPodaci = studentService.getStudentPodaci(studentId);
        StudijskiProgram sp = studijskiProgramService.getStudijskiProgram(studProgramId);

        studentIndeks.setBroj(this.findBroj(studentIndeks.getGodina(), sp.getId()));
        studentIndeks.setStudent(studentPodaci);
        studentIndeks.setStudijskiProgram(sp);

        return studentIndeksRepository.save(studentIndeks);
    }

    @Transactional
    public void deleteStudentIndeks(Long id) {
        StudentIndeks existing = this.getStudentIndeks(id);
        studentIndeksRepository.delete(existing);
    }

    @Transactional(readOnly = true)
    public int findBroj(int godina, Long studProgramId) {
        List<Integer> brojeviList = studentIndeksRepository.findBrojeviByGodinaAndStudProgramOznaka(godina, studProgramId);

        return findNextAvailableNumber(brojeviList);
    }

    private int findNextAvailableNumber(List<Integer> brojeviList) {
        if (brojeviList == null || brojeviList.isEmpty()) return 1;

        List<Integer> sorted = brojeviList.stream()
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(java.util.stream.Collectors.toList()); // <= works on JDK 8+

        int expected = 1;
        for (int num : sorted) {
            if (num != expected) return expected;
            expected++;
        }
        return expected;
    }
}
