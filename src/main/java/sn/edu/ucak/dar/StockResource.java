package sn.edu.ucak.dar;

import sn.edu.ucak.dar.entities.Stock;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Ressource REST JAX-RS pour gérer les stocks associés aux produits.
 * Disponible sur le chemin "/stocks".
 */
@Path("/stocks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StockResource {

    @Inject
    EntityManager em;

    /**
     * Récupère tous les stocks.
     */
    @GET
    public List<Stock> listAll() {
        return em.createQuery("FROM Stock", Stock.class).getResultList();
    }

    /**
     * Récupère le stock d'un produit spécifique via son ID produit.
     */
    @GET
    @Path("/produit/{produitId}")
    public Response getByProduitId(@PathParam("produitId") Integer produitId) {
        List<Stock> stocks = em.createQuery("FROM Stock s WHERE s.produit.id = :produitId", Stock.class)
                               .setParameter("produitId", produitId)
                               .getResultList();
                               
        if (stocks.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Stock non trouvé pour le produit ID : " + produitId)
                           .build();
        }
        return Response.ok(stocks.get(0)).build();
    }

    /**
     * Met à jour la quantité d'un stock spécifique.
     */
    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateQuantite(@PathParam("id") Integer id, Stock updateData) {
        Stock stock = em.find(Stock.class, id);
        if (stock == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Stock non trouvé pour l'ID : " + id)
                           .build();
        }
        
        stock.setQuantite(updateData.getQuantite());
        
        return Response.ok(stock).build();
    }
}
