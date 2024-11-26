package advanced_final;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Basket {
    private final List<MenuItem> menuItems = new ArrayList<>();
    private int itemNumber = 0;

    public void addItem(MenuItem item) {
        menuItems.add(item);
        ++itemNumber;
        System.out.println( item.getName()+ " 이(가) 장바구니에 추가되었습니다.");
    }

    public void clearItem() {
        menuItems.clear();
        itemNumber = 0;
    }

    public int getItemNumber() { return itemNumber; }

    public double getPriceSum(double discount) {
        return menuItems.stream()
            .mapToDouble(MenuItem::getPrice)
            .map(val -> val * 1000 * (1 - discount))
            .sum() / 1000;
    }

    public double getPriceSum() {
        return menuItems.stream()
            .mapToDouble(MenuItem::getPrice)
            .sum();
    }

    public String getItemDescriptions() {
        return menuItems.stream()
            .map(MenuItem::toString)
            .collect(Collectors.joining("\n"));
    }
}
