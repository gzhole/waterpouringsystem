package waterpouring;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import waterpouring.model.Path;

public class Utilities {
	public static boolean isTargetInEndState(int[] endState, int target) {
		for (int i : endState) {
			if (target == i)
				return true;
		}
		return false;
	}
	
	
	public static Set<int[]> addPathsToExplored(Set<int[]> explored, List<Path> more) {

		return Stream.concat(explored.stream(),	more.stream().map(x -> x.getEndState())).collect(Collectors.toSet());
	}
	
	public static boolean isExplored(Set<int[]> explored, int[] endState) {
		for (int[] state : explored) {
			if (Arrays.equals(state, endState)) {
				return true;
			}
		}
		return false;
	}
}
