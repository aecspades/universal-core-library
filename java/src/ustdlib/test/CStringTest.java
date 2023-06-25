package ustdlib.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ustdlib.CStr;

public class CStringTest {

	@Test
	public void testStrCat() {
		String str1="abc";
		String str2="def";
		String s = CStr.strCat(str1, str2);
		assertEquals("abcdef", s);
	}

}
