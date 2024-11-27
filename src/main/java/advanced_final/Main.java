package advanced_final;

import advanced_final.enums.Menu;

public class Main {
    public static void main(String[] args) {
        Kiosk kiosk = new Kiosk(Menu.getMenuList());
        kiosk.start();
    }
}
