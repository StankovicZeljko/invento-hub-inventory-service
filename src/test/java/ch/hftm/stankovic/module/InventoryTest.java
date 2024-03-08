package ch.hftm.stankovic.module;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class InventoryTest {

    @Test
    public void testInventory() {
        Long productId = 1L;
        Integer quantity = 10;

        Inventory inventory = new Inventory(1L, productId, quantity);

        assertEquals(productId, inventory.getProductId());
        assertEquals(quantity, inventory.getQuantity());
    }
}