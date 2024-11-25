package advanced_lv2;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private final List<MenuItem> menuItems = new ArrayList<>();
    private int itemNumber = 0;

    public void addItem(MenuItem item) {
        menuItems.add(item);
        ++itemNumber;
        System.out.println(item.getName() + " 이(가) 장바구니에 추가되었습니다.");
    }

    public void clearItem() {
        menuItems.clear();
        itemNumber = 0;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public double getPriceSum() {
        double result = 0d;

        for (MenuItem item : menuItems) {
            result += item.getPrice() * 10;
        }

        return result / 10;
    }

    public String getItemDescriptions() {
        StringBuilder sb = new StringBuilder();

        sb.append("[ ORDERS ]\n");
        for (MenuItem item : menuItems) {
            sb.append(item.toString()).append("\n");
        }

        return sb.toString();
    }
}
