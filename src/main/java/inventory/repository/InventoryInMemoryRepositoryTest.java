package inventory.repository;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class InventoryInMemoryRepositoryTest {
    private InventoryInMemoryRepository inventory;
    ObservableList<Part> parts;
    Product produsExistent;

    @BeforeEach
    void setUp() {
        inventory = new InventoryInMemoryRepository();

        parts = FXCollections.observableArrayList();
        Part part1 = new InhousePart(1, "part1", 1.0, 5, 1, 10, 1);
        Part part2 = new InhousePart(2, "part2", 1.0, 5, 1, 10, 2);
        inventory.addPart(part1);
        inventory.addPart(part2);
        parts.add(part1);
        parts.add(part2);

        produsExistent = new Product(1, "produs", 100.0,10,1,100,parts );
        inventory.addProduct(produsExistent);
    }

    @Test
    void lookupProductNonvalid() {
        String searchTerm = "-1";
        Product produsCautat1 = inventory.lookupProduct(searchTerm);
        assertNull(produsCautat1);
    }

    @Test
    void lookupProductValidId(){
        String searchTerm = "1";
        Product produsCautat2 = inventory.lookupProduct("1");
        assertEquals(produsCautat2.getProductId(), Integer.parseInt(searchTerm));
    }

    @Test
    void lookupProductValidNume(){
        String searchTerm = "produs";
        Product produsCautat3 = inventory.lookupProduct(searchTerm);
        assertEquals(produsCautat3.getName(), searchTerm);
    }
}