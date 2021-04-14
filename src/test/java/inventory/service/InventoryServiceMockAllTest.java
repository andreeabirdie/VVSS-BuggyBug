package inventory.service;

import inventory.model.Part;
import inventory.repository.InventoryFileRepository;
import inventory.repository.InventoryInMemoryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class InventoryServiceMockAllTest {

    private Part part;
    private InventoryFileRepository fileRepo;
    private InventoryService service;

    @BeforeEach
    public void setUp() {
        part = mock(Part.class);
        fileRepo = mock(InventoryFileRepository.class);
        service = new InventoryService(fileRepo);
    }

    @Test
    void deletePart(){
        Mockito.doNothing().when(fileRepo).deletePart(part);
        service.deletePart(part);

        Mockito.verify(fileRepo, times(1)).deletePart(part);
    }

    @Test
    void getAllParts() {
        Mockito.when(fileRepo.getAllParts()).thenReturn(FXCollections.observableArrayList(part));

        assertEquals(service.getAllParts().size(),1);
        assertEquals(service.getAllParts().get(0),part);
    }

    @AfterEach
    public void tearDown() {
        fileRepo = null;
        part = null;
    }
}