package mainmenu;

import menuhelper.*;

public class MainMenu {
		public static void main(String[] args) {
			MenuHelper menu = new MenuHelper();
			menu.printMenu();
			String select = menu.select();
			if(select.equals("2")){
				menu.printCurrentListAPI();
				String selectApi = menu.select();
				menu.selectApi(selectApi);
			}
		}
}
