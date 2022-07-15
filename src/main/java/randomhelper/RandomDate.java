package randomhelper;

public class RandomDate {

    public String getRandomDate() {
    	RandomInteger rd = new RandomInteger();
        int day = rd.getRandomInteger(1, 25);
        int month = rd.getRandomInteger(1, 12);
        int year = rd.getRandomInteger(2023, 3000);
        String res = Integer.toString(year) + "/" + 
        				((month<10)?("0"+Integer.toString(month)):Integer.toString(month)) + "/" + 
        				((day<10)?("0"+Integer.toString(day)):Integer.toString(day));
        return res;
    }
    
}
