package ch.hftm.stankovic.repository;

import ch.hftm.stankovic.module.Inventory;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@QuarkusTest
public class InventoryRepositoryTest {

    @InjectMock
    InventoryRepository inventoryRepository;

    @Test
    public void testFindByProductId() {
        Long productId = 1L;
        Inventory inventory = new Inventory(1L, productId, 10,2);
        when(inventoryRepository.findByProductId(productId)).thenReturn(inventory);
        inventoryRepository.persist(inventory);

        Inventory foundInventory = inventoryRepository.findByProductId(productId);

        assertEquals(productId, foundInventory.getProductId());
    }
}