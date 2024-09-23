package uclib;

import java.util.AbstractList;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Helper/utility class for collections/containers
 * 
 * @author AndrewClark
 *
 */
public final class Group {
	private Group() {}
	
	@SafeVarargs
	public static <T> int indexOf(T value, T... array) {
		//return Arrays.asList(array).indexOf(value);
		for (int i=0; i<array.length; i++) {
			if (array[i].equals(value))
				return i;
		}
		return -1;
	}
	
	public static <T> int indexOf(T value, @NonNull AbstractList<T> list) {
		return list.indexOf(value);
	}
	
	@SafeVarargs
	public static <T> int len(T... array) {
		return array.length;
	}
	public static <T> int len(@NonNull AbstractList<T> list) {
		return list.size();
	}


}
