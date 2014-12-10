package careercup;

public class FindChange {
	
	public static void findChange1(int n, int denom, int[] change) {
		int next_denom = 0;
		next_denom = getNextDenom(denom);
		int denomIndex = getDenomIndex(denom);
		if (denomIndex < 0) {
			System.out.println("Unexpected error");
			return;
		}
		int sum = n;
		for(int i=0;sum>0;i++) {
			sum = (n-(i*denom));
			change[denomIndex] = i;
			if (sum == 0) {
				System.out.println("Found Sum. Change:" + print(change));
				change[denomIndex] = 0;
			} else if (sum > 0) {
				if (next_denom > 0) {
			        findChange1(sum, next_denom, change);
				}
			}
		}
	}


	private static String print(int[] change) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int i=0;i<change.length;i++) {
			if(i>0) {
				sb.append(",");
			}
			sb.append(change[i]);
		}
		sb.append("}");
		return sb.toString();
	}

	private static int getDenomIndex(int denom) {
		switch(denom) {
		case 25:
			return 0;
		case 10:
			return 1;
		case 5:
			return 2;
		case 1:
			return 3;
		}
		return -1;
	}

	private static int getNextDenom(int denom) {
		switch(denom) {
		case 25:
			return 10;
		case 10:
			return 5;
		case 5:
			return 1;
		case 1:
			return -1;
		}
		return -1;
	}

	private static void printChange(int[] change) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Change numbers");
		for (int i=0;i<change.length;i++) {
			System.out.println();
			System.out.print(change[i]);
			switch(i) {
				case 0:
					System.out.print(" Quarters");
					break;
				case 1:
					System.out.print(" Dimes");
					break;
				case 2:
					System.out.print(" Nickels");
					break;
				case 3:
					System.out.print(" Pennies");
					break;
			    default:
			    	System.out.print("Error");
						
			}
		}
		
	}

	public static void main(String[] args) {
		int change[] = {0,0,0,0};
	    findChange1(100, 25, change);	
	}
}
