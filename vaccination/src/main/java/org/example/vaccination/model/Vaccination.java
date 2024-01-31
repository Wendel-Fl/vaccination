package org.example.vaccination.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.vaccination.model.dto.VaccinationDTO;
import org.example.vaccination.model.dto.VaccinationDetailDTO;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @OneToMany(
            mappedBy = "vaccination",
            fetch = FetchType.LAZY
    )
    @JsonManagedReference(value = "schedule-vaccination")
    @JsonIgnore
    private List<Schedule> schedules;

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

    @JsonIgnore
    public ChronoUnit getFrequencyUnit() {
        return switch (this.frequency) {
            case 1 -> ChronoUnit.DAYS;
            case 2 -> ChronoUnit.WEEKS;
            case 3 -> ChronoUnit.MONTHS;
            case 4 -> ChronoUnit.YEARS;
            default -> throw new RuntimeException("Invalid frequency unit");
        };
    }
}
