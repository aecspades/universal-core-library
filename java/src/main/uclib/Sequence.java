package ustdlib;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Specialized version of SubSequence that implies start=0 (and length=value.length())
 * 
 * @author AndrewClark
 *
 */
public class Sequence extends SubSequence {
	public Sequence(@NonNull String value) {
		super(value, 0);
	}

}
