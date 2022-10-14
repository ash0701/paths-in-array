import java.util.ArrayList;

/**
 * This class is the class responsible for finding all paths of a given array. It
 * takes in an string array from the main class composed of digits
 * It is capable of finding all possible paths to the last index of the array
 * and counting the amount of paths possible. 
 * 
 * @author Ashley Huang
 *
 */
public class ProblemSolve {

	private static String[] toBeSolved;
	private static int pathCount;

	/**
	 * Creates a ProblemSolve object with a given String array composed of digits
	 * @param arr the String array consisting of digits ranging from 0 to 99, inclusive
	 */
	public ProblemSolve(String[] arr) {
		toBeSolved = arr;
		pathCount = 0;

	}

	/**
	 * Wrapper method: checks if the array to be explored is only of size 1
	 * If it is, just return result, otherwise call recursive function
	 */
	public void findPath() {
		if (toBeSolved.length == 1) {
			pathCount++;
			System.out.println("[ " + toBeSolved[toBeSolved.length - 1] + " ]");
			return;
		}

		ArrayList<Integer> indexes = new ArrayList<Integer>();
		indexes.add(0);
		ArrayList<Character> directions = new ArrayList<Character>();
		directions.add('R');
		findAllPaths(toBeSolved, indexes, directions);
	}

	/**
	 * Recursive method that finds all possible paths in the array
	 * If a path is found, increment the number of paths found and 
	 * print the steps it took
	 * To avoid repeated calls, remember the index that has already been moved
	 * in a certain direction and do not repeat the same action
	 * If the index out of bounds of the array, then this is not a path
	 * 
	 * @param arr array that is to be traversed
	 * @param indexes a list of indexes that have been checked already
	 * @param directions contains a list of actions it took to get to the goal up until the current step
	 */
	@SuppressWarnings("unchecked")
	public void findAllPaths(String[] arr, ArrayList<Integer> indexes, ArrayList<Character> directions) {

		int index = indexes.get(indexes.size() - 1); // where the last call left off

		if (directions.get(directions.size() - 1) == 'L') // move index to left if direction is L
			index = index - Integer.parseInt(arr[index]);
		if (directions.get(directions.size() - 1) == 'R') // move index to right if direction is R
			index = index + Integer.parseInt(arr[index]);

		if (indexes.contains(index)) // check if path has alrdy been explored
		{
			return;
		}

		if (index < 0 || index > arr.length - 1) { // base case if index goes out of bounds, then return
			return;
		}

		if (index == arr.length - 1) { // base case if we hit the final index of arr, yay!
			pathCount++;
			printPath(indexes, directions); // print all paths with the two ArrayLists
			return;
		}

		ArrayList<Integer> rightIndexes = (ArrayList<Integer>) indexes.clone(); // create clone to keep track of indexes
		// to right
		rightIndexes.add(index);
		ArrayList<Character> toRight = (ArrayList<Character>) directions.clone(); // create clone to keep track of
																			// direction to right
		toRight.add('R');
		findAllPaths(arr, rightIndexes, toRight); // recursive call

		ArrayList<Integer> leftIndexes = (ArrayList<Integer>) indexes.clone(); // create clone of indexes to keep track
																				// of indexes move left
		leftIndexes.add(index);
		ArrayList<Character> toLeft = (ArrayList<Character>) directions.clone(); // create clone to keep track of
		// direction to left																			
		toLeft.add('L');
		findAllPaths(arr, leftIndexes, toLeft); // recursive call

	}

	/**
	 * Is called to print each step of a path that worked
	 * If the list contains the index position in the array, add the action it took at that position
	 * The two lists it takes in are aligned so that the index and the direction correspond to one another
	 * @param pathWay contains the index positions in which action was taken
	 * @param directions contains all the actions taken in the order of the index positions from pathWay
	 */
	public static void printPath(ArrayList<Integer> pathWay, ArrayList<Character> directions) {

		for (int lines = 0; lines < pathWay.size(); lines++) { // because we moved x amount of times and it's stored in
																// pathWay, go for length of pathWay
			System.out.print("[");
			for (int i = 0; i < toBeSolved.length; i++) {
				System.out.print(String.format("%2d", Integer.parseInt(toBeSolved[i]))); // print each thing in the
																							// array
				if (i == pathWay.get(lines)) { // if we moved at this point, or this index is IN pathWay
					System.out.print(String.format("%c", directions.get(lines))); // Add the directions at this index
																					// (pathWay and directions are
																					// aligned)
				} else // otherwise add a space at end
					System.out.printf("%s", " ");

				if (i != toBeSolved.length - 1)
					System.out.printf(",%s", " ");
			}
			System.out.print("]\n");

		}
		System.out.println();

	}
	
	/**
	 * Returns the total number of paths found
	 * @return the total number of paths found
	 */
	public int getNumPaths() {
		return pathCount;
	}
}
