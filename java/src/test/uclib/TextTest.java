package uclib;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class TextTest {

	@Test
	public void testText() {
		Text text = new Text();
		assertTrue(text.value == "");
	}

	static final String GOOD_TEXT = "g̈óód Day😊";
	@SuppressWarnings("unused")
	@Test
	public void testTextString() {
		Text text = new Text("A string");
		text = new Text(GOOD_TEXT);
		text = new Text("Omg🤦🏼‍♂️!");
	}

	@Test
	public void testOf() {
		Text text = Text.of("");
		assertTrue(text == Text.NULL_TEXT);
		text = Text.of("A string");
		text = Text.of(GOOD_TEXT);
		text = Text.of("Omg🤦🏼‍♂️!");
	}

	
	@Test
	public void testToString() {
		Text text = new Text("A string");
		assertTrue(text.toString().equals("A string"));
		text = Text.of(GOOD_TEXT);
		assertTrue(text.toString().equals(GOOD_TEXT));
		text = Text.of("Omg🤦🏼‍♂️!");
		assertTrue(text.toString().equals("Omg🤦🏼‍♂️!"));
	}

	@Test
	public void testLength() {
		Text text = new Text("A string");
		assertTrue(text.length() == 8);
		text = Text.of(GOOD_TEXT);
		assertTrue(text.length() == 11);
		text = Text.of("Omg🤦🏼‍♂️!");
		assertTrue(text.length() == 11);
	}

	@Test
	public void testCount() {
		Text text = new Text("A string");
		assertTrue(text.count() == 8);
		text = Text.of(GOOD_TEXT);
		assertTrue(text.count() == 9);
		text = Text.of("Omg🤦🏼‍♂️!");
		assertTrue(text.count() == 5);
	}

	@Test
	public void testSymbolAt() {
		Text textGood = new Text(GOOD_TEXT); 
		assertTrue(textGood.symbolAt(0).value.equals("g̈"));
		assertTrue(textGood.symbolAt(3).value.equals("d"));
		assertTrue(textGood.symbolAt(8).value.equals("😊"));
		
		Text textOmg = new Text("Omg🤦🏼‍♂️!");
		assertTrue(textOmg.symbolAt(3).value.equals("🤦🏼‍♂️"));
		assertTrue(textOmg.symbolAt(4).value.equals("!"));
	}
	

	@Test
	public void testCharAt() {
		Text textGood = new Text(GOOD_TEXT); 
		assertTrue(textGood.charAt(3)=='ó');
		assertTrue(textGood.charAt(4)=='d');

		Text textOmg = Text.of("Omg🤦🏼‍♂️!");
		assertTrue(textOmg.charAt(0)=='O');
		int x = textOmg.toString().indexOf('!');
		assertTrue(textOmg.charAt(x)=='!');
	}
	
	@Test
	public void testSubSequence() {
		Text textOmg = Text.of("Omg🤦🏼‍♂️!");
		int x = textOmg.toString().indexOf('!');
		CharSequence cs = textOmg.subSequence(0, x);
		assertTrue(cs.equals("Omg🤦🏼‍♂️"));
	}


	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testIterator() {
		Text text = new Text("Omg🤦🏼‍♂️!");
		Iterator<Symbol> textIterator = text.iterator();
		assertTrue(textIterator.next().equals("O"));
		assertTrue(textIterator.next().equals("m"));
		assertTrue(textIterator.next().equals("g"));
		assertTrue(textIterator.next().equals("🤦🏼‍♂️"));
		assertTrue(textIterator.next().equals("!"));
		assertFalse(textIterator.hasNext());
		
		text = Text.of(GOOD_TEXT);
		textIterator = text.iterator();
		assertTrue(textIterator.next().equals("g̈"));
		assertTrue(textIterator.next().equals("ó"));
		assertTrue(textIterator.next().equals("ó"));
		assertTrue(textIterator.next().equals("d"));
		assertTrue(textIterator.next().equals(" "));
		assertTrue(textIterator.next().equals("D"));
		assertTrue(textIterator.next().equals("a"));
		assertTrue(textIterator.next().equals("y"));
		assertTrue(textIterator.next().equals("😊"));
		assertFalse(textIterator.hasNext());
	}

}
