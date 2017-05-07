package waterpouring.model;


public class Fill implements Move {
	private final int glass;
	private final int [] capacity; 
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
		return this.getClass().getSimpleName() + " " + glass;
	}

}
