package randomhelper;

import java.util.Random;

public class RandomString {

	public String getRandomString(int length) {
		String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
	    String numbers = "0123456789";
	    
	    String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
	    
	    StringBuilder sb = new StringBuilder();
	    
	    Random rd = new Random();
	    
	    for(int i = 0; i < length; i++) {
	        int index = rd.nextInt(alphaNumeric.length());
	        char randomChar = alphaNumeric.charAt(index);
	        sb.append(randomChar);
	      }
	      return sb.toString();
	}
	
	public String getRandomNumericString(int length) {
	    String numbers = "0123456789";

	    StringBuilder sb = new StringBuilder();
	    
	    Random rd = new Random();
	    
	    for(int i = 0; i < length; i++) {
	        int index = rd.nextInt(numbers.length());
	        char randomChar = numbers.charAt(index);
	        sb.append(randomChar);
	      }
	      return sb.toString();
	}

}
