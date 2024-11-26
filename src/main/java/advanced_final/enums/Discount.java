package advanced_final.enums;

import java.util.Arrays;

public enum Discount {
    MAN_OF_NATIONAL_MERIT(1, "국가유공자", 0.1),
    SOLDIERS(2, "군인", 0.05),
    STUDENT(3, "학생", 0.03),
    GENERAL(4, "일반", 0);

    private final int index;
    private final String name;
    private final double discountRate;

    Discount(int index, String name, double discountRate) {
        this.index = index;
        this.name = name;
        this.discountRate = discountRate;
    }

    public int getIndex() { return index; }
    public double getDiscountRate() { return this.discountRate; }

    public static double findDiscountRate(int index) {
        return Arrays.stream(values())
            .filter(val -> val.getIndex() == index)
            .mapToDouble(Discount::getDiscountRate)
            .findFirst().orElse(0);
    }

    public String toString() {
        return this.name + "\t : " + (this.discountRate * 100) + "%";
    }

    public static String getDiscountList() {
        return Arrays.stream(values())
            .map(val -> String.format("%d. %s", val.getIndex(), val))
            .reduce((a, b) -> String.join("\n", a, b))
            .orElse("");
    }
}
