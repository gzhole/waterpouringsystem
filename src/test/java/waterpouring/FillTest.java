package waterpouring;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import waterpouring.model.Fill;
import waterpouring.model.Move;

public class FillTest {

	@Test
	public void test() {
		int [] capacity = {4,7};
		Move move = new Fill(0,capacity);
		int [] state = {0,0};
		int [] newState = move.change(state);
		int [] expectedState = {4, 0};
		assertArrayEquals(expectedState, newState);
	}

}
