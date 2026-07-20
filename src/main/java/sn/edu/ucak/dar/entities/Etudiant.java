package sn.edu.ucak.dar.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Etudiant")
public class Etudiant {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "dateNaissance")
    private LocalDate dateNaissance;

    @Column(name = "promotion")
    private Short promotion;

    @Column(name = "dateEnregistrement")
    private Instant dateEnregistrement;

    @Column(name = "dateModification")
    private Instant dateModification;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "filiere")
    private String filiere;

    @Column(name = "lieuNaissance")
    private String lieuNaissance;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "telephone")
    private String telephone;


}