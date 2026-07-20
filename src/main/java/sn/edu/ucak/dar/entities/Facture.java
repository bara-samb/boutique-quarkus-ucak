package sn.edu.ucak.dar.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "facture")
@Getter
@Setter
@NoArgsConstructor
public class Facture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 20)
    private String numero;

    @Column(nullable = false)
    private LocalDateTime dateFacture;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL)
    private Set<LigneArticle> ligneArticles;

    @PrePersist
    public void prePersist() {
        if (dateFacture == null) {
            dateFacture = LocalDateTime.now();
        }
    }
}
