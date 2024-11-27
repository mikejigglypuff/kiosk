package advanced_final.enums;

public enum MenuType {
    BURGERS(0, "Burger"),
    DRINK(1, "Drink"),
    DESSERT(2, "Dessert");

    private final int index;
    private final String TypeName;

    MenuType(int index, String categoryName) {
        this.index = index;
        this.TypeName = categoryName;
    }

    // Getter
    public int getIndex() {
        return index;
    }
    public String getTypeName() {
        return TypeName;
    }

    public static MenuType of(int index) throws IndexOutOfBoundsException {
        for(MenuType m : MenuType.values()) {
            if(m.getIndex() == index) return m;
        }

        throw new IndexOutOfBoundsException();
    }
}
