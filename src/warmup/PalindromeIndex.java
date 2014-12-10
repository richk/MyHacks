package warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PalindromeIndex {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	try {
			String s1 = br.readLine();
			int num = Integer.valueOf(s1);
			String [] inputs = new String[num];
			int i = 0;
			while (i < num) {
				inputs[i] = br.readLine();
				++i;
			}
			for (int j=0;j<inputs.length;++j) {
				System.out.println(getPalindromeIndex(inputs[j]));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int getPalindromeIndex(String str) {
		int leftIndex = -1;
		int rightIndex = -1;
		int i=0;
	    for (;i<str.length()/2;++i) {
	    	System.out.println("i=" + str.charAt(i));
	    	System.out.println("(str.length()-i-1)=" + str.charAt(str.length()-i-1));
	    	if (str.charAt(i) != str.charAt(str.length()-i-1)) {
	    		leftIndex = i;
	    	    rightIndex = str.length()-i-1;
	    	    ++i;
	    	    break;
	    	}
	    }
	    System.out.println("Outside loop, i=" + str.charAt(i));
//	    System.out.println("Outside loop,leftIndex=" + (i>0?str.charAt(leftIndex):-1));
//    	System.out.println("Outside loop,rightIndex=" + (i>0?str.charAt(rightIndex):-1));
	    if (leftIndex == -1 && rightIndex == -1) {
	    	return leftIndex;
	    } else {
	    	if (str.charAt(i) == str.charAt(leftIndex)) {
	    		return rightIndex;
	    	} else {
	    		return leftIndex;
	    	}	
	    }
	}
}
