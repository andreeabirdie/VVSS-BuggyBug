package inventory.service;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.repository.InventoryFileRepository;
import inventory.repository.InventoryInMemoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class InventoryServiceNoMocksTest {

    private Part part;
    private InventoryInMemoryRepository repo;
    private InventoryFileRepository fileRepo;
    private InventoryService service;

    @BeforeEach
    public void setUp() {
        part = new InhousePart(1, "part1", 1.0, 5, 1, 10, 1);
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
        assertEquals(service.getAllParts().size(),0);
        fileRepo.addPart(part);
        assertEquals(service.getAllParts().size(), 1);
        assertEquals(service.getAllParts().get(0).getName(), "part1");
    }

    @Test
    void deletePart() {
        fileRepo.addPart(part);
        assertEquals(service.getAllParts().size(), 1);
        service.deletePart(part);
        assertEquals(service.getAllParts().size(), 0);
    }
}