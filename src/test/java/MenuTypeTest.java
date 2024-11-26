import advanced_final.enums.MenuType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTypeTest {
    @Test
    public void testOf() {
        assertEquals(MenuType.DESSERT, MenuType.of(2));
    }

    @Test
    public void testOfIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> MenuType.of(3));
    }
}
