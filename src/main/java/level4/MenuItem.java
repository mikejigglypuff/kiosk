package level4;

public class MenuItem {
    public final String name;
    public double price;
    public final String description;

    public MenuItem(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(name).append("\t| ")
            .append("W ").append(price).append("\t| ")
            .append(description);

        return sb.toString();
    }
}
