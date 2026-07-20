package sn.edu.ucak.dar.entities;

<<<<<<< HEAD
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
=======
import jakarta.persistence.Entity;
>>>>>>> parent of f05c08b (Adding Enseignant and Etudiant)
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
<<<<<<< HEAD
@Table(name = "Enseignant")
public class Enseignant {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "dateEnregistrement")
    private Instant dateEnregistrement;

    @Column(name = "dateModification")
    private Instant dateModification;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "matiere")
    private String matiere;

    @Column(name = "matricule", nullable = false)
    private String matricule;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "telephone")
    private String telephone;


}
=======
public class Enseignant extends Personne {
    private String matricule;
    private String matiere;
}
>>>>>>> parent of f05c08b (Adding Enseignant and Etudiant)
