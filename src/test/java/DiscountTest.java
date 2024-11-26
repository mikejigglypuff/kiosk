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
        String comp = "학생" + "\t : 3%";
        assertEquals(comp, Discount.STUDENT.toString());
    }

    @Test
    public void testGetDiscountList() {
        String comp = "1. 국가유공자\n2. 군인\n3. 학생\n4. 일반";
        assertEquals(comp, Discount.getDiscountList());
    }
}
