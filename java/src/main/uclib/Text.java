package uclib;

import java.util.Iterator;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.eclipse.jdt.annotation.NonNull;

/**
 * A text string consisting of zero or more Symbols
 * @see Symbol
 * 
 * @author AndrewClark
 *
 */
public class Text extends Symbol implements CharSequence, Iterable<Symbol> {
	
	private Long count;
	
	public static final Text NULL_TEXT = new Text();

	public Text() {
		value="";
		count=0L;
	}
	
	public Text(@NonNull String value) {
		this.value = value;
	}

	static public Text of(@NonNull String value) {
		return value.equals("") ? NULL_TEXT : new Text(value);
	}
	
	private Matcher valueMatcher;
	private Matcher getMatcher() {
		if (valueMatcher==null)
			valueMatcher = Pattern.compile("\\X").matcher(value);
		else
			valueMatcher.reset();
		return valueMatcher;
	}
	private Stream<MatchResult> getMatchResultStream() {
		return getMatcher().results();
	}
	
	/**
	 * @return The number of Symbols in the Text
	 */
	public long count() {
		// Cache count
		if (count == null)
			count = getMatchResultStream().count();
		return count;
	}

	/**
	 * @return The Symbol at the specified index
	 */
	public Symbol symbolAt(int index) {
		return new Symbol(getMatchResultStream()
				.skip(index)
				.findFirst()
				.get()
				.group());
	}
	
	private class TextIterator implements Iterator<Symbol> {
		Iterator<MatchResult> valueIterator;
		
		TextIterator(Text text) {
			valueIterator = text.getMatchResultStream().iterator();
		}
		
		@Override
		public boolean hasNext() {
			return valueIterator.hasNext();
		}
		@Override
		public Symbol next() {
			return new Symbol(valueIterator.next().group());
		}
	}


	@Override
	public Iterator<Symbol> iterator() {
		return new TextIterator(this);
	}

}
