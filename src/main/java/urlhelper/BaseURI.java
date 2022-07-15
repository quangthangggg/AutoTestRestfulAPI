package urlhelper;

import java.util.Scanner;

public class BaseURI {
	private String BASEURI = "https://auctions-app-2.herokuapp.com/";
	
	public void selectURL() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập một base URI cần kiểm thử: ");
		String baseURI = sc.nextLine();
		if(!baseURI.isEmpty()) {
			this.BASEURI = baseURI;
		}
	}
	
	public String getBaseURI() {
		return this.BASEURI;
	}
}
