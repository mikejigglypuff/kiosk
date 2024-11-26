package advanced_final;

import advanced_final.enums.SellingMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Kiosk kiosk = new Kiosk(SellingMenu.getMenuList());
        kiosk.start(new Scanner(System.in));
    }
}
