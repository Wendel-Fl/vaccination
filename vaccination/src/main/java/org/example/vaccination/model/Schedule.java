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

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.example.vaccination.model.Status.CANCELLED;
import static org.example.vaccination.model.Status.CARRIED_OUT;

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
    private LocalDateTime dateTime;

    @Column(name = "situacao")
    @Enumerated(EnumType.STRING)
    @Setter
    private Status status;

    @Column(name = "data_situacao")
    @Setter
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
            this.status = scheduleDetailDTO.status();
            if (this.status == CARRIED_OUT ||
                    this.status == CANCELLED) {
                this.statusDate = LocalDate.now();
            }
        }

        if (scheduleDetailDTO.notes() != null) {
            this.notes = scheduleDetailDTO.notes();
        }
    }

//    public void attachVaccination(Vaccination vaccination) {
//        this.vaccination = vaccination;
//    }
}
