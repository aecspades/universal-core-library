package uclib;

import static org.junit.Assert.*;

import org.junit.Test;

public class CStrTest {

	static final String str1="abc";
	static final String str2="Defg";
	
	@Test
	public void testStrCat() {
		String s = CStr.strCat(str1, str2);
		assertEquals("abcDefg", s);
	}

}
