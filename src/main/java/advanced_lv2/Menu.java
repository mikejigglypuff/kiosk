package advanced_lv2;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Menu {
    private final List<MenuItem> menuItems;
    private final String category;

    public Menu(List<MenuItem> menuItems, String category) {
        this.menuItems = menuItems;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public MenuItem getItem(int n) throws IndexOutOfBoundsException {
        if(n < 0 || n >= menuItems.size())
            throw new IndexOutOfBoundsException("0 ~ " + menuItems.size() + " 사이의 수를 입력하세요.");
        return menuItems.get(n);
    }

    public int getMenuAmount() { return menuItems.size(); }

    public String summarizeMenu() {
        AtomicInteger index = new AtomicInteger(1);

        return menuItems.stream()
            .map(MenuItem::toString)
            .map(val -> String.format("%d. %s", index.getAndIncrement(), val))
            .collect(Collectors.joining("\n"));
    }
}
