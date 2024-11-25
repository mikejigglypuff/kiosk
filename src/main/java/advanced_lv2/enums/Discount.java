package advanced_lv2.enums;

import java.util.Arrays;

public enum Discount {
    MAN_OF_NATIONAL_MERIT("국가유공자", 0.1),
    SOLDIERS("군인", 0.05),
    STUDENT("학생", 0.03),
    GENERAL("일반", 0);

    private final String name;
    private final double discountRate;

    Discount(String name, double discountRate) {
        this.name = name;
        this.discountRate = discountRate;
    }

    public double getDiscountRate() { return this.discountRate; }

    public static double findDiscountRate(int index) {
        return Arrays.stream(values())
            .filter(val -> val.ordinal() + 1 == index)
            .mapToDouble(Discount::getDiscountRate)
            .findFirst().orElse(0);
    }

    public String toString() {
        return this.name + "\t : " + (this.discountRate * 100) + "%";
    }

    public static String getDiscountList() {
        return Arrays.stream(values())
            .map(val -> String.format("%d. %s", val.ordinal() + 1, val.toString()))
            .reduce((a, b) -> String.join("\n", a, b))
            .orElse("");
    }
}
