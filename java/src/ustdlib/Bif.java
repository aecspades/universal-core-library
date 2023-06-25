package ustdlib;

import java.util.AbstractList;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Static, minimal (1-line) helper methods that can be considered "Built-In Functions" i.e. should be inlined by compiler
 * 
 * @author AndrewClark
 *
 */
public class Bif {
	
	/*
Supplier       ()    -> x
Consumer       x     -> ()
BiConsumer     x, y  -> ()
Callable       ()    -> x throws ex
Runnable       ()    -> ()
Function       x     -> y
BiFunction     x,y   -> z
Predicate      x     -> boolean
UnaryOperator  x1    -> x2
BinaryOperator x1,x2 -> x3
	 */
	
	/**
	 * @return Null-safe (guaranteed not to be null) value
	 */
	public static @NonNull String NullSafe(String s)  {
		return NullSafe.value(s);
	}
	public static @NonNull Integer NullSafe(Integer i)  {
		return NullSafe.value(i);
	}
	public static <T> @NonNull T NullSafe(T object, @NonNull NonNullSupplier<T> s) {
		return NullSafe.value(object, s);
	}
	public static <T> @NonNull T NullSafe(T object, @NonNull T defaultValue)  {
		return NullSafe.value(object, defaultValue);
	}
	
	
	/**
	 * @return Index of <code>value</code> in Group
	 */
	@SafeVarargs
	public static <T> int IndexOf(T value, T... array) {
		return Group.indexOf(value, array);
	}
	public static <T> int IndexOf(T value, @NonNull AbstractList<T> list) {
		return Group.indexOf(value, list);
	}
	public static int IndexOf(@NonNull String value, @NonNull String s) {
		return Group.indexOf(value);
	}
	public static int IndexOf(int c, @NonNull String s) {
		return Group.indexOf(c);
	}
	
	/**
	 * @return Length of String/Group
	 */
	public static int Length(@NonNull String str) {
		return Str.len(str);
	}
	public static int Length(@NonNull SubSequence seq) {
		return SubSequence.len(seq);
	}
	@SafeVarargs
	public static <T> int Length(T... array) {
		return Group.len(array);
	}
	public static <T> int Length(@NonNull AbstractList<T> list) {
		return Group.len(list);
	}

	
	/**
	 * @return Trimmed String
	 */
	public static @NonNull String Trim(@NonNull String s) {
		return Str.trim(s);
	}
	public static @NonNull String LTrim(@NonNull String s) {
		return Str.ltrim(s);
	}
	public static @NonNull String RTrim(@NonNull String s) {
		return Str.rtrim(s);
	}
	/**
	 * @return Concatenated Strings
	 */
	public static @NonNull String StrCat(@NonNull String str1, @NonNull String str2) {
		return Str.cat(str1, str2);
	}
	
	/**
	 * @return {@link Sequence} equivalent of <code>value</code>
	 */
	public static Sequence ToSeq(@NonNull String value) {
		return new Sequence(value);
	}
	
	/**
	 * @return {@link Text} equivalent of <code>value</code>
	 */
	public static Text ToTxt(@NonNull String value) {
		return new Text(value);
	}
}
