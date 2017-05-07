package pouring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import pouring.moves.Empty;
import pouring.moves.Fill;
import pouring.moves.Move;
import pouring.moves.Path;
import pouring.moves.Pour;

public class PouringTest {

	// http://www.math.tamu.edu/~dallen/hollywood/diehard/diehard.htm
	
	@Test
	public void testTheWaterJugRiddleFromDieHard3new() {
		
		int[] capacities = {3,7};
		Pouring pouring = new Pouring(capacities);
		
		Path solutionPath = pouring.getShortestSolution(5);
		
		List<Move> history = new ArrayList<>();
		history.add(new Fill(capacities, 1));
		history.add(new Pour(capacities, 1,0));
		history.add(new Empty(0));
		history.add(new Pour(capacities, 1,0));
		history.add(new Fill(capacities, 1));
		history.add(new Pour(capacities, 1,0));
		
		// Moves come inverted in the response
		Collections.reverse(history);

	//	Assert.assertThat(solutionPath.history, Is.is(history));
		System.out.println("\nSHORTEST SOLUTION: " + solutionPath +"\n");
	}
	
	
//	@Test
	public void testTheWaterJugRiddleFromDieHard3() {
		
		int[] capacities = {3,7};
		PouringOrig pouring = new PouringOrig(capacities);
		
		Path solutionPath = pouring.getShortestSolution(3);
		
		List<Move> history = new ArrayList<>();
		history.add(new Fill(capacities, 1));
		history.add(new Pour(capacities, 1,0));
		history.add(new Empty(0));
		history.add(new Pour(capacities, 1,0));
		history.add(new Fill(capacities, 1));
		history.add(new Pour(capacities, 1,0));
		
		// Moves come inverted in the response
		Collections.reverse(history);

	//	Assert.assertThat(solutionPath.history, Is.is(history));
		System.out.println("\nSHORTEST SOLUTION: " + solutionPath +"\n");
	}
}
