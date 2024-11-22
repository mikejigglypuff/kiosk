package level5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Menu> menus = new ArrayList<>();

        List<MenuItem> burgerItems = new ArrayList<>();
        burgerItems.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgerItems.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        burgerItems.add(new MenuItem("Cheeseburger", 6.9,
                "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        burgerItems.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));
        menus.add(new Menu(burgerItems, "Burgers"));

        List<MenuItem> drinkItems = new ArrayList<>();
        drinkItems.add(new MenuItem("Lemonade", 4.5, "매장에서 직접 만드는 상큼한 레몬에이드"));
        drinkItems.add(new MenuItem("Fifty", 4.0,
                "레몬에이드와 유기농 홍차를 우려낸 아이스 티가 만나 탄생한 쉐이크쉑의 시그니처 음료"));
        drinkItems.add(new MenuItem("Fountain Soda", 2.9,
                "코카콜라, 코카콜라 제로, 스프라이트, 환타 오렌지, 환타 그레이프, 환타 파인애플"));
        drinkItems.add(new MenuItem("Abita Root Beer", 5.0, "청량감 있는 독특한 미국식 무알콜 탄산음료"));
        menus.add(new Menu(drinkItems, "Drinks"));

        List<MenuItem> dessertItems = new ArrayList<>();
        dessertItems.add(new MenuItem("Vanilla Cookie & Cream Shake", 7.8,
                "부드러운 바닐라 커스터드와 바삭한 쿠키 크럼블이 어우러진 홀리데이 쉐이크"));
        dessertItems.add(new MenuItem("Cup & Cones", 5.7,
                "매일 점포에서 신선하게 제조하는 쫀득하고 진한 아이스크림 (바닐라/초콜릿)"));
        dessertItems.add(new MenuItem("Shack Attack", 6.2,
                "진한 초콜릿 커스터드에 퍼지 소스와 세 가지 초콜릿 토핑이 블렌딩된 쉐이크쉑의 대표 콘크리트"));
        dessertItems.add(new MenuItem("Shack-ffogato", 6.2,
                "바닐라 커스터드에 커피 카라멜 소스, 초콜릿 토피, 초콜릿 청크, 코코아 파우더가 어우러진 쉐이크쉑만의 아포가토 콘크리트"));
        menus.add(new Menu(dessertItems, "Desserts"));
        
        Kiosk kiosk = new Kiosk(menus);
        kiosk.start(new Scanner(System.in));
    }
}
