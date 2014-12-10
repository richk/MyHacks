package trials;

public class Substring {
	
	static boolean isSubString(String orig, String tar) {
	    if (orig == null || tar == null) {
	        return false;
	    }
	    if (orig.isEmpty() && tar.isEmpty()) {
	        return true;
	    }
	    if (orig.isEmpty() || tar.isEmpty()) {
	        return false;
	    }
	    if (tar.length() > orig.length()) {
	        return false;
	    } 
	    int origIndex = 0;
	    while (origIndex < (orig.length()-tar.length()+1)) {
	        char t = tar.charAt(0);
	        char o = orig.charAt(origIndex);
	        if (t==o) {
	            boolean isSubstring = checkSubstring(orig, tar, origIndex+1);
	            if (isSubstring) {
	                return isSubstring;
	            } else {
	                ++origIndex;
	            }
	        } else {
	            ++origIndex;    
	        }
	    }
	    return false;
	}

	static boolean checkSubstring(String orig, String tar, int origIndex) {
	    int remOrigLength = orig.length()-origIndex+1;
	    if (tar.length() > remOrigLength) {
	        return false;
	    }
	    int i = 1;
	    while (i < tar.length()) {
	        if (tar.charAt(i) != orig.charAt(origIndex)) {
	            return false;
	        } else {
	            ++origIndex;
	            ++i;
	        }           
	    }
	    return true;
	}
	
	public static void main(String[] args) {
		System.out.println("IsSubstring:" + isSubString("char", "ar"));
	}

}
