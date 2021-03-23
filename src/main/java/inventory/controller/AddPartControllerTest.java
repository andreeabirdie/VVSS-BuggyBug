package inventory.controller;

import inventory.repository.InventoryFileRepository;
import inventory.repository.InventoryInMemoryRepository;
import inventory.service.InventoryService;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.DisplayName("Add part test")
class AddPartControllerTest {
    private AddPartController addPartController;
    private InventoryInMemoryRepository inventory;

    String name;
    String price;
    String inStock;
    String min;
    String max;
    String partDynamicValue;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        inventory = new InventoryInMemoryRepository();
        InventoryFileRepository repo = new InventoryFileRepository(inventory, "data/testData.txt");
        InventoryService service = new InventoryService(repo);
        addPartController = new AddPartController();
        addPartController.setService(service);
        // Arrange - part 1 (correct dummy data)
        name = "part";
        price = "5.0";
        inStock = "5";
        min = "1";
        max = "10";
        partDynamicValue = "1234";
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        // clean testData.txt after for every test
        PrintWriter pw = null;
        ClassLoader classLoader = AddPartControllerTest .class.getClassLoader();
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
    void addPartECPValidNameIsOk() {
        // Arrange - part 2
        name = "piesaMea";
        // Act
        assertDoesNotThrow(() -> addPartController.addPart(name, price, inStock, min, max, partDynamicValue));
        // Assert
        assertEquals(1, inventory.getAllParts().size());
    }

    @org.junit.jupiter.api.Order(1)
    @org.junit.jupiter.api.Timeout(10)
    @org.junit.jupiter.api.Tag("part")
    @org.junit.jupiter.api.Test
    void addPartECPNonvalidNameIsEmpty() {
        // Arrange - part 2
        name = "";
        // Act
        assertThrows(Exception.class, () -> addPartController.addPart(name, price, inStock, min, max, partDynamicValue));
        // Assert
        assertEquals(0, inventory.getAllParts().size());
    }

    @org.junit.jupiter.api.Order(2)
    @org.junit.jupiter.api.Timeout(10)
    @org.junit.jupiter.api.Tag("part")
    @org.junit.jupiter.api.Test
    void addPartECPValidPriceIsOk() {
        // Arrange - part 2
        price="4.5";
        // Act
        assertDoesNotThrow(() -> addPartController.addPart(name, price, inStock, min, max, partDynamicValue));
        // Assert
        assertEquals(1, inventory.getAllParts().size());
    }

    @org.junit.jupiter.api.Order(3)
    @org.junit.jupiter.api.Timeout(10)
    @org.junit.jupiter.api.Tag("part")
    @org.junit.jupiter.api.Test
    void addPartECPNonvalidPriceIsNegative() {
        // Arrange - part 2
        price = "-10.0";
        // Act
        assertThrows(Exception.class, () -> addPartController.addPart(name, price, inStock, min, max, partDynamicValue));
        // Assert
        assertEquals(0, inventory.getAllParts().size());
    }

    @org.junit.jupiter.api.Order(4)
    @org.junit.jupiter.api.Timeout(10)
    @org.junit.jupiter.api.Tag("part")
    @org.junit.jupiter.api.Test
    void addPartBVANonvalidNameIsNull() {
        // Arrange - part 2
        name = null; // length(name) = -1
        // Act
        assertThrows(Exception.class, () -> addPartController.addPart(name, price, inStock, min, max, partDynamicValue));
        // Assert
        assertEquals(0, inventory.getAllParts().size());
    }

    @org.junit.jupiter.api.Order(5)
    @org.junit.jupiter.api.Timeout(10)
    @org.junit.jupiter.api.Tag("part")
    @org.junit.jupiter.api.Test
    void addPartBVAValidNameIsOk() {
        // Arrange - part 2
        name = "M";
        // Act
        assertDoesNotThrow(() -> addPartController.addPart(name, price, inStock, min, max, partDynamicValue));
        // Assert
        assertEquals(1, inventory.getAllParts().size());
    }

    @org.junit.jupiter.api.Order(6)
    @org.junit.jupiter.api.Timeout(10)
    @org.junit.jupiter.api.Tag("part")
    @org.junit.jupiter.api.Test
    void addPartBVANonvalidPriceIs0() {
        // Arrange - part 2
        price="0";
        // Act
        assertThrows(Exception.class, () -> addPartController.addPart(name, price, inStock, min, max, partDynamicValue));
        // Assert
        assertEquals(0, inventory.getAllParts().size());
    }

    @org.junit.jupiter.api.Order(7)
    @org.junit.jupiter.api.Timeout(10)
    @org.junit.jupiter.api.Tag("part")
    @org.junit.jupiter.api.Test
    void addPartBVANonvalidPriceIsNegative1() {
        // Arrange - part 2
        price="-1";
        // Act
        assertThrows(Exception.class, () -> addPartController.addPart(name, price, inStock, min, max, partDynamicValue));
        // Assert
        assertEquals(0, inventory.getAllParts().size());
    }

    @org.junit.jupiter.api.Order(8)
    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled
    void ignoredTest() {

    }
}