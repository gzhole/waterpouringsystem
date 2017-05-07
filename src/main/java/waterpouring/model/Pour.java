package waterpouring.model;

public class Pour implements Move {
	private int fromGlass;
	private int toGlass;
	private int [] capacity; 
	public Pour(int fromGlass, int toGlass, int [] capacity) {
		this.fromGlass = fromGlass;
		this.toGlass = toGlass;
		this.capacity = capacity;
	}
	@Override
	public int[] change(int[] state) {
		int availableVolumeinToClass = capacity[toGlass] - state[toGlass];
		int amount = Math.min(state[fromGlass], availableVolumeinToClass);
		int [] newState = state.clone(); 
		
		newState[fromGlass] = state[fromGlass] - amount;
		newState[toGlass] = state[toGlass] + amount;
		
		return newState;
	}
	
	
	@Override
	public String toString() {
		//List<Move> newHisotry = new LinkedList<Move>(history);
		// Collections.reverse(newHisotry);
		return this.getClass().getSimpleName() + " from " + fromGlass + " to " + toGlass;
	}

}
