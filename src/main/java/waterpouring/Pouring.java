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

/**
 * 
 * A functional approach to Solve the Water Pouring puzzles
 * 
 * @author garyzeng
 *
 */
public final class Pouring {
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
		int[] capacity = { 3, 5 };
		Pouring system = new Pouring(capacity);
		Path resultPath = system.solution(4);
		System.out.println("result is: " + resultPath);

	}

	private List<Move> getAllMoves() {
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

	private LazySeq<List<Path>> from(List<Path> initialPaths,Set<int[]> explored) {
		if (initialPaths.isEmpty()) {
			return empty();
		} else {
			List<Path> more = new ArrayList<Path>();
			for (Path path : initialPaths) {
				for (Move move : moves) {
					Path next = path.extend(move);
					if (!Utilities.isExplored(explored, next.getEndState())) {
						more.add(next);
					}
				}
			}
			return LazySeq.cons(initialPaths,	() -> from(more,Utilities.addPathsToExplored(explored, more)));
		}
	}

	public Path solution(int target) {
		for (List<Path> pathSet : pathSets) {
			for (Path path : pathSet) {
				if (Utilities.isTargetInEndState(path.getEndState(), target)) {
					return path;
				}
			}
		}
		return null;
	}
}
