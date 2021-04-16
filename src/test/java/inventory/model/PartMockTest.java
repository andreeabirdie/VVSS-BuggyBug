package inventory.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartMockTest {

    @Test
    void getName() {
        Part part = new InhousePart(1, "part1", 1.0, 5, 1, 10, 1);
        assertEquals(part.getName(), "part1");
    }

    @Test
    void getPrice() {
        Part part = new InhousePart(1, "part1", 1.0, 5, 1, 10, 1);
        assertEquals(part.getPrice(), 1.0);
    }
}