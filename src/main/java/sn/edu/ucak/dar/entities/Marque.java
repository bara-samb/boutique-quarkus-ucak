package sn.edu.ucak.dar.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "marque")
@Getter
@Setter
@NoArgsConstructor
public class Marque implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(length = 500)
    private String description;

    @OneToMany(mappedBy = "marque")
    private List<Produit> produits = new ArrayList<>();
}
