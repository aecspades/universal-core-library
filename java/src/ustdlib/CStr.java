package ustdlib;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Wrapper class for <a href="https://en.cppreference.com/w/cpp/header/cstring">&lt;cstring&gt;</a>-like methods
 * 
 * @author AndrewClark
 *
 */
public final class CStr {
	private CStr() {}
	
	/**
	 * String span
	 * @return Length of <code>str</code> consisting of only the characters not found in <code>searchCharacters</code>
	 * @see Str#indexOfNone(String, String) 
	 * @see <a href="https://en.cppreference.com/w/cpp/string/byte/strspn">strspn</a> 
	 */
	public static int strSpn(@NonNull String str, @NonNull String searchCharacters) {
		int x = Str.indexOfNone(str, searchCharacters);
		return x>=0 ? x : str.length();
	}
	/**
	 * String complementary span
	 * @return Length of <code>str</code> consisting of none of the characters not found in <code>searchCharacters</code>
	 * @see Str#indexOfAny(String, String) 
	 * @see <a href="https://en.cppreference.com/w/cpp/string/byte/strcspn">strcspn</a> 
	 */
	public static int strcSspn(@NonNull String str, @NonNull String searchCharacters) {
		int x = Str.indexOfAny(str, searchCharacters);
		return x>=0 ? x : str.length();
	}
	
	/**
	 * String pointer break
	 * @return SubSequence of <code>str</code> at first occurrence of any characters in <code>breakset</code>, or null if no match
	 * @see Str#indexOfAny(String, String) 
	 * @see SubSequence 
	 * @see <a href="https://en.cppreference.com/w/cpp/string/byte/strpbrk">strpbrk</a> 
	 */
	public static @Nullable SubSequence strPBrk(@NonNull final String str, @NonNull String breakset) {
		int x = Str.indexOfAny(str, breakset);
		return x>=0 ? new SubSequence(str, x) : null;
	}
	
	
	/**
	 * @return Subsequence of <code>str</code> at first occurrence of <code>c</code>, or null if not found
	 * see @link Bif#IndexOf(int, String)
	 * @see <a href="https://en.cppreference.com/w/cpp/string/byte/strchr">strchr</a> 
	 */
	public static @Nullable SubSequence strChr(@NonNull String str, int c) {
		int x = Bif.IndexOf(c, str);
		return x>=0 ? new SubSequence(str, x) : null;
	}

	/**
	 * @return Subsequence of <code>str</code> at last occurrence of <code>c</code>, or null if not found
	 * @see Str#lastIndexOf(String, int)
	 * @see <a href="https://en.cppreference.com/w/cpp/string/byte/strrchr">strrchr</a> 
	 */
	public static @Nullable SubSequence strrChr(@NonNull String str, int c) {
		int x = Str.lastIndexOf(str, c);
		return x>=0 ? new SubSequence(str, x) : null;
	}
	
	/** 
	 * String concatenation - alias for {@link Str#concat(String, String)}
	 * @see <a href="https://en.cppreference.com/w/cpp/string/byte/strcat">strcat</a>
	 */ 
	public static @NonNull String strCat(@NonNull String str1, @NonNull String str2) {
		return Str.concat(str1, str2);
	}
	
	private final static String COUNT_GE_0 = "count must be >=0";
	/** 
	 * Concatenates <code>count</code> characters of <code>str2</code> to <code>str1</code>
	 * @see Str#concat(String, String)
	 * @see <a href="https://en.cppreference.com/w/cpp/string/byte/strncat">strncat</a>
	 */ 
	public static @NonNull String strnCat(@NonNull String str1, @NonNull String str2, int count) {
		if (count<0)
			throw new IllegalArgumentException(COUNT_GE_0);
		
		return Str.concat(str1, str2.substring(0, count));
	}

	
	/**
	 * String compare - alias for {@link Str#compare(String, String)}
	 * @see <a href="https://en.cppreference.com/w/cpp/string/byte/strcmp">strcmp</a>
	 */
	public static int strCmp(@NonNull String str1, @NonNull String str2) {
		return Str.compare(str1, str2);
	}
	/**
	 * Compares <code>count</code> characters of <code>str2</code> to <code>str1</code>
	 * @see Str#compare(String, String)
	 * @see <a href="https://en.cppreference.com/w/cpp/string/byte/strncmp">strncmp</a>
	 */
	public static int strnCmp(@NonNull String str1, @NonNull String str2, int count) {
		if (count<0)
			throw new IllegalArgumentException(COUNT_GE_0);
		
		return Str.compare(str1.substring(0, count), str2.substring(0, count));
	}
	
	
	/**
	 * String in string
	 * @return Subsequence of <code>str1</code> at first occurrence of <code>str2</code>, or null if not found
	 * @see Bif#IndexOf(String, String)
	 * @see <a href="https://en.cppreference.com/w/cpp/string/byte/strstr">strstr</a>
	 */
	public static @Nullable SubSequence strStr(@NonNull String str1, @NonNull String str2) {
		int x = Bif.IndexOf(str2, str1);
		return x>=0 ? new SubSequence(str1, x) : null;
	}
	

	/**
	 * String length - alias for {@link Str#len}
	 * @see <a href="https://en.cppreference.com/w/cpp/string/byte/strlen">strlen</a>
	 */
	public static int strLen(@NonNull String str) {
		return Str.len(str);
	}

	public static void main(String[] args) {
		int x = strSpn("abcdefg", "bca"); // Should be 3 i.e. 'd' not in "bca"
		x = strSpn("abcdefg", "zxy"); // Should be 0 i.e. not found
		x = strSpn("abcdefg", "gfedbca"); // Should be 7 i.e. all characters match str.len()
		
		
		x = strcSspn("abcdefg", "zxye"); // Should be 4 i.e. 'e' in "zxye"
		SubSequence seq = strPBrk("abcdefg", "zxye"); // Should be "efg" i.e. 'e' in "zxye"
		x = strcSspn("abcdefg", "zxy"); // Should be 7 i.e. no characters match str.len()
		seq = strPBrk("abcdefg", "zxy"); // Should be null i.e. not found
		
		seq = strChr("abcdefg", 'f'); // Should be "fg"
		seq = strStr("abcdefg", "def"); // Should be "defg"
		seq = strStr("abcdefg", "zxy"); // Should be null i.e. not found

		x = strCmp("abcdef", "abc"); // >0
		x = strnCmp("abcdef", "abc", 3); // 0
		x = strnCmp("abcdef", "abc", 0); // 0
		//x = strnCmp("abcdef", "abc", -1); // IllegalArgument
		
		String s = strCat("abc", "def"); // "abcdef"
		s = strnCat("abc", "defghi", 3); // "abcdef"
		s = strnCat("abc", "defghi", 0); // "abc"
		s = strnCat("abc", "defghi", -1); // Illegal argument 
		
		// Suppress compiler warnings for unused local variables
		System.out.println(s + seq + x);
	}
	

}
