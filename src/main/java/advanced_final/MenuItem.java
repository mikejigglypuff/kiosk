package advanced_final;

public class MenuItem {
    private final String name;
    private final double price;
    private final String description;
    private final boolean recommandation;

    public MenuItem(String name, double price, String description, boolean recommandation) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.recommandation = recommandation;
    }

    public String getName() {
        return name;
    }
    public double getPrice() { return price; }
    public boolean isRecommandation() { return recommandation; }


    @Override
    public String toString() {
        return name + "\t| " + "W " + price + "\t| " + description;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof MenuItem m) {
            return (this.toString().equals(m.toString()) && this.recommandation == m.isRecommandation());
        }
        return false;
    }
}
