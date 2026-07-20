package sn.edu.ucak.dar.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

@Entity
@Table(name = "ligne_article")
@Getter
@Setter
@NoArgsConstructor
public class LigneArticle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer quantite;

    @Column(name = "prix_unitaire", nullable = false)
    private Double prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "facture_id", nullable = false)
    @JsonIgnoreProperties("ligneArticles")
    private Facture facture;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;
}
