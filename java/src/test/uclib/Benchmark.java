package uclib;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

import static uclib.BIF.*;

import org.eclipse.jdt.annotation.NonNull;


public class Benchmark {
	
	static long LOOP_COUNT=10000000; // 10,000,00
	static String NUMBER_FORMAT="%,d";
	
	public static void runBenchmark(String benchmark, Supplier<String> s) {
	    ThreadMXBean threadMX = ManagementFactory.getThreadMXBean();
	    long time = threadMX.getCurrentThreadCpuTime();
	    long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		System.out.println("\nBenchmark begin    : " + benchmark);

		for (int i=0; i<LOOP_COUNT; i++) {
			s.get();
		}
		
		long elapsedTime = threadMX.getCurrentThreadCpuTime() - time;
		long usedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory() - beforeUsedMem;
		System.out.println("Elapsed nanoseconds: " + String.format(NUMBER_FORMAT, elapsedTime) + "    Used memory: " + String.format(NUMBER_FORMAT, usedMem));
		System.out.println(benchmark + '\t' + elapsedTime + '\t' + usedMem);
		System.out.println("Benchmark end      : " + benchmark); 
		
	}

	public static void runBenchmark(String parm0) {
		//String nullableString = "benchmark-nullable".equals(parm0) ? null : "nullableString";
		
		// Run full suite (warmup/ternary/optional/NullSafe) of benchmarks 25 times
		for (short i=1; i<=10; i++) {
			String nullableString = (new Random().nextBoolean()) ? "nullableString" : null;
			
			System.out.println("----------");
			System.out.println("Test#: " + String.format("%2d", i) + " LoopCount: " + String.format(NUMBER_FORMAT, LOOP_COUNT) + " nullableString: " + nullableString);

			//Warmup
			runBenchmark("Warmup", () ->
			{ return ""; } );
		
			// Ternary
			runBenchmark("Ternary", () ->
				{ return nullableString!=null ? nullableString : ""; } );
			
			// Optional
			runBenchmark("Optional", () ->
				{ return Optional.ofNullable(nullableString).orElse(""); } );
			
			// NullSafe(String)
			runBenchmark("NullSafe(String)", () ->
				{ return NullSafe(nullableString); } );
			
			// NullSafe(String, String)
			runBenchmark("NullSafe(String, String)", () ->
				{ return NullSafe(nullableString, ""); } );
			
			// NullSafe(String, SafeSupplier)
			runBenchmark("NullSafe(String, SafeSupplier)", () ->
				{ return NullSafe(nullableString, () ->
						{ return new String(); } );
				} );
			
			System.out.println("**********\n\n\n");
		}
	}

	public static void main(String[] args) throws Exception {
		
		
		String nullString = null;
		String nullableString = "0123456789";
		SubSequence subSequence = new SubSequence(nullableString, 4); 
		subSequence = new SubSequence(nullableString, 3, 5);
		StringBuilder sb=new StringBuilder();
		for (char c: subSequence)
			sb.append(c);
			

		ReverseSubSequence reverseSubSequence = new ReverseSubSequence(nullableString, 0);
		reverseSubSequence = new ReverseSubSequence(nullableString, 4);
		reverseSubSequence = new ReverseSubSequence(nullableString, 3, 5);
		sb.setLength(0);
		for (char c: reverseSubSequence)
			sb.append(c);
		
		CharSequence charSequence = subSequence.subSequence(0, subSequence.length());
		charSequence = subSequence.subSequence(1, subSequence.length());
		
		charSequence = reverseSubSequence.subSequence(0, reverseSubSequence.length());
		charSequence = reverseSubSequence.subSequence(1, subSequence.length());
		
		if (args.length>0) {
			String parm0 = args[0];
			switch (parm0) {
			case "benchmark":
			case "benchmark-nullable":
				runBenchmark(parm0);
				return;
			case "nullable":
				nullableString = null;
			}
		}
		String valueString = null;
		@NonNull String nonNullString;
		
		/* Errors
		nonNullString=null;
		nonNullString=nullString;
		nonNullString=nullableString;
		nonNullString=valueString;
		*/
		
		nonNullString = NullSafe(nullString);
		/* Errors
		nonNullString = NullSafe(valueString, null);
		nonNullString = NullSafe(valueString, nullString);
		nonNullString = NullSafe(nullString, nullableString);
		nonNullString = NullSafe(nullString, valueString);
		*/ 

		nonNullString = NullSafe(nullString, 
				() -> {return "";} );
		
		// Guaranteed to work
		System.out.println(nonNullString.length());
		// Could throw NullPointerException (if args[0]=="null")
		System.err.println(nullableString.length());
		//TODO: Guaranteed to throw NullPointerException
		System.err.println(nullString.length());
		
		System.out.println(nonNullString);
		int[] iarray = {1, 2, 3};
		int x = IndexOf(2, iarray);
		System.out.println(x);
		
		// Suppress compiler warnings for unused local variables
		System.out.println(valueString + nullString + charSequence);
	}

}
