package level3;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    public final List<MenuItem> menuItems;

    public Kiosk(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void displayMenu() {
        int menuAmount = menuItems.size();
        StringBuilder sb = new StringBuilder();
        for(int i = 1, count = 0; count < menuAmount; i = (i + 1) % menuAmount, ++count) {
            sb.append(i).append(". ").append(menuItems.get(i).summarizeMenu()).append("\n");
        }
        System.out.print(sb);
    }

    public void start(Scanner sc) {
        displayMenu();

        int menu = -1;
        while(menu != 0) {
            try {
                menu = sc.nextInt();
                System.out.println(menuItems.get(menu).toString());
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다.");
                break;
            } catch(IndexOutOfBoundsException e) {
                System.out.println("0 ~ " + (menuItems.size() - 1) + " 사이의 수를 입력하세요.");
                break;
            }
        }
    }
}