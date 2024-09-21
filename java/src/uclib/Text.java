package ustdlib;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * A text string consisting of one or more Symbols
 * @see Symbol
 * 
 * @author AndrewClark
 *
 */
public class Text implements CharSequence, Iterable<Symbol> {
	
	private String value;
	private @Nullable Integer count;

	public Text() {
		value="";
		count=0;
	}
	
	public Text(@NonNull String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}

	@Override
	public int length() {
		return value.length();
	}
	
	/**
	 * @return The number of Symbols in the Text
	 */
	public int count() {
		//TODO: Calculate number of symbols in this string
		if (count==null)
			count = value.length();
		
		return count;
	}

	@Override
	public char charAt(int index) {
		return value.charAt(index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return value.subSequence(start, end);
	}
	
	class TextIterator implements Iterator<Symbol> {
		Text text;
		int currentIndex=0;
		
		TextIterator(Text text) {
			this.text = text;
		}
		
		@Override
		public boolean hasNext() {
			return currentIndex < text.count(); 
		}
		@Override
		public Symbol next() {
			//TODO: actual real iterator over symbols
			return new Symbol(text.value.substring(currentIndex++));
		}
	}


	@Override
	public Iterator<Symbol> iterator() {
		return new TextIterator(this);
	}

}
