package uclib;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.security.InvalidParameterException;

import org.junit.Test;

public class SymbolTest {

	@Test
	public void testSymbol() {
		Symbol symbol = new Symbol();
		assertTrue(symbol.value == "\0");
	}

	@SuppressWarnings("unused")
	@Test
	public void testSymbolString() {
		Symbol symbolA = new Symbol("A");
		Symbol symbolG = new Symbol("g̈");
		Symbol symbolOmg = new Symbol("🤦🏼‍♂️");
		// Should throw InvalidParameterException (only one grapheme cluster allowed)
		try {
			Symbol symbol = new Symbol("AA");
			fail(symbol.toString());
		} catch(InvalidParameterException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testOf() {
		Symbol symbol = Symbol.of("\0");
		assertTrue(symbol == Symbol.NULL_SYMBOL);
		symbol = Symbol.of("A");
		symbol = Symbol.of("g̈");
		symbol = Symbol.of("🤦🏼‍♂️");
	}

	@Test
	public void testToString() {
		Symbol symbol = Symbol.of("A");
		assertTrue(symbol.toString().equals("A"));
		symbol = Symbol.of("g̈");
		assertTrue(symbol.toString().equals("g̈"));
		symbol = Symbol.of("🤦🏼‍♂️");
		assertTrue(symbol.toString().equals("🤦🏼‍♂️"));
	}

	@Test
	public void testLength() {
		Symbol symbol = Symbol.of("A");
		assertTrue(symbol.length()==1);
		symbol = Symbol.of("g̈");
		assertTrue(symbol.length()==2);
		symbol = Symbol.of("🤦🏼‍♂️");
		assertTrue(symbol.length()==7);
	}

	@Test
	public void testCharAt() {
		Symbol symbol = Symbol.of("A");
		assertTrue(symbol.charAt(0)=='A');
	}

	@Test
	public void testSubSequence() {
		Symbol symbol = Symbol.of("A");
		assertTrue(symbol.subSequence(0, 1).equals("A"));
	}

	@Test
	public void testCompareTo() {
		Symbol symbolA = Symbol.of("A");
		assertTrue(symbolA.compareTo("A")==0);
		Symbol symbolG = Symbol.of("g̈");
		assertTrue(symbolG.compareTo("g̈")==0);
		assertFalse(symbolG.compareTo(symbolA)==0);
		Symbol symbolOmg = Symbol.of("🤦🏼‍♂️");
		assertTrue(symbolOmg.compareTo("🤦🏼‍♂️")==0);
		assertFalse(symbolOmg.compareTo(symbolG)==0);
	}

}
