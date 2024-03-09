package ch.hftm.stankovic.repository;

import ch.hftm.stankovic.module.Inventory;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InventoryRepository implements PanacheRepository<Inventory> {

    public Inventory findByProductId(Long productId){
        return find("product_id", productId).firstResult();
    }
}
