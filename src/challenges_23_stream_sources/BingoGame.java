package challenges_23_stream_sources;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class BingoGame {

	public static void main(String[] args) {
		// B: use IntStream.rangeClosed -> B1..B15
		Stream<String> b = IntStream.rangeClosed(1, 15).mapToObj(n -> "B" + n);

		// I: use Stream.iterate -> I16..I30
		Stream<String> i = Stream.iterate(16, n -> n + 1).limit(15).map(n -> "I" + n);

		// N: use Stream.of from an Integer array -> N31..N45
		Integer[] nNums = IntStream.rangeClosed(31, 45).boxed().toArray(Integer[]::new);
		Stream<String> n = Stream.of(nNums).map(num -> "N" + num);

		// G: use Stream.generate with AtomicInteger -> G46..G60
		AtomicInteger gCounter = new AtomicInteger(46);
		Stream<String> g = Stream.generate(() -> "G" + gCounter.getAndIncrement()).limit(15);

		// O: use IntStream.rangeClosed -> O61..O75 (mapToObj)
		Stream<String> o = IntStream.rangeClosed(61, 75).mapToObj(nu -> "O" + nu);

		// Collect each group's labels into one line and print (one line per letter)
		String bLine = b.collect(Collectors.joining(" "));
		String iLine = i.collect(Collectors.joining(" "));
		String nLine = n.collect(Collectors.joining(" "));
		String gLine = g.collect(Collectors.joining(" "));
		String oLine = o.collect(Collectors.joining(" "));

		System.out.println(bLine);
		System.out.println(iLine);
		System.out.println(nLine);
		System.out.println(gLine);
		System.out.println(oLine);
	}

}

// ====== output ======
// B1 B2 B3 B4 B5 B6 B7 B8 B9 B10 B11 B12 B13 B14 B15
// I16 I17 I18 I19 I20 I21 I22 I23 I24 I25 I26 I27 I28 I29 I30
// N31 N32 N33 N34 N35 N36 N37 N38 N39 N40 N41 N42 N43 N44 N45
// G46 G47 G48 G49 G50 G51 G52 G53 G54 G55 G56 G57 G58 G59 G60
// O61 O62 O63 O64 O65 O66 O67 O68 O69 O70 O71 O72 O73 O74 O75
