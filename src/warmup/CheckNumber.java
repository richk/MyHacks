package warmup;

// LinkedIn Phone Screen
public class CheckNumber {
	
	public static void main(String[] args) {
		System.out.println("Is number? " + isNumber(""));
		System.out.println("Is number? " + isNumber("0000"));
		System.out.println("Is number? " + isNumber("242"));
		System.out.println("Is number? " + isNumber("242.09"));
		System.out.println("Is number? " + isNumber("242.09.08"));
		System.out.println("Is number? " + isNumber("-3"));
	}
	
	/*
	 * Returns true if the input string is a number and false otherwise
	 "" -> false
	 "0000" -> true
	 "242" -> true
	 "242.09" -> true
	 "242.09.08" -> false
	 "-3"
	 
	 */
	  
	public static boolean isNumber(String toTest)
	{
	    if (toTest.isEmpty()) {
	        return false;
	    } else {
	        int decimalCount = 0;
	        for (int i=0;i<toTest.length();++i) {
	            char currentChar = toTest.charAt(i);
	            if (!isZeroToNine(currentChar) && (currentChar != '.') && (currentChar != '-')) {
	                return false;
	            } else if (currentChar == '.') {
	                ++decimalCount;
	                if (decimalCount >= 2) {
	                    return false;
	                }
	            } else if (currentChar == '-' && (i != 0)){
	                return false;
	            } else {
	            }
	        }
	        return true;
	    }
	}

	public static boolean isZeroToNine(char c) {
	    if (c >= '0' && c <= '9') {
	        return true;
	    } else {
	        return false;
	    }
	}

}
