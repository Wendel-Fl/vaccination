package org.example.vaccination.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.vaccination.model.dto.AllergyDTO;
import org.example.vaccination.model.dto.AllergyDetailDTO;

import java.util.Set;

@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Allergy")
@Table(name = "alergias")
public class Allergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @ManyToMany(mappedBy = "allergies")
    @JsonIgnore
    private Set<User> users;

    public Allergy(AllergyDTO allergyDTO) {
        this.name = allergyDTO.name();
    }

    public void updateInfo(AllergyDetailDTO allergyDetailDTO) {
        if (allergyDetailDTO.name() != null) {
            this.name = allergyDetailDTO.name();
        }
    }
}
