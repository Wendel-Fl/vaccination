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
    @Setter
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
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.DETACH
    )
    @JoinColumn(name = "vacina_id", nullable = false)
    @JsonBackReference(value = "schedule-vaccination")
    private Vaccination vaccination;

    @Setter
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.DETACH
    )
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference(value = "schedule-user")
    private User user;

    public Schedule(ScheduleDTO scheduleDTO) {
        this.dateTime = scheduleDTO.dateTime();
        this.notes = scheduleDTO.notes();
        this.vaccination = scheduleDTO.vaccination();
        this.user = scheduleDTO.user();
    }

    public void updateInfo(ScheduleDetailDTO scheduleDetailDTO) {
        if (scheduleDetailDTO.notes() != null) {
            this.notes = scheduleDetailDTO.notes();
        }
    }

//    public void attachVaccination(Vaccination vaccination) {
//        this.vaccination = vaccination;
//    }
}
