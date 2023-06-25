package ustdlib;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Null handling and helper methods
 * 
 * @author AndrewClark
 *
 */
public class NullSafe {
	public static @NonNull String value(String s)  {
		return s !=null ? s : "";
	}
	public static @NonNull Integer value(Integer i)  {
		return i !=null ? i : Integer.valueOf(0);
	}
	/* These were a good idea, but Java is stupid and doesn't have a "GuaranteedPublicDefaultContsructor" Interface
	 * so you can't do T(), and getClass() doesn't work on null pointers (Delphi *does* have this, btw... :P)
	public static <T extends Number> T NullSafe(T i)  {
		return i !=null ? i : new T(0);
	}
	public static <T implements GuaranteedPublicDefaultConstructor> T NullSafe(T object) {
		if (object !=null)
			return object;
		try {
			return T(); // Nope, no GuaranteedPublicDefaultConstructor... :(
		} catch (IllegalAccessException | InstantiationException e) {
			throw new Error(e);
		}
	}
	public static <T> T NullSafe(T object) {
		if (object !=null)
			return object;
		try {
			return (T) (object.getClass().newInstance()); // Nope, Java null objects have no class information... :(
		} catch (IllegalAccessException | InstantiationException e) {
			throw new Error(e);
		}
	}
	*/
	public static <T> @NonNull T value(T object, @NonNull NonNullSupplier<T> s) {
		return object !=null ? object : s.get();
	}
	public static <T> @NonNull T value(T object, @NonNull T defaultValue)  {
		return object !=null ? object : defaultValue;
	}

	/* This should probably not throw an exception...
	public interface SafeCallable<V> extends Callable<V> {
	    @Override
		@NonNull V call() throws Exception;
	}
	public static <T> @NonNull T NullSafe(T object, SafeCallable<T> c) throws Exception  {
	*/



}
