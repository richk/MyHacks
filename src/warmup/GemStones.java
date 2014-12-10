package warmup;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class GemStones {
	
	private static final int MASK = 0x1F;

    public static void main(String[] args) {
        try {
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		    int numElements = Integer.parseInt(br.readLine());
		    
		    System.out.println("Number of elements "+numElements);
            
            int [] elemMap = new int [numElements];
            
            int i = 0;
 
		    while(i < numElements) {
                String input = br.readLine();
                System.out.println("Input at line "+i+"="+input);
                for (int j=0;j<input.length();++j) {
                	char elem = input.charAt(j);
                	System.out.println("Element at "+j+"="+elem);
                	int elemIndex = elem - 'a';
                	System.out.println("Element index at "+j+"="+elemIndex);
                	System.out.println("Element array "+j+"="+elemMap[i]);
                	elemMap[i] = set(elemMap[i], elemIndex);
                	System.out.println("Element array after calling set at "+j+"="+elemMap[i]);
                }
                System.out.println("Final elemMap at "+i+"="+elemMap[i]);
                ++i;
		    }
		    
		    int allElem = 0;
		    if (numElements > 1) {
		        allElem = elemMap[0];
		        System.out.println("lastElemSum at 0="+allElem);
		        for (int j=1;j<elemMap.length;++j) {
		        	allElem = allElem & elemMap[j];
		        	System.out.println("lastElemSum at "+j+"="+allElem);
		        }
		    } else {
		    	allElem = elemMap[0];
		    }
		    
		    System.out.println("allElem=" + allElem);
		    
		    int numGemElems = 0;
		    for (int j=0;j<26;++j) {
		    	int tested = test(allElem, j);
		    	System.out.println("Tested at index "+j+"="+tested);
		    	if (tested >= 1) {
		    		++numGemElems;
		    	}
		    }
		    
		    System.out.println("Number of gem elements:" + numGemElems);
		    
		    
	    } catch(IOException io){
		    io.printStackTrace();
 	    }
    }
    
    public static int set(int number, int i) {
        number = number | (1<<(i&MASK));
        return number;
    }
    
    public static int test(int number, int i) {
    	return (number & (1<<(i&MASK)));
    }
}    
