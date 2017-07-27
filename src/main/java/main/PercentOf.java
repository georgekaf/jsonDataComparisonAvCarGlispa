package main;

public class PercentOf {
    /**
     * 
     * @param number1
     * @param number2
     * @return 
     */
    public static Double TwoNumbers(Double number1, Double number2) {
    	Double increaseDecrease = number1-number2;
    	Double roundedPercentage =  (double) Math.round(((Math.abs(increaseDecrease)/number2)*100));
        
//        System.out.println("increaseDecrease:" +increaseDecrease);
//        System.out.println("roundedPercentage:" +roundedPercentage);
        
        if(increaseDecrease>0 && number2 != 0) {
            System.out.println("An increase of "+roundedPercentage+"%");    
        } else if(increaseDecrease<0 && number2!=0) {
            System.out.println("A decrease of "+roundedPercentage+"%");    
        } else if(increaseDecrease == 0) {
            System.out.println("An increase of 0%");
        }
        
        if (number1==0) {
            System.out.println("You can't work out the percentage change if the original number is 0.");
        }
        
        if(increaseDecrease > 0) {
            System.out.println("This is an increase of "+Math.abs(roundedPercentage));
        } else if(increaseDecrease < 0) {
            System.out.println("This is a decrease of "+Math.abs(roundedPercentage));
        } else if (increaseDecrease == 0) {
            System.out.println("The two numbers are the same");
        }
        
        return roundedPercentage;
    }

}
