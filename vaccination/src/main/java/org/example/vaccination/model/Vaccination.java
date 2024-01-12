package org.example.vaccination.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.vaccination.model.dto.VaccinationDTO;
import org.example.vaccination.model.dto.VaccinationDetailDTO;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Vaccination")
@Table(name = "vacinas")
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String title;

    @Column(name = "descricao")
    private String description;

    @Column(name = "doses")
    private Integer dosage;

    @Column(name = "periodicidade")
    private Integer frequency;

    @Column(name = "intervalo")
    private Integer interval;

//    TODO: Relação one-to-many para Agendas aqui

    public Vaccination(VaccinationDTO vaccinationDTO) {
        this.title = vaccinationDTO.title();
        this.description = vaccinationDTO.description();
        this.dosage = vaccinationDTO.dosage();
        this.frequency = vaccinationDTO.frequency();
        this.interval = vaccinationDTO.interval();
    }

    public void updateVaccine(VaccinationDetailDTO vaccinationDetailDTO) {
        if (vaccinationDetailDTO.title() != null) {
            this.title = vaccinationDetailDTO.title();
        }

        if (vaccinationDetailDTO.description() != null) {
            this.description = vaccinationDetailDTO.description();
        }

        if (vaccinationDetailDTO.dosage() != null) {
            this.dosage = vaccinationDetailDTO.dosage();
        }

        if (vaccinationDetailDTO.frequency() != null) {
            this.frequency = vaccinationDetailDTO.frequency();
        }

        if (vaccinationDetailDTO.interval() != null) {
            this.interval = vaccinationDetailDTO.interval();
        }
    }
}
