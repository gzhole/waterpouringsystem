package waterpouring;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import waterpouring.model.Path;

public class PouringTest {

	/**
	 * Test base case
	 */
	@Test
	public void testBaseCase() {
		int [] capacity = {4,7};
		Pouring pouring = new Pouring(capacity);
		Path path = pouring.solution(0);
		
		assertEquals(path.toString(), "[]-->[0, 0]");
	
	}
	
	/**
	 * Test first layer
	 */
	@Test
	public void testLayer1() {
		int [] capacity = {4,7};
		Pouring pouring = new Pouring(capacity);
		Path path = pouring.solution(4);
		assertEquals(path.toString(), "[Fill 0]-->[4, 0]");
		path = pouring.solution(7);
	//	System.out.println(path);
		assertEquals(path.toString(), "[Fill 1]-->[0, 7]");	
	}
	
	
	@Test
	public void testLayer2() {
		int [] capacity = {4,7};
		Pouring pouring = new Pouring(capacity);
		Path path = pouring.solution(3);
		assertEquals(path.toString(), "[Fill 1, Pour from 1 to 0]-->[4, 3]");
		//path = pouring.solution(7);
	//	System.out.println(path);
	}

	
	@Test
	public void testLayerBeyond() {
		int [] capacity = {4,7};
		Pouring pouring = new Pouring(capacity);
		Path path = pouring.solution(5);
		assertEquals(path.toString(), "[Fill 0, Pour from 0 to 1, Fill 0, Pour from 0 to 1, Empty 1, Pour from 0 to 1, Fill 0, Pour from 0 to 1]-->[0, 5]");
	}
	
	@Test
	public void testLayerBeyondComplex() {
		int [] capacity = {4,11};
		Pouring pouring = new Pouring(capacity);
		Path path = pouring.solution(9);
		assertEquals(path.toString(), "[Fill 0, Pour from 0 to 1, Fill 0, Pour from 0 to 1, Fill 0, Pour from 0 to 1, Empty 1, Pour from 0 to 1, Fill 0, Pour from 0 to 1, Fill 0, Pour from 0 to 1]-->[0, 9]");
//		System.out.println(path);
	}
	
	
	@Test
	public void testLayerBeyondManyState() {
		int [] capacity = {4,5,6,11};
		Pouring pouring = new Pouring(capacity);
		Path path = pouring.solution(10);
		assertEquals(path.toString(), "[Fill 0, Fill 2, Pour from 0 to 3, Pour from 2 to 3]-->[0, 0, 0, 10]");
	//	System.out.println(path);
	}
	
	}
