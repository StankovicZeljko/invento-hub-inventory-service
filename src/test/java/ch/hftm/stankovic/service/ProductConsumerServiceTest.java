package ch.hftm.stankovic.service;

import ch.hftm.stankovic.module.Inventory;
import ch.hftm.stankovic.module.Product;
import ch.hftm.stankovic.repository.InventoryRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@QuarkusTest
public class ProductConsumerServiceTest {

    @InjectMocks
    private ProductConsumerService productConsumerService;

    @Mock
    private InventoryRepository inventoryRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsume() {
        // Arrange
        Product product = new Product();
        product.setId(1L);

        ArgumentCaptor<Inventory> inventoryCaptor = ArgumentCaptor.forClass(Inventory.class);

        // Act
        productConsumerService.consume(product);

        // Assert
        verify(inventoryRepository).persist(inventoryCaptor.capture());
        Inventory capturedInventory = inventoryCaptor.getValue();
        assertEquals(product.getId(), capturedInventory.getProductId());
        assertEquals(10, capturedInventory.getQuantity());
        assertEquals(2, capturedInventory.getMinQuantity());
    }
}