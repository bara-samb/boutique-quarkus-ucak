package sn.edu.ucak.dar.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// sous classe Personne
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Enseignant extends Personne {
    private String matricule;
    private String matiere;
}
