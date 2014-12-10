package warmup;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Pangram {

    public static void main(String[] args) {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	try {
			String s1 = br.readLine();
			if (isPangram(s1)) {
				System.out.println("pangram");
			} else {
				System.out.println("not pangram");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static boolean isPangram(String s) {
    	s = s.toLowerCase();
        boolean[] alphaChars = new boolean[26];
        for (int i=0;i<alphaChars.length;++i) {
        	alphaChars[i] = false;
        }
        for (int i=0;i<s.length();++i) {
        	char c = s.charAt(i);
        	if (isAlpha(c)) {
        		int index = c - 'a';
        		alphaChars[index] = true;
        		if (getCurrentCount(alphaChars) == 26) {
        			return true;
        		}
        	}
        }
        if (getCurrentCount(alphaChars) < 26) {
    		return false;
    	} else {
    		return true;
    	}
    }
    
    private static boolean isAlpha(char c) {
    	int charCode = (int) c;
		if (charCode >= 97 && charCode <=122) {
		    return true;
		} else {
			return false;
		}
	}

	public static int getCurrentCount(boolean[] chars) {
    	int currentSum = 0;
    	for (int i=0;i<chars.length;++i) {
            if(chars[i]) {
            	++currentSum;
            }
    	}
    	return currentSum;
    }
}