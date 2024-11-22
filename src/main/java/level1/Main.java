package level1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] menuArr = new String[] {
            "프로그램을 종료합니다.",
            "ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거",
            "SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거",
            "Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거",
            "Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거"
        };

        System.out.println(
            """
                [ SHAKESHACK MENU ]\n
                1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거\n
                2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거\n
                3. Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거\n
                4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거\n
                0. 종료      | 종료
            """
        );
        int menu = -1;

        while(menu != 0) {
            try {
                menu = sc.nextInt();
                System.out.println(menuArr[menu]);
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다.");
                break;
            } catch(IndexOutOfBoundsException e) {
                System.out.println("0 ~ 4 사이의 수를 입력하세요.");
                break;
            }
        }
    }
}
