package waterpouring.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Fill implements Move {
	private int glass;
	private int [] capacity; 
	public Fill(int glass, int [] capacity) {
		this.glass = glass;
		this.capacity = capacity;
	}
	@Override
	public int[] change(int[] state) {
		int [] newState = state.clone(); 
		newState[glass] = capacity[glass];
		return newState;
	}
	
	@Override
	public String toString() {
		//List<Move> newHisotry = new LinkedList<Move>(history);
		// Collections.reverse(newHisotry);
		return this.getClass().getSimpleName() + " " + glass;
	}

}
