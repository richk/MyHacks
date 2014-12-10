package warmup;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Anagram {

    public static void main(String[] args) {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	try {
			String s1 = br.readLine();
			String s2 = br.readLine();
			int count = getMinDelAnagram(s1, s2);
	    	System.out.println(count);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static int getMinDelAnagram(String s1, String s2) {
        int count = 0;
        int [] a = new int[26];
        int [] b = new int[26];
        if (s1 == null && s2 == null) {
        	return 0;
        } else if (s1 == null && s2 != null) {
        	return s2.length();
        } else if (s1 != null && s2 == null) { 
        	return s1.length();
        } else {
        	for(int i=0;i<s1.length();++i) {
        		a[(int)(s1.charAt(i)-'a')]++;;
        	}
        	for(int i=0;i<s2.length();++i) {
        		b[(int)(s2.charAt(i)-'a')]++;;
        	}
        	for(int i=0;i<26;++i) {
        		count += a[i] + b[i] - 2*(Math.min(a[i],b[i]));
        	}
        }
        return count;
    }
}