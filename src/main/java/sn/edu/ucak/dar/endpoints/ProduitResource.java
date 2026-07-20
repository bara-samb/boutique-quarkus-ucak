package sn.edu.ucak.dar.endpoints;

import sn.edu.ucak.dar.entities.Produit;
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
 * Ressource REST JAX-RS pour gérer les opérations CRUD sur la nouvelle entité Produit.
 * Disponible sur le chemin "/produits".
 */
@Path("/produits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProduitResource {

    @Inject
    EntityManager em;

    /**
     * Récupère tous les produits.
     */
    @GET
    public List<Produit> listAll() {
        return em.createQuery("FROM Produit", Produit.class).getResultList();
    }

    /**
     * Récupère un produit par son ID.
     */
    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") Integer id) {
        Produit produit = em.find(Produit.class, id);
        if (produit == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Produit non trouvé pour l'ID : " + id)
                           .build();
        }
        return Response.ok(produit).build();
    }

    /**
     * Crée un nouveau produit.
     */
    @POST
    @Transactional
    public Response create(Produit produit) {
        if (produit.getId() != null) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("L'ID ne doit pas être fourni pour la création d'un produit.")
                           .build();
        }
        // Si le produit contient une marque avec un ID, on la rattache au contexte Hibernate
        if (produit.getMarque() != null && produit.getMarque().getId() != null) {
            produit.setMarque(em.find(sn.edu.ucak.dar.entities.Marque.class, produit.getMarque().getId()));
        }
        em.persist(produit);
        return Response.status(Response.Status.CREATED).entity(produit).build();
    }

    /**
     * Met à jour un produit existant.
     */
    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, Produit updateData) {
        Produit produit = em.find(Produit.class, id);
        if (produit == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Produit non trouvé pour l'ID : " + id)
                           .build();
        }
        
        produit.setCode(updateData.getCode());
        produit.setNom(updateData.getNom());
        produit.setDescription(updateData.getDescription());
        produit.setPrix(updateData.getPrix());
        
        if (updateData.getMarque() != null && updateData.getMarque().getId() != null) {
            produit.setMarque(em.find(sn.edu.ucak.dar.entities.Marque.class, updateData.getMarque().getId()));
        }
        
        return Response.ok(produit).build();
    }

    /**
     * Supprime un produit.
     */
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        Produit produit = em.find(Produit.class, id);
        if (produit == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Produit non trouvé pour l'ID : " + id)
                           .build();
        }
        em.remove(produit);
        return Response.noContent().build();
    }
}
