import advanced_final.Basket;
import advanced_final.Kiosk;
import advanced_final.MenuItem;
import advanced_final.enums.Discount;
import advanced_final.enums.MenuType;
import advanced_final.enums.SellingMenu;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class KioskTest {
    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final Map<String, String> inputs = new HashMap<>();
    private static final InputStream originalIn = System.in;
    private static Kiosk kiosk;
    private static Basket basket;
    private static Basket emptyBasket;
    private static final Map<MenuType, List<MenuItem>> menus = SellingMenu.getMenuList();

    @BeforeAll
    public static void setInputs() {
        basket = new Basket();
        emptyBasket = new Basket();

        // confirmOrder 입력 데이터
        inputs.put("confirm", "1\n1\n");
        inputs.put("confirmNoDis", "1\n5\n");
        inputs.put("notConfirm", "2\n");

        // deleteOrder 입력 데이터
        inputs.put("delete", "1\n");
        inputs.put("noDelete", "2\n");

        // start 입력 데이터
        inputs.put("outOfIndex", "-1\n");
        inputs.put("outOfIndexWithEmptyBasket", "4\n");
        inputs.put("inputMismatch", "O\n");
        inputs.put("exitKiosk", "0\n");
        inputs.put("addBasket", "1\n1\n1\n0\n0\n");
        inputs.put("notAddBasket", "1\n1\n0\n0\n0\n");
        inputs.put("outOfIndexWithMenuItems", "1\n-1\n");
    }

    @BeforeEach
    public void beforeEach() {
        basket.addItem(menus.get(MenuType.BURGERS).get(3));
        basket.addItem(menus.get(MenuType.DRINK).get(2));
        basket.addItem(menus.get(MenuType.DESSERT).get(1));
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void afterEach() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        outputStream.reset();
        basket.clearItem();
    }

    @AfterAll
    public static void close() throws IOException {
        outputStream.close();
    }

    @Test
    public void testConfirmOrder() {
        String comp = assertionFormat(confirmOrderComp(1));

        System.setIn(new ByteArrayInputStream(inputs.get("confirm").getBytes()));
        kiosk = new Kiosk(menus, basket);
        kiosk.confirmOrder();

        assertEquals(comp, assertionFormat(outputStream.toString()));
        assertEquals(0, basket.getItemNumber());
    }

    @Test
    public void testConfirmWithNoDiscount() {
        String comp = assertionFormat(confirmOrderComp(5));

        System.setIn(new ByteArrayInputStream(inputs.get("confirmNoDis").getBytes()));
        kiosk = new Kiosk(menus, basket);
        kiosk.confirmOrder();

        assertEquals(comp, assertionFormat(outputStream.toString().trim()));
        assertEquals(0, basket.getItemNumber());
    }

    @Test
    public void testNotConfirmOrder() {
        System.setIn(new ByteArrayInputStream(inputs.get("notConfirm").getBytes()));
        kiosk = new Kiosk(menus, basket);
        kiosk.confirmOrder();

        // trim()으로 효과 본 곳
        assertEquals(assertionFormat(questionOrder()), assertionFormat(outputStream.toString()));
        assertEquals(3, basket.getItemNumber());
    }


    @Test
    public void testCancelOrder() {
        System.setIn(new ByteArrayInputStream(inputs.get("delete").getBytes()));
        kiosk = new Kiosk(menus, basket);
        kiosk.deleteOrder();

        String comp = questionCancel() + "주문이 정상적으로 취소되었습니다.\n";
        assertEquals(assertionFormat(comp),
            assertionFormat(outputStream.toString()));
        assertEquals(0, basket.getItemNumber());
    }

    @Test
    public void testNoCancelOrder() {
        System.setIn(new ByteArrayInputStream(inputs.get("noDelete").getBytes()));
        kiosk = new Kiosk(menus, basket);
        kiosk.deleteOrder();

        assertEquals(assertionFormat(questionCancel()), assertionFormat(outputStream.toString()));
        assertEquals(3, basket.getItemNumber());
    }


    @Test
    public void testDisplayMenu() {
        kiosk = new Kiosk(menus);
        List<MenuItem> list = menus.get(MenuType.BURGERS);
        kiosk.displayMenu(list);

        assertEquals(assertionFormat(displayMenuText(list)), assertionFormat(outputStream.toString()));
    }


    @Test
    public void testDisplayMenus() {
        kiosk = new Kiosk(menus, basket);
        kiosk.displayMenus();

        assertEquals(assertionFormat(displayMenusText(true)), assertionFormat(outputStream.toString()));
    }

    @Test
    public void testDisplayMenusWithoutBasket() {
        kiosk = new Kiosk(menus, emptyBasket);
        kiosk.displayMenus();

        assertEquals(assertionFormat(displayMenusText(false)), assertionFormat(outputStream.toString()));
    }


    @Test
    public void testStartOutOfIndex() {
        System.setIn(new ByteArrayInputStream(inputs.get("outOfIndex").getBytes()));
        kiosk = new Kiosk(menus, basket);
        kiosk.start();

        String comp = displayMenusText(true) + "0 ~ " + (menus.size() + 2) + " 사이의 수를 입력하세요.\n";

        assertEquals(assertionFormat(comp), assertionFormat(outputStream.toString()));
    }

    @Test
    public void testStartOutOfIndexWithEmptyBasket() {
        System.setIn(new ByteArrayInputStream(inputs.get("outOfIndexWithEmptyBasket").getBytes()));
        kiosk = new Kiosk(menus);
        kiosk.start();

        String comp = displayMenusText(false) + "0 ~ " + (menus.size()) + " 사이의 수를 입력하세요.\n";

        assertEquals(assertionFormat(comp), assertionFormat(outputStream.toString()));
    }

    @Test
    public void testStartInputMismatch() {
        System.setIn(new ByteArrayInputStream(inputs.get("inputMismatch").getBytes()));
        kiosk = new Kiosk(menus);
        kiosk.start();

        String comp = displayMenusText(false) + "잘못된 입력입니다.\n";

        assertEquals(assertionFormat(comp), assertionFormat(outputStream.toString()));
    }

    @Test
    public void testStartExitKiosk() {
        System.setIn(new ByteArrayInputStream(inputs.get("exitKiosk").getBytes()));
        kiosk = new Kiosk(menus);
        kiosk.start();

        String comp = displayMenusText(false) + exitText();

        assertEquals(assertionFormat(comp.trim()), assertionFormat(outputStream.toString()));
    }

    @Test
    public void testStartAddBasket() {
        System.setIn(new ByteArrayInputStream(inputs.get("addBasket").getBytes()));
        kiosk = new Kiosk(menus, basket);
        kiosk.start();

        List<MenuItem> curMenu = menus.get(MenuType.BURGERS);
        MenuItem curItem = curMenu.get(0);
        String comp = displayMenusText(true) + displayMenuText(curMenu)
            + curItem.toString() + "\n위 메뉴를 장바구니에 추가하시겠습니까?\n1.확인     2. 취소\n"
            + curItem.getName() + " 이(가) 장바구니에 추가되었습니다.\n" + displayMenuText(curMenu)
            + displayMenusText(true) + "프로그램을 종료합니다.\n";

        assertEquals(assertionFormat(comp), assertionFormat(outputStream.toString()));
        assertEquals(4, basket.getItemNumber());
    }

    @Test
    public void testStartNotAddBasket() {
        System.setIn(new ByteArrayInputStream(inputs.get("notAddBasket").getBytes()));
        kiosk = new Kiosk(menus);
        kiosk.start();

        List<MenuItem> curMenu = menus.get(MenuType.BURGERS);
        MenuItem curItem = curMenu.get(0);
        String comp = displayMenusText(false) + displayMenuText(curMenu)
            + curItem.toString() + "\n위 메뉴를 장바구니에 추가하시겠습니까?\n1.확인     2. 취소\n"
            + displayMenuText(curMenu) + displayMenusText(false) + "프로그램을 종료합니다.\n";

        assertEquals(assertionFormat(comp), assertionFormat(outputStream.toString()));
        assertEquals(3, basket.getItemNumber());
    }

    @Test
    public void testStartOutOfIndexWithMenuItems() {
        System.setIn(new ByteArrayInputStream(inputs.get("outOfIndexWithMenuItems").getBytes()));
        kiosk = new Kiosk(menus);
        kiosk.start();

        List<MenuItem> curMenu = menus.get(MenuType.BURGERS);
        String comp = displayMenusText(false) + displayMenuText(curMenu)
            + "0 ~ " + curMenu.size() + " 사이의 수를 입력하세요.\n";

        assertEquals(assertionFormat(comp),
            assertionFormat(outputStream.toString()));
    }


    private String confirmOrderComp(int discount) {
        return questionOrder() + "할인 정보를 입력해주세요.\n" + Discount.getDiscountList()
            + "\n주문이 완료되었습니다. 금액은 W " + basket.getPriceSum(Discount.findDiscountRate(discount))
            + " 입니다.\n";
    }

    private String questionOrder() {
        return "아래와 같이 주문하시겠습니까?\n"
            + basket.getItemDescriptions() + "\n[ TOTAL ]\nW " + basket.getPriceSum()
            + "\n\n 1. 주문       2. 메뉴판\n";
    }

    private String questionCancel() {
        return "주문을 취소하시겠습니까?\n1. 예         2. 아니오\n";
    }

    private String displayMenuText(List<MenuItem> list) {
        StringBuilder comp = new StringBuilder();
        int index = 1;
        for(MenuItem item : list) {
            comp.append(index++).append(". ").append(item.toString()).append("\n");
        }
        comp.append("0. 뒤로가기\n");

        return comp.toString();
    }

    private String displayMenusText(boolean basketNotEmpty) {
        StringBuilder comp = new StringBuilder();
        int menuAmount = menus.size();
        comp.append("[ MAIN MENU ]\n");
        for(MenuType m : menus.keySet()) {
            comp.append(m.getIndex() + 1).append(". ").append(m.getTypeName()).append("\n");
        }
        comp.append("0. 종료      | 종료\n");

        if(basketNotEmpty) {
            comp.append("\n[ ORDER MENU ]\n")
                .append(menuAmount + 1).append(". Orders\n").append(menuAmount + 2).append(". Cancel\n");
        }

        return comp.toString();
    }

    private String exitText() {
        return "프로그램을 종료합니다.\n";
    }

    private String assertionFormat(String str) {
        return str.replace("\r\n", "\n").trim();
    }
}
