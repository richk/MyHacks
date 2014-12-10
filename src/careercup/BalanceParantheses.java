package careercup;

public class BalanceParantheses {
	
	public static boolean isBalanced(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		} else {
			int leftRem = 0;
			for (int i=0;i<str.length();i++) {
				char c = str.charAt(i);
				if (c == '(') {
					leftRem++;
				} else {
					if (leftRem == 0) {
						return false;
					} else {
						leftRem--;
					}
				}
			}
			if (leftRem > 0) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("IsBalanced: () ? -> " + isBalanced("(())()"));
		test(new Integer(2));
	}
	
	public static void test(int x) {
		System.out.println(x);
	}

}
