package sn.edu.ucak.dar;

import sn.edu.ucak.dar.entities.Client;
import sn.edu.ucak.dar.entities.Carte;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Ressource REST JAX-RS pour gérer les opérations CRUD sur l'entité Client.
 * Disponible sur le chemin "/clients".
 */
@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    @Inject
    EntityManager em;

    /**
     * Récupère tous les clients.
     */
    @GET
    public List<Client> listAll() {
        return em.createQuery("FROM Client", Client.class).getResultList();
    }

    /**
     * Récupère un client par son ID.
     */
    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") Integer id) {
        Client client = em.find(Client.class, id);
        if (client == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Client non trouvé pour l'ID : " + id)
                           .build();
        }
        return Response.ok(client).build();
    }

    /**
     * Crée un nouveau client et lui associe automatiquement une carte de fidélité.
     */
    @POST
    @Transactional
    public Response create(Client client) {
        if (client.getId() != null) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("L'ID ne doit pas être fourni pour la création d'un client.")
                           .build();
        }
        
        // Persister le client
        em.persist(client);
        
        // Créer et associer automatiquement une carte de fidélité pour ce client
        Carte carte = new Carte();
        carte.setProprietaire(client);
        em.persist(carte);
        
        return Response.status(Response.Status.CREATED).entity(client).build();
    }

    /**
     * Met à jour un client existant.
     */
    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, Client updateData) {
        Client client = em.find(Client.class, id);
        if (client == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Client non trouvé pour l'ID : " + id)
                           .build();
        }
        
        client.setNom(updateData.getNom());
        client.setPrenom(updateData.getPrenom());
        client.setEmail(updateData.getEmail());
        client.setAdresseLivraison(updateData.getAdresseLivraison());
        client.setNumeroTelephone(updateData.getNumeroTelephone());
        
        return Response.ok(client).build();
    }

    /**
     * Supprime un client, ainsi que sa carte associée.
     */
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        Client client = em.find(Client.class, id);
        if (client == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Client non trouvé pour l'ID : " + id)
                           .build();
        }
        
        // 1. Supprimer d'abord la carte de fidélité associée pour respecter les contraintes d'intégrité
        em.createQuery("DELETE FROM Carte c WHERE c.proprietaire = :client")
          .setParameter("client", client)
          .executeUpdate();
        
        // 2. Supprimer le client
        em.remove(client);
        
        return Response.noContent().build();
    }
}
