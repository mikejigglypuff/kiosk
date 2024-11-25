/*
    1. 장바구니 구현
        1-1. 장바구니는 메뉴 아이템 리스트, 수량을 가짐
        1-2. 장바구니에 메뉴를 담을 수 있어야 하며,
            순회를 통해 메뉴들에 대한 설명, 가격 합계를 구할 수 있어야 함
        1-3. 키오스크는 메인 메뉴에서 메뉴들 리스트 이외에 Orders, Cancel 메뉴를 띄워야 함
            1-3-1. 장바구니가 비어있지 않을 때만 띄울 것
            1-3-2. Orders 선택 시 아래와 같이 주문하시겠습니까? 와 함께 장바구니 목록 출력하기
                1-3-2-1. 1. 주문      2. 메뉴판  출력 후
            1-3-3. Cancel 선택 시 주문을 취소하시겠습니까? 라는 문구 띄울 것
                1-3-3-1. 이후 1. 예        2. 아니오 를 띄우고 입력받기
                1-3-3-2. 예 선택 시 장바구니 리스트 초기화할 것
                1-3-3-3. 아니오 선택 시 메뉴로 돌아갈 것
        1-4. 상세 메뉴를 선택할 시 해당 메뉴를 장바구니에 추가할 것인지를 물어볼 것
            1-4-1. 1. 확인        2. 취소       출력할 것

 */

package advanced_lv1;

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
                        System.out.println("주문이 완료되었습니다. 금액은 W " + basket.getPriceSum() + " 입니다.");
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
                    System.out.print(curMenu.summarizeMenu());

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