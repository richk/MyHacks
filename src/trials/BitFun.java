package trials;

public class BitFun {
	
	public static void main(String[] args) {
		int num = 132;
		byte[] buffer = new byte[4];
		buffer[0] = (byte) (num >> 24);
		buffer[1] = (byte) (num >> 16);
		buffer[2] = (byte) (num >> 8);
		buffer[3] = (byte) (num);
		System.out.println("MSB=" + (byte)num);
		int newNum = ((buffer[0] & 0xff) << 24) + ((buffer[1] & 0xff) << 16) + ((buffer[2] & 0xff) << 8) + (buffer[3] & 0xff);
		System.out.println("New num=" + newNum);
	}

}
