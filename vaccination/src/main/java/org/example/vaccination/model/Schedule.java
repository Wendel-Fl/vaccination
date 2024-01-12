package org.example.vaccination.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.vaccination.model.dto.ScheduleDTO;
import org.example.vaccination.model.dto.ScheduleDetailDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Schedule")
@Table(name = "agendas")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data")
    private LocalDate date;

    @Column(name = "hora")
    private LocalDateTime hour;

    @Column(name = "situacao")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "data_situacao")
    private LocalDate statusDate;

    @Column(name = "observacoes")
    private String notes;

//    TODO: Relação many-to-one para agenda aqui
//    TODO: Relação many-to-one para usuário aqui
//    TODO: Referência many-to-many para alergias aqui?

    public Schedule(ScheduleDTO scheduleDTO) {
        this.date = scheduleDTO.date();
        this.hour = scheduleDTO.hour();
        this.status = Status.valueOf(scheduleDTO.status().getDescription());
        this.statusDate = scheduleDTO.statusDate();
        this.notes = scheduleDTO.notes();
    }

    public void updateInfo(ScheduleDetailDTO scheduleDetailDTO) {
        if (scheduleDetailDTO.date() != null) {
            this.date = scheduleDetailDTO.date();
        }

        if (scheduleDetailDTO.hour() != null) {
            this.hour = scheduleDetailDTO.hour();
        }

        if (scheduleDetailDTO.status() != null) {
            this.status = Status.valueOf(scheduleDetailDTO.status().getDescription());
        }

        if (scheduleDetailDTO.statusDate() != null) {
            this.statusDate = scheduleDetailDTO.statusDate();
        }

        if (scheduleDetailDTO.notes() != null) {
            this.notes = scheduleDetailDTO.notes();
        }
    }
}
