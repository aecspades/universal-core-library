package ustdlib;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * A sub-sequence of a String, that holds only a <code>reference, start and length</code>, not the actual String value itself; 
 * similar to char* in C/C++.
 * <p>
 * Useful for iterating over character (but not {@link Symbol}) values or whenever the cost of creating (large) substrings is too high.
 * </p>
 * 
 * @author AndrewClark
 *
 */
public class SubSequence implements CharSequence, Iterable<Character> {
	
	private @NonNull String referencedValue;
	protected int start;
	protected int length;
	
	public SubSequence(@NonNull String value, int start) {
		this(value, start, value.length()-start);
	}
	public SubSequence(@NonNull String value, int start, int length ) {
		this.referencedValue = value;
		this.start = start;
		this.length = length;
	}

	@Override
	public int length() {
		return length;
	}
	public static int len(@NonNull SubSequence seq) {
		return seq.length();
	}


	@Override
	public char charAt(int index) {
		return referencedValue.charAt(start+index);
	}
	
	protected @NonNull SubSequence newCharSequence(@NonNull String value, int start, int length) {
		return new SubSequence(value, this.start + start, length);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		if (start==0 && end==length)
			return this;
		
		return newCharSequence(referencedValue, start, end-start);
	}
	
	protected @NonNullByDefault String toStringValue=null;
	@SuppressWarnings("null")
	@Override
	public @NonNull String toString() {
		if (toStringValue==null)
			toStringValue = referencedValue.substring(start, start+length);
		
		return toStringValue;
	}
	
	class SubSequenceIterator implements Iterator<Character> {
		SubSequence subSequence;
		int currentIndex=0;
		
		SubSequenceIterator(SubSequence subSequence) {
			this.subSequence = subSequence;
		}
		
		@Override
		public boolean hasNext() {
			return currentIndex < subSequence.length;
		}
		@Override
		public Character next() {
			return subSequence.charAt(currentIndex++);
		}
	}

	@Override
	public Iterator<Character> iterator() {
        return new SubSequenceIterator(this);
    }


}
