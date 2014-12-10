package warmup;

public class TelephoneWords {
	
	static String[][] charKey = {
		{},
		{"A","B","C"},
		{"D","E","F"},
		{"G","H","I"},
		{"J","K","L"},
		{"M","N","O"},
		{"P","R","S"},
		{"T","U","V"},
		{"W","X","Y"}
	};
	
	public static void main(String[] args) {
		System.out.println("CharKey for 1 -->" + getCharKey(1, 1));
		System.out.println("CharKey for 2 -->" + getCharKey(2, 1));
		System.out.println("CharKey for 2 -->" + getCharKey(2, 2));
		System.out.println("CharKey for 2 -->" + getCharKey(2, 3));
		System.out.println("CharKey for 3 -->" + getCharKey(3, 1));
		System.out.println("CharKey for 3 -->" + getCharKey(3, 2));
		System.out.println("CharKey for 3 -->" + getCharKey(3, 3));
		printTelephoneWords("8662665","");
	}
	
	public static void printTelephoneWords(String str, String word) {
		if (str.isEmpty()) {
			System.out.println("");	
		} else {
			int currentNum = Integer.valueOf(str.substring(0,1));
			if (str.length() == 1) {
				for (int i=1;i<=3;++i) {
				    System.out.println("Word " + (word + getCharKey(currentNum, i)));
				}
				return;
			} else {
				for (int i=1;i<=3;++i) {
				    String newWord = word + getCharKey(currentNum, i);
				    String newStr = str.substring(1);
				    printTelephoneWords(newStr, newWord);
				}
			}
		}
	}
	
	public static String getCharKey(int telephoneKey, int place) {
		if (telephoneKey > charKey.length) {
			return "";
		} else {
			int key = telephoneKey - 1;
			String[] chars = charKey[key];
			if (chars.length == 0 || (chars.length < place)) {
                return "";
			} else {
				return charKey[key][place-1];
			}
		}
	}

}
