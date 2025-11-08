package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"oznaka"})
    }
)
public class VrstaStudija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String naziv;
    @NotBlank
    private String oznaka;
}
