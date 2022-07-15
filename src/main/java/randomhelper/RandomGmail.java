package randomhelper;

public class RandomGmail {
	private String baseString = "THISISRANDOMGMAIL"; 

	public String getRandomGmail(int length) {
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

}
