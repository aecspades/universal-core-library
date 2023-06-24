package ustdlib;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Utility class for Str (String) handling
 * 
 * @author AndrewClark
 *
 */
public class Str {

	/**
	 * {@link String#trim()}
	 * @param s
	 * @return
	 */
	@SuppressWarnings("null")
	public static @NonNull String trim(@NonNull String s) {
		return s.trim();
	}
	
	/**
	 * Trim whitespace from leading edge of String
	 */
	@SuppressWarnings("null")
	public static @NonNull String ltrim(@NonNull String s) {
		//return s.toString().stripLeading();
		int slength = s.length();
		for (int i=0; i<slength; i++) {
			if (!Character.isWhitespace(s.charAt(i)))
				return s.substring(i);
		}
		return s;
	}
	/**
	 * Trim whitespace from trailing edge of String
	 */
	@SuppressWarnings("null")
	public static @NonNull String rtrim(@NonNull String s) {
		//return s.toString().stripTrailing();
		for (int i=s.length()-1; i>=0; i--) {
			if (!Character.isWhitespace(s.charAt(i)))
				return s.substring(0, i+1);
		}
		return s;
	}
	
	/**
	 * Concatenate Strings
	 * @see StringBuilder#append(String)
	 */
	public static @NonNull String cat(@NonNull String str1, @NonNull String str2) {
		return str1 + str2;
	}

	
	/**
	 * Compare Strings
	 * @see String#compareTo(String)
	 */
	public static int cmp(@NonNull String str1, @NonNull String str2) {
		return str1.compareTo(str2);
	}
	
	/**
	 * @return Length of <code>str</code>
	 */
	public static int len(@NonNull String str) {
		return str.length();
	}

	
	/**
	 * @return The substring of <code>str</code> beginning at the first occurrence of any of the <code>searchCharacters</code>, or "" if not found
	 * @see Str#indexOfAny(String, String)
	 */
	@SuppressWarnings("null")
	public static @NonNull String substringAtAny(@NonNull final String str, @NonNull String searchCharacters) {
		int x = indexOfAny(str, searchCharacters);
		return x>=0 ? str.substring(x) : "";
	}

	/**
	 * @return The substring of <code>str</code> beginning at the first occurrence of <code>searchValue</code>, or "" if not found
	 * @see String#indexOf(String) 
	 */
	@SuppressWarnings("null")
	public static @NonNull String substringAt(@NonNull final String str, @NonNull String searchValue) {
		int x = str.indexOf(searchValue);
		return x>=0 ? str.substring(x) : "";
	}
	
	/**
	 * @return The first index (or -1 if not found) of any of the <code>searchCharacters</code> in <code>str</code>.
	 */
	public static int indexOfAny(@NonNull final String str, @NonNull String searchCharacters) {
		for (int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if (searchCharacters.indexOf(c) >= 0)
				return i;
		}
		return -1;
	}


	/**
	 * @return The first index of <str> that does <b>not<b> match any of the <code>searchCharacters</code>, or -1 if all characters match.
	 */
	public static int indexOfNone(@NonNull final String str, @NonNull String searchCharacters) {
		for (int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if (searchCharacters.indexOf(c) < 0)
				return i;
		}
		return -1;
	}
	
	/**
	 * @return The substring of <code>str</code> beginning at the first occurrence of <b>none</b> of the <code>searchCharacters</code>, or "" if all characters found
	 * @see Str#indexOfAny(String, String)
	 */
	@SuppressWarnings("null")
	public static @NonNull String substringAtNone(@NonNull final String str, @NonNull String searchCharacters) {
		int x = indexOfNone(str, searchCharacters);
		return x>=0 ? str.substring(x) : "";
	}
	
	/**
	 * @return Last index of <code>c</code> in <code>str</code>
	 * @see String#lastIndexOf(int)
	 */
	public static int lastIndexOf(@NonNull String str, int c) {
		return str.lastIndexOf(c);
	}

}
