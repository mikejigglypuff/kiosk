import advanced_final.Basket;
import advanced_final.MenuItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BasketTest {
    private static Basket basket;
    private final List<MenuItem> menuItems = new ArrayList<>(Arrays.asList(
        new MenuItem("test1", 4.7, "test item1", false),
        new MenuItem("test2", 5.2, "test item2", false),
        new MenuItem("test3", 8.1, "test item3", true)
    ));

    @BeforeEach
    public void initTest() {
        basket = new Basket();
    }

    @Test
    public void testAddItem() {
        MenuItem menuItem = menuItems.get(0);

        basket.addItem(menuItem);

        assertEquals(1, basket.getItemNumber());
        assertEquals(menuItem, basket.getMenuItems().get(0));
    }

    @Test
    public void testGetPriceSum() {
        double sum = 0;

        for(MenuItem i : menuItems) {
            basket.addItem(i);
            sum += i.getPrice();
        }

        assertEquals(sum, basket.getPriceSum());
    }

    @Test
    public void testGetDiscountPriceSum() {
        double sum = 0, discount = 0.1;
        for(MenuItem i : menuItems) {
            basket.addItem(i);
            sum += i.getPrice();
        }

        assertEquals(sum * 1000 * (1 - discount) / 1000, basket.getPriceSum(0.1));
    }

    @Test
    public void testClearItem() {
        for(MenuItem i : menuItems) {
            basket.addItem(i);
        }

        basket.clearItem();
        assertEquals(0, basket.getItemNumber());
    }

    @Test
    public void testGetItemDescriptions() {
        StringBuilder sb = new StringBuilder();

        for(MenuItem i : menuItems) {
            basket.addItem(i);
            sb.append(i.toString()).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);

        assertEquals(sb.toString(), basket.getItemDescriptions());
    }
}
