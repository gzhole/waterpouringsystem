package waterpouring;

import static com.nurkiewicz.lazyseq.LazySeq.empty;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pouring.moves.State;

import com.nurkiewicz.lazyseq.LazySeq;

import waterpouring.model.Empty;
import waterpouring.model.Fill;
import waterpouring.model.Move;
import waterpouring.model.Path;
import waterpouring.model.Pour;



public class Pouring {
	private int [] capacity = null;
	private int [] initialState = null;
	private Path initialPath;
	private List<Move> moves;
	private LazySeq<List<Path>> pathSets;
	private Pouring() {}
	public Pouring(int [] capacity) {
		this.capacity = capacity;
		initialState = new int[capacity.length];
		initialPath = new Path(null, initialState);
		moves = getAllMoves();
		List <Path> initialPaths = new ArrayList<Path>();
		initialPaths.add(initialPath);
		Set<int []> explored = new HashSet<int []>();
		explored.add(initialState);
		pathSets = from(initialPaths, explored);
		
	}
	
	
	public static void main(String[] args) {
		int [] capacity = {3,7};
		Pouring system = new Pouring(capacity);
		
	}
	
	public List<Move> getAllMoves() {
		List <Move>moves = new LinkedList<Move>();
		for (int i=0 ;i < capacity.length; i++) {
			moves.add(new Empty(i)); 
			moves.add(new Fill(i, capacity));
		}
		
		for (int from = 0; from <capacity.length; from ++) {
			for (int to = 0; to <capacity.length; to ++) {
				if (from!=to) {
					moves.add(new Pour(from, to, capacity));
				}
			}
		}
		
		return moves;
	}
	
private LazySeq<List<Path>> from(List<Path> initialPaths,	Set<int []> explored) {
		
		if (initialPaths.isEmpty()) {
			// Using Collections.EMPTY_LIST was throwing a UnsupportedOperationException
			// when prepending
			return empty();//new ArrayList<List<Path>>();
		}
		else {
			// This 'more' gives us the new set of paths after adding all
			// possible moves to all pre-existing paths not yet explored...
			List<Path> more = new ArrayList<Path>();
			for (Path path : initialPaths) {
				for (Move move : moves) {
					Path next = path.extend(move);
					if (!isExplored(explored, next.getEndState())) {
						more.add(next);
					}
				}
			}
			
		//	Set <State> aPath = more.stream().map(x -> x.endState).collect(Collectors.toSet());
		//	explored.addAll(aPath);
			// Calling recursively and prepending...
			//LazySeq<List<Path>> newPaths = fromInitialPaths(more, addToExplored(explored, more));
			//newPaths.add(0, initialPaths);
		//	return LazySeq.cons(initialPaths, () -> fromInitialPaths(more, addToExplored(explored, more)));
			//return LazySeq.concat(initialPaths, () -> newPaths);
			//Stream.concat(newStringSet.stream(), oldStringSet.stream()).collect(Collectors.toSet())
			System.out.println(initialPaths);
			return LazySeq.cons(initialPaths, () -> from(more, conStream(explored, more)));
			
			//newPaths.cons(0, () -> initialPaths);
			
		//	return newPaths;
		}
	}

private boolean isExplored(Set<int[]> explored, int [] endState) {
	for (int[] state : explored) {
		if ( Arrays.equals(state, endState)) {
			return true;
		}
	}
	return false;
}
private Set<int []> conStream(Set<int []> explored, List<Path> more) {
	
	return Stream.concat(explored.stream(), more.stream().map(x -> x.getEndState())).collect(Collectors.toSet());
}

public Path solution(int target) {
	for (List <Path> pathSet: pathSets) {
		for (Path path: pathSet) {
			if (isTargetInEndState(path.getEndState(),target)) {
				return path;
			}
		}
	}
	
	
	return null;
	
}
private boolean isTargetInEndState(int[] endState, int target) {
	for (int i : endState) {
		if (target == i)
			return true;
	}
	return false;
}


}
