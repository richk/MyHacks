package warmup;

// LinkedIn Phone Screen/Google Onsite
public class BinarySearchRotated {
	
	public static void main(String[] args) {
		float[] list = {5,6,7,0,1,2,3,4};
		System.out.println("Is 8 in the list? " + isInList(8, list));
	}
	
	public static boolean isInList(float targetValue, float[] list)
	{
	    return findTarget(targetValue, list, 0, list.length-1); 
	}

	public static boolean findTarget(float targetValue, float[] list, int start, int end) {
	    if (start < end) {
	        int mid = (start + end)/2;
	        if (list[mid] == targetValue) {
	            return true;
	        } else {
	            if ((list[start] < list[mid])){
	                if (targetValue >= list[start] && targetValue < list[mid]) {
	                    return findTarget(targetValue, list, start, mid-1);	
	                } else {
	                	return findTarget(targetValue, list, mid+1, end);
	                }
	            } else {
	            	if (targetValue > list[mid] && targetValue <= list[end]) {
	            		return findTarget(targetValue, list, mid+1, end);
	            	} else {
	            		return findTarget(targetValue, list, start, mid-1);
	            	}
	                
	            }
	        }
	    } else {
	    	return false;
	    }
	}

}
