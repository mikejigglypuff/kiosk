package advanced_final.enums;

import advanced_final.MenuItem;

import java.util.*;

public enum Menu {
    SHACKBURGER(MenuType.BURGERS, "ShackBurger", 6.9,
        "토마토, 양상추, 쉑소스가 토핑된 치즈버거", true),
    SMOKEBURGER(MenuType.BURGERS, "SmokeShack", 8.9,
        "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거", false),
    CHEESEBURGER(MenuType.BURGERS, "Cheeseburger", 6.9,
        "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거", false),
    HAMBURGER(MenuType.BURGERS,"Hamburger", 5.4,
        "비프패티를 기반으로 야채가 들어간 기본버거", false),
    LEMONADE(MenuType.DRINK, "Lemonade", 4.5,
        "매장에서 직접 만드는 상큼한 레몬에이드", false),
    FIFTY(MenuType.DRINK, "Fifty", 4.0,
        "레몬에이드와 유기농 홍차를 우려낸 아이스 티가 만나 탄생한 쉐이크쉑의 시그니처 음료", false),
    SODA(MenuType.DRINK,"Fountain Soda", 2.9,
        "코카콜라, 코카콜라 제로, 스프라이트, 환타 오렌지, 환타 그레이프, 환타 파인애플", false),
    ROOTBEER(MenuType.DRINK, "Abita Root Beer", 5.0,
        "청량감 있는 독특한 미국식 무알콜 탄산음료", false),
    COOKIECREAMSHAKE(MenuType.DESSERT, "Vanilla Cookie & Cream Shake", 7.8,
        "부드러운 바닐라 커스터드와 바삭한 쿠키 크럼블이 어우러진 홀리데이 쉐이크", false),
    CUPCONES(MenuType.DESSERT, "Cup & Cones", 5.7,
        "매일 점포에서 신선하게 제조하는 쫀득하고 진한 아이스크림 (바닐라/초콜릿)", false),
    SHACKATTACK(MenuType.DESSERT, "Shack Attack", 6.2,
        "진한 초콜릿 커스터드에 퍼지 소스와 세 가지 초콜릿 토핑이 블렌딩된 쉐이크쉑의 대표 콘크리트", false),
    SHACKFFOGATO(MenuType.DESSERT, "Shack-ffogato", 6.2,
        "바닐라 커스터드에 커피 카라멜 소스, 초콜릿 토피, 초콜릿 청크, 코코아 파우더가 어우러진 쉐이크쉑만의 아포가토 콘크리트",
        false);

    private final MenuType type;
    private final MenuItem menuItem;

    Menu(MenuType type, String menuName, double price, String description, boolean recommandation) {
        this.type = type;
        this.menuItem = new MenuItem(menuName, price, description, recommandation);
    }

    // Getter
    public MenuType getType() {
        return type;
    }
    public MenuItem getMenuItem() {
        return menuItem;
    }

    // 메뉴 리스트들을 Kiosk 에서 사용하기 좋은 형태로 반환
    public static Map<MenuType, List<MenuItem>> getMenuList() {
        Map<MenuType, List<MenuItem>> categoryMap = new LinkedHashMap<>();
        for(MenuType m : MenuType.values()) {
            List<MenuItem> list = new ArrayList<>();
            categoryMap.put(m, list);
        }

        for(Menu s : Menu.values()) {
            categoryMap.get(s.getType()).add(s.getMenuItem());
        }

        return categoryMap;
    }
}
