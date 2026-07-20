package sn.edu.ucak.dar.endpoints;

import sn.edu.ucak.dar.entities.Personne;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

// endpoint personnes
@Path("/personnes")
public class PersonneResource {

    @Inject
    EntityManager em;

    // ajout d'une personne
    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Personne savePersonne(Personne personne) {
        em.persist(personne);
        return personne;
    }
}
