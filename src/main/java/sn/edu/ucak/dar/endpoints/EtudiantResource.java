package sn.edu.ucak.dar.endpoints;

import sn.edu.ucak.dar.entities.Etudiant;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

// endpoint etudiants
@Path("/etudiants")
public class EtudiantResource {

    @Inject
    EntityManager em;

    // ajout d'un etudiant
    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Etudiant saveEtudiant(Etudiant etudiant) {
        em.persist(etudiant);
        return etudiant;
    }
}
