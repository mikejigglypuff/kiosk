package level5;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final List<Menu> menus;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    private void displayMenus() {
        int menuAmount = menus.size();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < menuAmount; i++) {
            sb.append(i + 1).append(". ").append(menus.get(i).getCategory()).append("\n");
        }
        sb.append("0. 종료      | 종료\n");
        System.out.print(sb);
    }

    public void start(Scanner sc) {
        int category;
        while(true) {
            try {
                displayMenus();
                category = sc.nextInt() - 1;

                if(category < -1 || category >= menus.size())
                    throw new IndexOutOfBoundsException("0 ~ " + (menus.size() - 1) + " 사이의 수를 입력하세요.");
                else if(category == -1) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                }

                int menu;
                while(true) {
                    System.out.print(menus.get(category).summarizeMenu());

                    menu = sc.nextInt() - 1;
                    if(menu == -1) break;

                    System.out.println(menus.get(category).getItemDescription(menu));
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다.");
                break;
            } catch(IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}