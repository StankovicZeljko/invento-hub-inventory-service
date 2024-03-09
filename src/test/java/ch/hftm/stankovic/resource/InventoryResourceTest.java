package ch.hftm.stankovic.resource;

import ch.hftm.stankovic.module.Inventory;
import ch.hftm.stankovic.repository.InventoryRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
public class InventoryResourceTest {
    @InjectMocks
    private InventoryResource inventoryResource;

    @Mock
    private InventoryRepository inventoryRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllInventories() {
        Inventory inventory1 = new Inventory(1L, 1L, 10, 2);
        Inventory inventory2 = new Inventory(2L, 2L, 20, 5);
        List<Inventory> inventories = Arrays.asList(inventory1, inventory2);

        when(inventoryRepository.listAll()).thenReturn(inventories);

        List<Inventory> result = inventoryResource.getAllInventories();

        assertEquals(inventories, result);
    }

    @Test
    public void testGetInventoryByProductId() {
        Inventory inventory = new Inventory(1L, 1L, 10, 4);

        when(inventoryRepository.findByProductId(1L)).thenReturn(inventory);

        Inventory result = inventoryResource.getInventoryByProductId(1L);

        assertEquals(inventory, result);
    }

    @Test
    public void testCreateInventory() {
        Inventory inventory = new Inventory(null, 1L, 10, 5);

        when(inventoryRepository.findByProductId(inventory.getProductId())).thenReturn(null);

        Response response = inventoryResource.createInventory(inventory);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        verify(inventoryRepository, times(1)).persist(inventory);
    }

    @Test
    public void testUpdateInventory() {
        Inventory inventory = new Inventory(1L, 1L, 10, 4);

        when(inventoryRepository.findByProductId(1L)).thenReturn(inventory);

        Response response = inventoryResource.updateInventory(1L, inventory);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(inventory, response.getEntity());
    }

    @Test
    public void testDeleteInventory() {
        Inventory inventory = new Inventory(1L, 1L, 10, 5);

        when(inventoryRepository.findByProductId(1L)).thenReturn(inventory);

        Response response = inventoryResource.deleteInventory(1L);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(inventoryRepository, times(1)).delete(inventory);
    }
}