import java.util.List;

import com.nurkiewicz.lazyseq.LazySeq;


public class Test {

	
	private static LazySeq<Integer> naturals(int from) {
		return LazySeq.cons(from, () -> naturals(from + 1));
	}
	
	public static void main (String args []) {
		System.out.println(naturals(2));
	}

	public List<Record> loadPage(int offset, int max) {
		return null;
		//load records from offset to offset + max
	}
	//I abstract from the technology entirely, but you get the point. Imagine that we now define LazySeq<Record> that starts from row 0 and loads next pages only when needed:
//
	public static final int PAGE_SIZE = 5;

	private LazySeq<Record> records(int from) {
		return LazySeq.concat(
			loadPage(from, PAGE_SIZE),
			() -> records(from + PAGE_SIZE)
		);
	}
}

class Record {
	
}