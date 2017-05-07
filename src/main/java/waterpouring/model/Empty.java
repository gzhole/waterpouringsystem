package waterpouring.model;

public class Empty implements Move {
	private int glass;
	public Empty(int glass) {
		this.glass = glass;
	}
	@Override
	public int[] change(int[] state) {
		int [] newState = state.clone(); 
		newState[glass] = 0;
		return newState;
	}
	
	@Override
	public String toString() {
		//List<Move> newHisotry = new LinkedList<Move>(history);
		// Collections.reverse(newHisotry);
		return this.getClass().getSimpleName() + " " + glass;
	}

}
