package ch.hftm.stankovic.resource;

import ch.hftm.stankovic.module.Inventory;
import ch.hftm.stankovic.repository.InventoryRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/inventories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventoryResource {
    @Inject
    InventoryRepository repository;

    @GET
    public List<Inventory> getAllInventories(){
        return repository.listAll();
    }

    @GET
    @Path("/{id}")
    public Inventory getInventoryByProductId(@PathParam("id") Long id){
        return repository.findByProductId(id);
    }

    @POST
    @Transactional
    public Response createInventory(Inventory inventory) {
        repository.persist(inventory);
        return Response.status(Response.Status.CREATED).entity(inventory).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateInventory(@PathParam("id") Long id, Inventory inventory) {
        Inventory existingInventory = repository.findByProductId(id);
        if (existingInventory != null) {
            existingInventory.setQuantity(inventory.getQuantity());
            repository.persist(existingInventory);
        }
        return Response.ok(existingInventory).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteInventory(@PathParam("id") Long id) {
        Inventory inventory = repository.findByProductId(id);
        if (inventory != null) {
            repository.delete(inventory);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}