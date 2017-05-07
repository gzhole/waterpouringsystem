package waterpouring;

import static org.junit.Assert.*;

import org.junit.Test;

import waterpouring.model.Empty;
import waterpouring.model.Move;

public class EmptyTest {

	@Test
	public void test() {
		Move move = new Empty(0);
		int [] state = {1,1};
		int [] newState = move.change(state);
		int [] expectedState = {0, 1};
		assertArrayEquals(expectedState, newState);
	}

}
