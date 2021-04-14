package inventory.service;

import inventory.model.Part;
import inventory.repository.InventoryFileRepository;
import inventory.repository.InventoryInMemoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;

class InventoryServiceMockPartTest {

    private Part part;
    private InventoryInMemoryRepository repo;
    private InventoryFileRepository fileRepo;
    private InventoryService service;

    @BeforeEach
    public void setUp() {
        part = mock(Part.class);
        repo = new InventoryInMemoryRepository();
        fileRepo = new InventoryFileRepository(repo, "data/testData.txt");
        service = new InventoryService(fileRepo);
    }

    @AfterEach
    void tearDown() {
        // clean testData.txt after for every test
        PrintWriter pw = null;
        ClassLoader classLoader = InventoryServiceMockPartTest.class.getClassLoader();
        try {
            pw = new PrintWriter(classLoader.getResource("data/testData.txt").getFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (pw != null) {
            pw.close();
        }
    }

    @Test
    void getAllParts() {
        fileRepo.addPart(part);
        assertEquals(service.getAllParts().size(), 1);
        fileRepo.addPart(part);
        assertEquals(service.getAllParts().size(), 2);
    }

    @Test
    void deletePart() {
        fileRepo.addPart(part);
        assertEquals(service.getAllParts().size(), 1);
        service.deletePart(part);
        assertEquals(service.getAllParts().size(), 0);
    }
}