package inventory.repository;

import inventory.model.Part;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockingDetails;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class InventoryInMemoryRepositoryMockAllTest {

    private Part part;
    private InventoryInMemoryRepository repo;

    @BeforeEach
    public void setUp() {
        part = mock(Part.class);
        repo = new InventoryInMemoryRepository();
    }

    @Test
    void addPart() {
        repo.addPart(part);
        assertEquals(repo.getAllParts().size(),1);
    }

    @Test
    void getAllParts() {
        assertEquals(repo.getAllParts().size(),0);
        repo.addPart(part);
        assertEquals(repo.getAllParts().size(),1);
        repo.addPart(part);
        assertEquals(repo.getAllParts().size(),2);
    }

    @AfterEach
    public void tearDown(){
        repo = null;
        part = null;
    }
}