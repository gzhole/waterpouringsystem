package waterpouring;

import static com.nurkiewicz.lazyseq.LazySeq.empty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import waterpouring.model.Empty;
import waterpouring.model.Fill;
import waterpouring.model.Move;
import waterpouring.model.Path;
import waterpouring.model.Pour;

import com.nurkiewicz.lazyseq.LazySeq;

public class Pouring {
	private final int[] capacity;
	private final int[] initialState;
	private final Path initialPath;
	private final List<Move> moves;
	private final LazySeq<List<Path>> pathSets;

	public Pouring(int[] capacity) {
		this.capacity = capacity;
		initialState = new int[capacity.length];
		initialPath = new Path(null, initialState);
		moves = getAllMoves();
		List<Path> initialPaths = new ArrayList<Path>();
		initialPaths.add(initialPath);
		Set<int[]> explored = new HashSet<int[]>();
		explored.add(initialState);
		pathSets = from(initialPaths, explored);

	}

	public static void main(String[] args) {
		int[] capacity = { 3, 7 };
		Pouring system = new Pouring(capacity);
		Path resultPath = system.solution(5);
		System.out.println("result is: " + resultPath);

	}

	public List<Move> getAllMoves() {
		List<Move> moves = new LinkedList<Move>();
		for (int i = 0; i < capacity.length; i++) {
			moves.add(new Empty(i));
			moves.add(new Fill(i, capacity));
		}

		for (int from = 0; from < capacity.length; from++) {
			for (int to = 0; to < capacity.length; to++) {
				if (from != to) {
					moves.add(new Pour(from, to, capacity));
				}
			}
		}
		return moves;
	}

	private LazySeq<List<Path>> from(List<Path> initialPaths,
			Set<int[]> explored) {

		if (initialPaths.isEmpty()) {
			return empty();
		} else {
			List<Path> more = new ArrayList<Path>();
			for (Path path : initialPaths) {
				for (Move move : moves) {
					Path next = path.extend(move);
					if (!isExplored(explored, next.getEndState())) {
						more.add(next);
					}
				}
			}

		//	System.out.println(initialPaths);
			return LazySeq.cons(initialPaths, () -> from(more, addPathsToExplored(explored, more)));
		}
	}

	private boolean isExplored(Set<int[]> explored, int[] endState) {
		for (int[] state : explored) {
			if (Arrays.equals(state, endState)) {
				return true;
			}
		}
		return false;
	}

	private Set<int[]> addPathsToExplored(Set<int[]> explored, List<Path> more) {

		return Stream.concat(explored.stream(),	more.stream().map(x -> x.getEndState())).collect(Collectors.toSet());
	}

	public Path solution(int target) {
		for (List<Path> pathSet : pathSets) {
			for (Path path : pathSet) {
				if (isTargetInEndState(path.getEndState(), target)) {
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
