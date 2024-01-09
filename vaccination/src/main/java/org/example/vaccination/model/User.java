package org.example.vaccination.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.vaccination.model.dto.UserDTO;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Users")
@Table(name = "Usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "data_nascimento")
    private LocalDate dateOfBirth;

    @Column(name = "sexo")
    private Character gender;

    @Column(name = "logradouro")
    private String publicPlace;

    @Column(name = "numero")
    private Integer number;

    @Column(name = "setor")
    private String district;

    @Column(name = "cidade")
    private String city;

    @Column(name = "uf")
    private State fu;

    public User(UserDTO userDTO) {
        this.name = userDTO.name();
        this.dateOfBirth = userDTO.dateOfBirth();
        this.gender = userDTO.gender();
        this.publicPlace = userDTO.publicPlace();
        this.number = userDTO.number();
        this.district = userDTO.district();
        this.city = userDTO.city();
        this.fu = State.valueOf(userDTO.fu().getAcronym());
    }
}
