package waterpouring.model;

public class Pour implements Move {
	private final int fromGlass;
	private final int toGlass;
	private final int [] capacity; 
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
		return this.getClass().getSimpleName() + " from " + fromGlass + " to " + toGlass;
	}

}
