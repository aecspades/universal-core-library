package ustdlib.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ustdlib.SubSequence;

public class SubSequenceTest {

	@Test
	public void testToString() {
		SubSequence seq = new SubSequence("abcdef", 3, 2);
		String str = seq.toString();
		assertEquals("de", str);
	}

}
