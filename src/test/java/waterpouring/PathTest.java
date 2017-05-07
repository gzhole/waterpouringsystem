package waterpouring;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import waterpouring.model.Empty;
import waterpouring.model.Fill;
import waterpouring.model.Move;
import waterpouring.model.Path;

public class PathTest {

	@Test
	public void test() {
		List<Move> newHisotry = new LinkedList<Move>();
		int [] capacity = {4,7};
		int [] endState = {0,7};
		newHisotry.add(new Empty(0));
		newHisotry.add(new Fill(1,capacity ));
		
		Path path = new Path(newHisotry, endState);
		assertEquals(path.toString(), "[Fill 1, Empty 0]-->[0, 7]");
	}

}
