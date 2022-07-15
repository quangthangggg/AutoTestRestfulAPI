package randomhelper;

import java.util.Random;

public class RandomInteger {

	public int getRandomInteger(int min, int max) {
		Random rd = new Random();
		return rd.nextInt(max-min+1) + min;
	}

}