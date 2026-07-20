package sn.edu.ucak.dar.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// sous classe Personne
@Getter
@Setter
@NoArgsConstructor
@Entity
//@DiscriminatorValue("Formateur")
public class Enseignant extends Personne {

    @Column(nullable = false)
    private String matricule;

    private String matiere;
}
