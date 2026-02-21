package parser;

public class LexerHelper {
	
	public static int lexemeToInt(String str) {
		try {
			return Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1;
	}
	public static double lexemeToReal(String str) {
		try {
			return Double.parseDouble(str);
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1;
	}
	public static Character lexemeToChar(String text) {
		if (text.length() == 3) {
			return text.charAt(1);
		}
		String content = text.substring(1, text.length() - 1);

		if (content.equals("\\n")) {
			return '\n';
		}
		if (content.equals("\\t")) {
			return '\t';
		}
		if (content.startsWith("\\")) {
			String digits = content.substring(1);
			try {
				int asciiCode = Integer.parseInt(digits);
				return (char) asciiCode;
			} catch (NumberFormatException e) {
				System.err.println("Error parsing ASCII code: " + e);
			}
		}

		return null;
	}




}
