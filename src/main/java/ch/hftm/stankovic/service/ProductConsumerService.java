package ch.hftm.stankovic.service;

import ch.hftm.stankovic.module.Inventory;
import ch.hftm.stankovic.module.Product;
import ch.hftm.stankovic.repository.InventoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class ProductConsumerService {
    @Inject
    InventoryRepository repository;
    @Incoming("product-create")
    @Transactional
    public void consume(Product product){
        Inventory inventory = new Inventory();
        inventory.setProductId(product.getId());
        inventory.setQuantity(10);
        inventory.setMinQuantity(2);
        repository.persist(inventory);
    }

}