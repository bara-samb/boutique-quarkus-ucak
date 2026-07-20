package sn.edu.ucak.dar.endpoints;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

// endpoint marques
@Path("/marques")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarqueResource {

    @Inject
    EntityManager em;

    // liste des marques
    @GET
    public List<Marque> listAll() {
        return em.createQuery("FROM Marque", Marque.class).getResultList();
    }

    // ajout d'une marque
    @POST
    @Transactional
    public Marque create(Marque marque) {
        em.persist(marque);
        return marque;
    }
}
