package uclib;

import java.util.function.Supplier;

import org.eclipse.jdt.annotation.NonNull;

public interface NonNullSupplier<V> extends Supplier<V> {
	@Override
	@NonNull V get();
}