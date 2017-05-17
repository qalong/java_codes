package test;

import org.apache.commons.lang3.StringUtils;

public class CKC {

	private static String encode(String string) {
		int total = 0;
		char[] charArray = string.toCharArray();
		int length = charArray.length;
		for (int i = 0; i < length; i++) {
			char c = charArray[i];
			total = total + ((int) c * (length - i));
		}

		total = total % 10;
		return Integer.toString(total);
	}

	public static void main(String[] args) {
		// System.out.println((int)'0');
		// System.out.println((int)'1');
		// System.out.println(encode("000000"));
		// System.out.println(encode("000001"));

		for (int i = 1; i <= 100; i++) {
			String code = StringUtils.leftPad(Integer.toString(i), 6, '0');

			System.out.println("WA" + code + encode("WA" + code));

		}

	}
}
