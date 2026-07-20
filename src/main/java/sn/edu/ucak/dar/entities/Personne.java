package sn.edu.ucak.dar.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// classe mere, table unique pour Personne/Enseignant/Etudiant
@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name = "Fonction")
//@DiscriminatorValue("Visiteur")
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String prenom;

    private String nom;

    private String adresse;

    private String telephone;

    private LocalDateTime dateEnregistrement;

    private LocalDateTime dateModification;

    // date de creation
    @PrePersist
    public void onCreate() {
        dateEnregistrement = LocalDateTime.now();
        dateModification = dateEnregistrement;
    }

    // date de modif
    @PreUpdate
    public void onUpdate() {
        dateModification = LocalDateTime.now();
    }
}
