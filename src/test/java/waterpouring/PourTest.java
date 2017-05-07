package waterpouring;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import waterpouring.model.Move;
import waterpouring.model.Pour;

public class PourTest {

	@Test
	public void test() {
		int [] capacity = {4,7};
		Move move = new Pour(1,0, capacity);
		int [] state = {0,7};
		int [] newState = move.change(state);
		int [] expectedState = {4, 3};
		assertArrayEquals(expectedState, newState);
	}

}
