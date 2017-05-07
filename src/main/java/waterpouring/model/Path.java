package waterpouring.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Path {
	private final List<Move> history;
	private final int [] endState;
	
	public int[] getEndState() {
		return endState;
	}

	public Path (List<Move> hisotry, int [] endState) {
		this.history = hisotry;
		this.endState = endState;
		
	}
	
	public Path extend(Move move) {
		List<Move> newHisotry = new LinkedList<Move>();
		if (history != null ) {
			newHisotry.addAll(history);
		}
		newHisotry.add(0,move);
		return new Path(newHisotry, move.change(endState));
		
	}
	
	@Override
	public String toString() {
		List<Move> newHisotry = new LinkedList<Move>();
		if (history != null ) {
			newHisotry.addAll(history);
		}
		 Collections.reverse(newHisotry);
		return newHisotry + "-->" +  Arrays.toString(endState);
	}
}
