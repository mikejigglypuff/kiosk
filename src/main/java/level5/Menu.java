package level5;

import java.util.List;

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

    public String getItemDescription(int n) throws IndexOutOfBoundsException {
        if(n < 0 || n >= menuItems.size())
            throw new IndexOutOfBoundsException("0 ~ " + menuItems.size() + " 사이의 수를 입력하세요.");
        return menuItems.get(n).toString() + "\n";
    }

    public String summarizeMenu() {
        int menuAmount = menuItems.size();
        StringBuilder sb = new StringBuilder();
        sb.append("[ ").append(category).append(" MENU ]\n");
        for(int i = 0; i < menuAmount; i++) {
            sb.append(i + 1).append(". ").append(menuItems.get(i).toString()).append("\n");
        }
        sb.append("0. 뒤로가기\n");

        return sb.toString();
    }
}
