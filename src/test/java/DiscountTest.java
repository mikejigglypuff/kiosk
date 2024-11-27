import advanced_final.enums.Discount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountTest {

    @Test
    public void testFindDiscountRate() {
        assertEquals(0.05, Discount.findDiscountRate(2));
    }

    @Test
    public void testFindWrongDiscountRate() {
        assertEquals(0, Discount.findDiscountRate(-1));
    }

    @Test
    public void testToString() {
        String comp = "학생" + "\t : 3.0%";
        assertEquals(comp, Discount.STUDENT.toString());
    }

    @Test
    public void testGetDiscountList() {
        String comp = "1. 국가유공자\t : " + Discount.MAN_OF_NATIONAL_MERIT.getDiscountRate() * 100
            + "%\n2. 군인\t : " + Discount.SOLDIERS.getDiscountRate() * 100
            + "%\n3. 학생\t : " + Discount.STUDENT.getDiscountRate() * 100
            + "%\n4. 일반\t : " + Discount.GENERAL.getDiscountRate() * 100 + "%";
        assertEquals(comp, Discount.getDiscountList());
    }
}
