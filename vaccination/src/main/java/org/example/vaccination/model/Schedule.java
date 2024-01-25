package org.example.vaccination.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.vaccination.model.dto.ScheduleDTO;
import org.example.vaccination.model.dto.ScheduleDetailDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Schedule")
@Table(name = "agendas")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora")
//    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime dateTime;

    @Column(name = "situacao")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "data_situacao")
    private LocalDate statusDate;

    @Column(name = "observacoes")
    private String notes;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacina_id", nullable = false)
    @JsonBackReference(value = "schedule-vaccination")
    private Vaccination vaccination;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference(value = "schedule-user")
    private User user;

    public Schedule(ScheduleDTO scheduleDTO) {
        this.dateTime = scheduleDTO.dateTime();
        this.status = scheduleDTO.status();
        this.statusDate = scheduleDTO.statusDate();
        this.notes = scheduleDTO.notes();
        this.vaccination = scheduleDTO.vaccination();
        this.user = scheduleDTO.user();
    }

    public void updateInfo(ScheduleDetailDTO scheduleDetailDTO) {
        if (scheduleDetailDTO.dateTime() != null) {
            this.dateTime = scheduleDetailDTO.dateTime();
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

//    public void attachVaccination(Vaccination vaccination) {
//        this.vaccination = vaccination;
//    }
}
