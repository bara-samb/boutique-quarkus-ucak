package sn.edu.ucak.dar.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

// sous classe Personne
@Getter
@Setter
@NoArgsConstructor
@Entity
//@DiscriminatorValue("Apprenant")
public class Etudiant extends Personne {

    private LocalDate dateNaissance;
    private String lieuNaissance;
    private String filiere;
    private Short promotion;
}
