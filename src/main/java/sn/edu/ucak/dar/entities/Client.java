package sn.edu.ucak.dar.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 100)
    private String prenom;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "adresse_livraison", nullable = false, length = 254)
    private String adresseLivraison;

    @Column(name = "telephone", nullable = false, length = 20)
    private String numeroTelephone;
}
