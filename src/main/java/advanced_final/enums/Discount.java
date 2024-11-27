package advanced_final.enums;

import java.util.Arrays;

public enum Discount {
    MAN_OF_NATIONAL_MERIT(1, "국가유공자", 0.1),
    SOLDIERS(2, "군인", 0.05),
    STUDENT(3, "학생", 0.03),
    GENERAL(4, "일반", 0);

    // ordinal() 대신 상수들의 순서를 정의할 필드
    private final int index;
    private final String name;
    private final double discountRate;

    Discount(int index, String name, double discountRate) {
        this.index = index;
        this.name = name;
        this.discountRate = discountRate;
    }

    // Getter
    public int getIndex() { return index; }
    public double getDiscountRate() { return this.discountRate; }

    // 일치하는 인덱스 값을 갖는 상수의 할인률 반환
    public static double findDiscountRate(int index) {
        return Arrays.stream(values())
            .filter(val -> val.getIndex() == index)
            .mapToDouble(Discount::getDiscountRate)
            .findFirst().orElse(0);
    }

    // Discount 상수 목록을 프롬프트 창에서 출력할 문자열로 변환
    public static String getDiscountList() {
        return Arrays.stream(values())
            .map(val -> String.format("%d. %s", val.getIndex(), val))
            .reduce((a, b) -> String.join("\n", a, b))
            .orElse("");
    }

    @Override
    public String toString() {
        return this.name + "\t : " + (this.discountRate * 100) + "%";
    }
}
