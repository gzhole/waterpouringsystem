package waterpouring;

import static org.junit.Assert.*;

import org.junit.Test;

import waterpouring.model.Path;

public class PouringTest {

	@Test
	public void test() {
		int [] capacity = {4,7};
		Pouring pouring = new Pouring(capacity);
		Path path = pouring.solution(4);
		System.out.println(path);
	}

}
