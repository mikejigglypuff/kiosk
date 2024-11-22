package level5;

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

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(name).append("\t| ")
            .append("W ").append(price).append("\t| ")
            .append(description);

        return sb.toString();
    }
}
