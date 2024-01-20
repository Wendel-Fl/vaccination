package org.example.vaccination.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.vaccination.model.dto.UserDTO;
import org.example.vaccination.model.dto.UserDetailDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
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
    private State state;

    @Setter
    @ManyToMany
    @JoinTable(
            name = "usuarios_alergias",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "alergia_id")
    )
    @JsonManagedReference
    private Set<Allergy> allergies;

    @Setter
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Schedule> schedules;

    public User(UserDTO userDTO) {
        this.name = userDTO.name();
        this.dateOfBirth = userDTO.dateOfBirth();
        this.gender = userDTO.gender();
        this.publicPlace = userDTO.publicPlace();
        this.number = userDTO.number();
        this.district = userDTO.district();
        this.city = userDTO.city();
        this.state = State.valueOf(userDTO.state().getAcronym());
        this.allergies = userDTO.allergies();
    }

    public void updateInfo(UserDetailDTO userDetailDTO) {
        if (userDetailDTO.name() != null) {
            this.name = userDetailDTO.name();
        }

        if (userDetailDTO.dateOfBirth() != null) {
            this.dateOfBirth = userDetailDTO.dateOfBirth();
        }

        if (userDetailDTO.gender() != null) {
            this.gender = userDetailDTO.gender();
        }

        if (userDetailDTO.publicPlace() != null) {
            this.publicPlace = userDetailDTO.publicPlace();
        }

        if (userDetailDTO.number() != null) {
            this.number = userDetailDTO.number();
        }

        if (userDetailDTO.district() != null) {
            this.district = userDetailDTO.district();
        }

        if (userDetailDTO.city() != null) {
            this.city = userDetailDTO.city();
        }

        if (userDetailDTO.state() != null) {
            this.state = State.valueOf(userDetailDTO.state().getAcronym());
        }
    }

//    Refatorar para usar o método attachAllergy
//    public void attachAllergy(UserDetailDTO userDetailDTO) {
//        if (userDetailDTO.allergies() != null) {
//            this.allergies.addAll(userDetailDTO.allergies());
//        }
//    }
}
