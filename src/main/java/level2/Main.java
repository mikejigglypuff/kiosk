package level2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("종료", "종료"));
        menuItems.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        int menuAmount = menuItems.size();
        StringBuilder sb = new StringBuilder();
        for(int i = 1, count = 0; count < menuAmount; i = (i + 1) % menuAmount, ++count) {
            sb.append(i).append(". ").append(menuItems.get(i).summarizeMenu()).append("\n");
        }
        System.out.print(sb);

        Scanner sc = new Scanner(System.in);

        int menu = -1;
        while(menu != 0) {
            try {
                menu = sc.nextInt();
                System.out.println(menuItems.get(menu).toString());
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다.");
                break;
            } catch(IndexOutOfBoundsException e) {
                System.out.println("0 ~ " + (menuAmount - 1) + " 사이의 수를 입력하세요.");
                break;
            }
        }
    }
}
