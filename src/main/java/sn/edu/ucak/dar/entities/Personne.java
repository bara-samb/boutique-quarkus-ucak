package sn.edu.ucak.dar.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
<<<<<<< HEAD
@Table(name = "Personne")
=======
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
>>>>>>> parent of f05c08b (Adding Enseignant and Etudiant)
public class Personne {
    @Id
<<<<<<< HEAD
    @Column(name = "id", nullable = false)
=======
    @GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> parent of f05c08b (Adding Enseignant and Etudiant)
    private Integer id;

    @Column(name = "dateEnregistrement")
    private Instant dateEnregistrement;

    @Column(name = "dateModification")
    private Instant dateModification;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "telephone")
    private String telephone;


}