package ustdlib;

import org.eclipse.jdt.annotation.NonNull;

/**
 * A reverse SubSequence, that iterates "backwards" over a range of characters.
 * <pre>
 * ReverseSubSequence rseq = new ReverseSubSequence("abcdef");
 * String s = rseq.toString(); // "fedcba" string reversed
 * char c = rseq.charAt(1); 'e' 1 character from end of string
 * </pre>
 * 
 * @see SubSequence
 * 
 * 
 * @author AndrewClark
 *
 */
public class ReverseSubSequence extends SubSequence {

	public ReverseSubSequence(@NonNull String value, int start) {
		super(value, start);
	}
	public ReverseSubSequence(@NonNull String value, int start, int length) {
		super(value, start, length);
	}
	
	@Override
	protected @NonNull SubSequence newCharSequence(@NonNull String value, int start, int length) {
		int end = this.start + this.length - start;
		return new ReverseSubSequence(value, end-length, length);
	}
	
	@Override
	public char charAt(int index) {
		return super.charAt(length-index-1);
	}

	@Override
	public @NonNull String toString() {
		if (toStringValue==null)
			toStringValue = new StringBuilder(super.toString()).reverse().toString();
		
		return toStringValue;
	}
}
