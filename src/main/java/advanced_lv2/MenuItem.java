package advanced_lv2;

public class MenuItem {
    private final String name;
    private final double price;
    private final String description;

    public MenuItem(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + "\t| " + "W " + price + "\t| " + description;
    }
}
