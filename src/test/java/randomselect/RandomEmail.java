package randomselect;

import java.util.Random;

public class RandomEmail {
	private String baseString = "THISISRANDOMEMAIL"; 

	public String getRandomEmail(int length) {
		RandomInteger rd = new RandomInteger();
		int reLen = length - this.baseString.length() - 10;
		String email = this.baseString;
		if(reLen > 0 ) {
			for(int i=0; i<reLen; i++) {
				int x = rd.getRandomInteger(0, 9);
				String str = Integer.toString(x);
				email = email + str;
			}
		}
		return email + "@gmail.com";
	}
	
	public static void main(String[] args) {
		RandomEmail rd = new RandomEmail();
		RandomInteger rdi = new RandomInteger();
		String e;
		for(int i=0; i<100; i++) {
			int k = rdi.getRandomInteger(100, 300);
			e = rd.getRandomEmail(k);
			System.out.println(e);
		}

	}
}
