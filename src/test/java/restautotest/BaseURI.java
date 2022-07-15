package restautotest;

import java.util.Scanner;

public class BaseURI {
	public static String BASEURI = "https://auctions-app-2.herokuapp.com/";
	
	public void selectURL() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập một base URI cần kiểm thử: ");
		String baseURI = sc.nextLine();
		if(!baseURI.isEmpty()) {
			this.BASEURI = baseURI;
		}
	}
}
