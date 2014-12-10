package careercup;

public class StringFun {
	
	static boolean isAnagram(String word1, String word2) {
	    if (word1 == null || word2 == null || word1.isEmpty() || word2.isEmpty()) {
	        return false;
	    } else {
	         if (word1.length() != word2.length()) {
	             return false;
	         } else {
	             int[] charcount = new int[256];
	             for (int i=0;i<word1.length();i++) {
	                 char c = word1.charAt(i);
	                 charcount[(int)c]++;
	             }
	             for (int i=0;i<word2.length();i++) {
	                 char c = word2.charAt(i);
	                 charcount[(int)c]--;
	                 if (charcount[(int)c] < 0) {
	                     return false;
	                 }    
	             }  
	             return true;
	         }
	    }
	}

	// word == "a"
	static boolean isPalindrome(String word) {
	    if (word == null || word.isEmpty()) {
	        return false;
	    } else {
	         int lastIndex = word.length()-1;
	         for (int i=0;i < word.length()/2;i++) {
	             if (word.charAt(i) != word.charAt(lastIndex-i)) {
	                 return false;
	             }
	         } 
	         return true;  
	    }
	}
	
	public static void main(String[] args) {
	    System.out.println("Is anagram:" + isAnagram("iPhone 6", "P6hone i"));
	    System.out.println("Is Palindrome:" + isPalindrome("12321"));
	    System.out.println(7/2);
	}

}
