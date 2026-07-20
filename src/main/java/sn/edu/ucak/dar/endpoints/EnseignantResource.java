package sn.edu.ucak.dar.endpoints;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import sn.edu.ucak.dar.entities.Enseignant;
import sn.edu.ucak.dar.entities.Etudiant;

// endpoint enseignants
@Path("/enseignants")
public class EnseignantResource {

    @Inject
    EntityManager em;

    // ajout d'un etudiant
    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Enseignant saveEnseignant(Enseignant enseignant) {
        em.persist(enseignant);
        return enseignant;
    }
}
