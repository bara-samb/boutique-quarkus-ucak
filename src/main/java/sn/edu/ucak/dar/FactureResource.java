package sn.edu.ucak.dar;

import sn.edu.ucak.dar.entities.Facture;
import sn.edu.ucak.dar.entities.LigneArticle;
import sn.edu.ucak.dar.entities.Client;
import sn.edu.ucak.dar.entities.Produit;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.HashSet;

/**
 * Ressource REST JAX-RS pour gérer les factures et les lignes d'articles associées.
 * Disponible sur le chemin "/factures".
 */
@Path("/factures")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FactureResource {

    @Inject
    EntityManager em;

    /**
     * Récupère toutes les factures.
     */
    @GET
    public List<Facture> listAll() {
        return em.createQuery("FROM Facture", Facture.class).getResultList();
    }

    /**
     * Récupère une facture par son ID.
     */
    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") Integer id) {
        Facture facture = em.find(Facture.class, id);
        if (facture == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Facture non trouvée pour l'ID : " + id)
                           .build();
        }
        return Response.ok(facture).build();
    }

    /**
     * Crée une nouvelle facture avec ses lignes d'articles.
     */
    @POST
    @Transactional
    public Response create(Facture facture) {
        if (facture.getId() != null) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("L'ID ne doit pas être fourni pour la création d'une facture.")
                           .build();
        }
        
        // 1. Rattacher le client existant
        if (facture.getClient() == null || facture.getClient().getId() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Un client existant valide est requis pour créer une facture.")
                           .build();
        }
        
        Client client = em.find(Client.class, facture.getClient().getId());
        if (client == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Client non trouvé pour l'ID : " + facture.getClient().getId())
                           .build();
        }
        facture.setClient(client);
        
        // 2. Traiter les lignes d'articles associées
        if (facture.getLigneArticles() != null && !facture.getLigneArticles().isEmpty()) {
            for (LigneArticle ligne : facture.getLigneArticles()) {
                ligne.setFacture(facture); // Associer la facture parente
                
                // Rattacher le produit existant et charger son prix unitaire si nécessaire
                if (ligne.getProduit() == null || ligne.getProduit().getId() == null) {
                    return Response.status(Response.Status.BAD_REQUEST)
                                   .entity("Chaque ligne d'article doit référencer un produit valide.")
                                   .build();
                }
                
                Produit produit = em.find(Produit.class, ligne.getProduit().getId());
                if (produit == null) {
                    return Response.status(Response.Status.NOT_FOUND)
                                   .entity("Produit non trouvé pour l'ID : " + ligne.getProduit().getId())
                                   .build();
                }
                ligne.setProduit(produit);
                
                // Si le prix unitaire n'est pas spécifié, on prend le prix actuel du produit
                if (ligne.getPrixUnitaire() == null) {
                    ligne.setPrixUnitaire(produit.getPrix().doubleValue());
                }
            }
        } else {
            facture.setLigneArticles(new HashSet<>());
        }
        
        // Persister la facture (le CascadeType.ALL s'occupera d'insérer les LigneArticle automatiquement)
        em.persist(facture);
        
        return Response.status(Response.Status.CREATED).entity(facture).build();
    }

    /**
     * Supprime une facture.
     */
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        Facture facture = em.find(Facture.class, id);
        if (facture == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Facture non trouvée pour l'ID : " + id)
                           .build();
        }
        
        // La suppression de la facture va également supprimer en cascade ses LigneArticle
        em.remove(facture);
        return Response.noContent().build();
    }
}
