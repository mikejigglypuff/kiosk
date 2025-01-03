/*
    1. 사용자 유형별 할인율 적용하기
        1-1. Enum으로 유형별 할인율 정의할 것
        1-2. 주문 시 할인율이 적용되도록 할 것

    2. 람다 & 스트림 활용
        2-1. 카테고리 별 메뉴 출력 시 활용할 것
        2-2. 장바구니 물건 출력 시 활용할 것

 */

package advanced_lv2;

import advanced_lv2.enums.Discount;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final List<Menu> menus;
    private final Basket basket = new Basket();

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    private void displayMenus() {
        int menuAmount = menus.size();
        StringBuilder sb = new StringBuilder();

        sb.append("[ MAIN MENU ]\n");
        for (int i = 0; i < menuAmount; i++) {
            sb.append(i + 1).append(". ").append(menus.get(i).getCategory()).append("\n");
        }
        sb.append("0. 종료      | 종료\n");

        if (basket.getItemNumber() > 0) {
            sb.append("\n[ ORDER MENU ]\n").append(menuAmount + 1)
                .append(". Orders\n").append(menuAmount + 2).append(". Cancel\n");
        }
        System.out.print(sb);
    }

    public void start(Scanner sc) {
        int category, menuAmount = menus.size();
        while (true) {
            try {
                displayMenus();
                category = sc.nextInt() - 1;


                if(basket.getItemNumber() == 0 && category >= menuAmount)
                    throw new IndexOutOfBoundsException("0 ~ " + menuAmount + " 사이의 수를 입력하세요.");
                else if (category < -1 || category >= menuAmount + 2)
                    throw new IndexOutOfBoundsException("0 ~ " + (menuAmount + 2) + " 사이의 수를 입력하세요.");
                else if (category == -1) {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                } else if (category == menuAmount) {
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
                    continue;
                } else if (category == menuAmount + 1) {
                    System.out.println("주문을 취소하시겠습니까?\n1. 예         2. 아니오");
                    if (sc.nextInt() == 1) {
                        System.out.println("주문이 정상적으로 취소되었습니다.");
                        basket.clearItem();
                    }

                    continue;
                }

                int menu;
                while (true) {
                    Menu curMenu = menus.get(category);
                    System.out.print(curMenu.summarizeMenu() + "\n0. 뒤로가기\n");

                    menu = sc.nextInt() - 1;

                    if (menu == -1) break;
                    else if(menu < -1 || menu > curMenu.getMenuAmount())
                        throw new IndexOutOfBoundsException("0 ~ " + curMenu.getMenuAmount() + " 사이의 수를 입력하세요.");

                    MenuItem menuItem = curMenu.getItem(menu);
                    System.out.println(menuItem.toString() + "\n위 메뉴를 장바구니에 추가하시겠습니까?\n1.확인     2. 취소");
                    if (sc.nextInt() == 1) {
                        basket.addItem(menuItem);
                    }
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