package uclib;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SubSequenceTest {

	@Test
	public void testToString() {
		SubSequence seq = new SubSequence("abcdef", 3, 2);
		String str = seq.toString();
		assertEquals("de", str);
	}

}
