package sn.edu.ucak.dar.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Etudiant extends Personne {
    private LocalDate dateNaissance;
    private String lieuNaissance;
    private String filiere;
    private Short promotion;
}
