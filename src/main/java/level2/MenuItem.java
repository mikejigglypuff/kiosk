package level2;

public class MenuItem {
    public final String name;
    public double price = -1;
    public final String description;

    public MenuItem(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public MenuItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String summarizeMenu() {
        StringBuilder sb = new StringBuilder();

        sb.append(name).append("\t| ");
        if(price >= 0) sb.append("W ").append(price).append("\t| ");
        sb.append(description);

        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        if(price >= 0) {
            sb.append("메뉴 이름: ").append(name);
            sb.append("\n가격: ").append(price);
            sb.append("\n설명: ").append(description);
        } else {
            sb.append("프로그램을 종료합니다.");
        }

        return sb.toString();
    }
}
