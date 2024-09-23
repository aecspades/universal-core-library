package uclib;

import org.eclipse.jdt.annotation.NonNull;

/**
 * A Symbol represents a single Unicode-defined <a href="https://unicode.org/reports/tr29/">user-perceived character</a> or grapheme cluster
 * 
 * @see Text
 * 
 * @author AndrewClark
 *
 */
public class Symbol implements CharSequence {
	
	@NonNull String value;

	public Symbol() {
		value="";
	}
	
	Symbol(@NonNull String value) {
		this.value = value;
	}

	static public Symbol valueOf(@NonNull String value) {
		//TODO: throw IllegalArgument exception if not 1 symbol value
		return new Symbol(value);
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

}
