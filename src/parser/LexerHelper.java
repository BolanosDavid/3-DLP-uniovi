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
		// text viene con las comillas: 'a', '\n', '\126', etc.

		// Caso 1: longitud 3 → carácter normal 'a', 'Z', ' ', etc.
		if (text.length() == 3) {
			return text.charAt(1);  // Carácter entre las comillas
		}

		// Caso 2 y 3: longitud > 3 → secuencias de escape
		// Extraer contenido sin las comillas
		String content = text.substring(1, text.length() - 1);  // Quita ' inicial y final

		// Caso 2: caracteres especiales \n y \t
		if (content.equals("\\n")) {
			return '\n';
		}
		if (content.equals("\\t")) {
			return '\t';
		}

		// Caso 3: código ASCII \###
		// content = "\\126" → extraer "126"
		if (content.startsWith("\\")) {
			String digits = content.substring(1);  // Quita la barra invertida
			try {
				int asciiCode = Integer.parseInt(digits);
				return (char) asciiCode;  // Convierte código ASCII a char
			} catch (NumberFormatException e) {
				System.err.println("Error parsing ASCII code: " + e);
			}
		}

		return null;  // Error: formato no reconocido
	}




}
