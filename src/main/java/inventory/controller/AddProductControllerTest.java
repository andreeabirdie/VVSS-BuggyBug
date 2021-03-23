package inventory.controller;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.repository.InventoryFileRepository;
import inventory.repository.InventoryInMemoryRepository;
import inventory.service.InventoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.DisplayName("Add part test")
class AddProductControllerTest {

    private AddProductController addProductController;
    private InventoryInMemoryRepository inventory;

    String name;
    String price;
    String inStock;
    String min;
    String max;
    ObservableList<Part> parts;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Part part1 = new InhousePart(1, "part1", 1.0, 5, 1, 10, 1);
        Part part2 = new InhousePart(2, "part2", 1.0, 5, 1, 10, 2);
        inventory = new InventoryInMemoryRepository();
        InventoryFileRepository repo = new InventoryFileRepository(inventory, "data/testData.txt");
        InventoryService service = new InventoryService(repo);
        addProductController = new AddProductController(service);
        inventory.addPart(part1);
        inventory.addPart(part2);
        // Arrange - part 1 (correct dummy data)
        name = "part";
        price = "5.0";
        inStock = "5";
        min = "1";
        max = "10";
        parts = FXCollections.observableArrayList();
        parts.add(part1);
        parts.add(part2);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        // clean testData.txt after for every test
        PrintWriter pw = null;
        ClassLoader classLoader = AddProductControllerTest .class.getClassLoader();
        try {
            pw = new PrintWriter(classLoader.getResource("data/testData.txt").getFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (pw != null) {
            pw.close();
        }
    }

    @org.junit.jupiter.api.Order(0)
    @org.junit.jupiter.api.Timeout(10)
    @org.junit.jupiter.api.Tag("part")
    @org.junit.jupiter.api.Test
    void addProductECPValidNameIsOk() {
        // Arrange - part 2
        name = "produsulMeu";
        // Act
        assertDoesNotThrow(() -> addProductController.addProduct(name, price, inStock, min, max, parts));
        // Assert
        assertEquals(1, inventory.getProducts().size());
    }

    @org.junit.jupiter.api.Order(1)
    @org.junit.jupiter.api.Timeout(10)
    @org.junit.jupiter.api.Tag("part")
    @org.junit.jupiter.api.Test
    void addProductECPNonvalidNameIsEmpty() {
        // Arrange - part 2
        name = "";
        // Act
        assertThrows(Exception.class, () -> addProductController.addProduct(name, price, inStock, min, max, parts));
        // Assert
        assertEquals(0, inventory.getProducts().size());
    }

    @org.junit.jupiter.api.Order(2)
    @org.junit.jupiter.api.Timeout(10)
    @org.junit.jupiter.api.Tag("part")
    @org.junit.jupiter.api.Test
    void addProductECPValidPriceIsOk() {
        // Arrange - part 2
        price="4.5";
        // Act
        assertDoesNotThrow(() -> addProductController.addProduct(name, price, inStock, min, max, parts));
        // Assert
        assertEquals(1, inventory.getProducts().size());
    }

    @org.junit.jupiter.api.Order(3)
    @org.junit.jupiter.api.Timeout(10)
    @org.junit.jupiter.api.Tag("part")
    @org.junit.jupiter.api.Test
    void addProductECPNonvalidPriceIsNegative() {
        // Arrange - part 2
        price = "-10.0";
        // Act
        assertThrows(Exception.class, () -> addProductController.addProduct(name, price, inStock, min, max, parts));
        // Assert
        assertEquals(0, inventory.getProducts().size());
    }

    @org.junit.jupiter.api.Order(4)
    @org.junit.jupiter.api.Timeout(10)
    @org.junit.jupiter.api.Tag("part")
    @org.junit.jupiter.api.Test
    void addProductBVANonvalidNameIsNull() {
        // Arrange - part 2
        name = null; // length(name) = -1
        // Act
        assertThrows(Exception.class, () -> addProductController.addProduct(name, price, inStock, min, max, parts));
        // Assert
        assertEquals(0, inventory.getProducts().size());
    }

    @org.junit.jupiter.api.Order(5)
    @org.junit.jupiter.api.Timeout(10)
    @org.junit.jupiter.api.Tag("part")
    @org.junit.jupiter.api.Test
    void addProductBVAValidNameIsOk() {
        // Arrange - part 2
        name = "M";
        // Act
        assertDoesNotThrow(() -> addProductController.addProduct(name, price, inStock, min, max, parts));
        // Assert
        assertEquals(1, inventory.getProducts().size());
    }

    @org.junit.jupiter.api.Order(6)
    @org.junit.jupiter.api.Timeout(10)
    @org.junit.jupiter.api.Tag("part")
    @org.junit.jupiter.api.Test
    void addProductBVANonvalidPriceIs0() {
        // Arrange - part 2
        price="0";
        // Act
        assertThrows(Exception.class, () -> addProductController.addProduct(name, price, inStock, min, max, parts));
        // Assert
        assertEquals(0, inventory.getProducts().size());
    }

    @org.junit.jupiter.api.Order(7)
    @org.junit.jupiter.api.Timeout(10)
    @org.junit.jupiter.api.Tag("part")
    @org.junit.jupiter.api.Test
    void addProductBVANonvalidPriceIsNegative1() {
        // Arrange - part 2
        price="-1";
        // Act
        assertThrows(Exception.class, () -> addProductController.addProduct(name, price, inStock, min, max, parts));
        // Assert
        assertEquals(0, inventory.getProducts().size());
    }
}