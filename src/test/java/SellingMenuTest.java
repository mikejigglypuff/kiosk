import advanced_final.MenuItem;
import advanced_final.enums.MenuType;
import advanced_final.enums.SellingMenu;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SellingMenuTest {
    @Test
    public void testGetMenuList() {
        Map<MenuType, List<MenuItem>> comp = new HashMap<>();
        for(MenuType m : MenuType.values()) {
            List<MenuItem> list = new ArrayList<>();
            comp.put(m, list);
        }

        for(SellingMenu s : SellingMenu.values()) {
            comp.get(s.getType()).add(s.getMenuItem());
        }

        assertEquals(comp, SellingMenu.getMenuList());
    }
}