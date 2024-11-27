package advanced_final;

import advanced_final.enums.Discount;
import advanced_final.enums.MenuType;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Kiosk {
    private final Map<MenuType, List<MenuItem>> menus;
    private final Basket basket;
    private final Scanner sc = new Scanner(System.in);

    public Kiosk(Map<MenuType, List<MenuItem>> menus){
        this.basket = new Basket();
        this.menus = menus;
    }

    public Kiosk(Map<MenuType, List<MenuItem>> menus, Basket basket) {
        this.basket = basket;
        this.menus = menus;
    }

    // 장바구니에 담긴 메뉴들 주문 처리
    public void confirmOrder() {
        System.out.println("아래와 같이 주문하시겠습니까?\n"
            + basket.getItemDescriptions() + "\n[ TOTAL ]\nW " + basket.getPriceSum()
            + "\n\n 1. 주문       2. 메뉴판");

        if (sc.nextInt() == 1) {
            System.out.println("할인 정보를 입력해주세요.\n" + Discount.getDiscountList());

            System.out.println(
                "주문이 완료되었습니다. 금액은 W "
                    + basket.getPriceSum(Discount.findDiscountRate(sc.nextInt())) + " 입니다.");
            basket.clearItem();
        }
    }

    // 주문 취소 처리
    public void deleteOrder() {
        System.out.println("주문을 취소하시겠습니까?\n1. 예         2. 아니오");
        if (sc.nextInt() == 1) {
            System.out.println("주문이 정상적으로 취소되었습니다.");
            basket.clearItem();
        }
    }

    // MenuType 별 상세 메뉴 출력
    public void displayMenu(List<MenuItem> list) {
        StringBuilder sb = new StringBuilder();

        int index = 1;
        for(MenuItem item : list) {
            sb.append(index++).append(". ").append(item.toString()).append("\n");
        }
        sb.append("0. 뒤로가기");

        System.out.println(sb);
    }

    // MenuType 및 장바구니 메뉴 출력
    public void displayMenus() {
        int menuAmount = menus.size();
        StringBuilder sb = new StringBuilder();

        sb.append("[ MAIN MENU ]\n");
        for(MenuType m : menus.keySet()) {
            sb.append(m.getIndex() + 1).append(". ").append(m.getTypeName()).append("\n");
        }
        sb.append("0. 종료      | 종료\n");

        if (basket.getItemNumber() > 0) {
            sb.append("\n[ ORDER MENU ]\n").append(menuAmount + 1)
                .append(". Orders\n").append(menuAmount + 2).append(". Cancel\n");
        }
        System.out.print(sb);
    }

    public void start() {
        int category, menuAmount = menus.size();
        while (true) {
            try {
                displayMenus();
                category = sc.nextInt() - 1;

                // 입력 예외처리
                if(basket.getItemNumber() == 0 && category >= menuAmount)
                    throw new IndexOutOfBoundsException("0 ~ " + menuAmount + " 사이의 수를 입력하세요.");
                else if (category < -1 || category >= menuAmount + 2)
                    throw new IndexOutOfBoundsException("0 ~ " + (menuAmount + 2) + " 사이의 수를 입력하세요.");
                else if (category == -1) {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                } else if (category == menuAmount) {
                    confirmOrder();
                    continue;
                } else if (category == menuAmount + 1) {
                    deleteOrder();
                    continue;
                }

                int menu, size;
                while (true) {
                    List<MenuItem> curMenu = menus.get(MenuType.of(category));
                    size = curMenu.size();
                    displayMenu(curMenu);

                    menu = sc.nextInt() - 1;

                    if (menu == -1) break;
                    else if(menu < -1 || menu > size)
                        throw new IndexOutOfBoundsException("0 ~ " + size + " 사이의 수를 입력하세요.");

                    MenuItem menuItem = curMenu.get(menu);
                    System.out.println(menuItem.toString() + "\n위 메뉴를 장바구니에 추가하시겠습니까?\n1.확인     2. 취소");
                    if (sc.nextInt() == 1) basket.addItem(menuItem);
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다.");
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}