package restautotest;

import java.util.Scanner;

public class MainMenu {

		
	public static void main(String[] args) {	
			System.out.println("==============MENU==============\r\n"
					+ "1) Chọn lựa đường link base URL (Default: https://auctions-app-2.herokuapp.com/api)\r\n"
					+ "2) Chọn lựa API cần kiểm thử tự động\r\n"
					+ "3) Chạy từng unit test case hay tất cả unit test của một API\r\n"
					+ "");
			
			Scanner input = new Scanner(System.in);
			
			//Select
			String select;
			do {
				System.out.println("Nhấn 1 đi: ");
				select = input.nextLine();
			}while(!(select.matches("1")));
			BaseURI base = new BaseURI();
			base.selectURL();
			System.out.println("Base mặc định: " + base.BASEURI);
			
			//Select API
			do {
				System.out.println("Nhấn 2 đi: ");
				select = input.nextLine();
			}while(!(select.matches("2")));
			ListAPI listApi = new ListAPI();
			listApi.display();
			String selectApi = listApi.select();
			
			
			
	}
}