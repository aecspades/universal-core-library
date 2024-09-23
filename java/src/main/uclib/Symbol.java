package uclib;

import java.security.InvalidParameterException;
import java.util.regex.Pattern;

import org.eclipse.jdt.annotation.NonNull;

/**
 * A Symbol represents exactly one Unicode-defined <a href="https://unicode.org/reports/tr29/">"user-perceived character"</a> aka "grapheme cluster".
 * 
 * @see Text
 * 
 * @author AndrewClark
 *
 */
public class Symbol implements CharSequence, Comparable<CharSequence> {
	
	@NonNull protected String value;
	
	public static final Symbol NULL_SYMBOL = new Symbol();

	public Symbol() {
		value="\0";
	}
	
	/**
	 * @param value A single "user-perceived character" (grapheme cluster)
	 * @throws InvalidParameterException if <code>value</code> is more than one grapheme cluster
	 * @since Seems to <b>require</b> JDK 20 to work correctly
	 */
	Symbol(@NonNull String value) {
		if (!Pattern.matches("^\\X$", value)) 
			throw new InvalidParameterException(value);
		this.value = value;
	}

	static public Symbol of(@NonNull String value) {
		return value.equals("\0") ? NULL_SYMBOL : new Symbol(value);
	}
	
	@Override
	public String toString() {
		return value;
	}

	@Override
	public int length() {
		return value.length();
	}

	@Override
	public char charAt(int index) {
		return value.charAt(index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return value.subSequence(start, end);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof CharSequence)
			return compareTo((CharSequence)o) == 0;
		
		return false;
	}
	
	@Override
	public int compareTo(CharSequence o) {
		return value.compareTo(o.toString());
	}

}
