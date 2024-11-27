import advanced_final.MenuItem;
import advanced_final.enums.MenuType;
import advanced_final.enums.Menu;
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
        Map<MenuType, Integer> indexMap = new HashMap<>();
        for(MenuType m : MenuType.values()) {
            List<MenuItem> list = new ArrayList<>();
            comp.put(m, list);
            indexMap.put(m, 0);
        }

        for(Menu s : Menu.values()) {
            MenuType type = s.getType();
            if(s.getMenuItem().isRecommandation()) {
                int index = indexMap.get(type);
                comp.get(type).add(index, s.getMenuItem());
                indexMap.put(type, ++index);
            }
            else comp.get(type).add(s.getMenuItem());
        }

        assertEquals(comp, Menu.getMenuList());
    }
}