
/**
 * 
 * This program takes in arguments from the command line. Valid input from
 * command line includes numbers within the range 0 to 99, inclusive. The last
 * digit entered must be 0
 * 
 * The goal is to print all paths that reach the last number in the array args
 * 
 * 
 */
public class WayFinder {

	public static void main(String[] args) {
		try {
			
			//checks if input is numbers, otherwise stop
			for (String s : args) {
				for (int i = 0; i < s.length(); i++) {
					if (!Character.isDigit(s.charAt(i))) {
						System.err.println("ERROR: Input must be composed of numbers");
						System.exit(1);
					}
				}
			}

			//check if last number entered is 0, otherwise stop
			if (!args[args.length - 1].equals("0")) {
				System.err.println("ERROR: The last number you enter should be a 0");
				System.exit(1);
			}

			//checks if all numbers are within range
			for (int i = 0; i < args.length; i++) {
				if (Integer.parseInt(args[i]) < 0) {
					System.err.print("Hiya! Please keep your numbers positive <3");
					System.exit(1);
				}
				if(Integer.parseInt(args[i]) > 99) {
					System.err.print("Hey! Numbers greater than 99 are not allowed");
					System.exit(1);
				}
			}

			ProblemSolve solv = new ProblemSolve(args);

			solv.findPath();
			if (solv.getNumPaths() == 0)
				System.out.println("No way through this puzzle.");
			else if (solv.getNumPaths() == 1)
				System.out.println("There is " + solv.getNumPaths() + " way through the puzzle.");
			else
				System.out.println("There are " + solv.getNumPaths() + " ways through the puzzle.");

		} catch (ArrayIndexOutOfBoundsException ex) { //occurs if nothing is inputed as argument
			System.err.println("Please enter a list of numbers ranging from 0 to 99, inclusive ");
			System.exit(1);
		}

	}
}
