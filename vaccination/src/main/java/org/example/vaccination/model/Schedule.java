package org.example.vaccination.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
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
}
