
public class Task10Solve {
	//Liora Ifraimov ID:315389197 Last update: 1/12/17 00:15
	//This function solves a given soduko
	public static int[][] solve(int sqrtN, int[][] hints) {
		int [][] solution;
		int n=sqrtN*sqrtN;
		int[][][] map=Task7Map.varsMap(n); //creates map for given variable
		SATSolver.init(n*n*n);       //Initialize the SAT solver
		Task8Encode.encode(sqrtN,hints,map);        //add the right clauses to the SAT solver
		boolean [] assignment=SATSolver.getSolution();
		if(assignment==null)
			throw new RuntimeException("time out 3 minutes");
		if (assignment.length >0) {
			int[][] board = Task9Decode.mapToBoard(map, n, assignment);			//converts the map to a soduko board
			boolean isSolution = Task5Verify.isSolution(sqrtN, hints, board);	//checks if the board is correct
			if (!isSolution)
				throw new IllegalArgumentException("The board is illegal");
			solution = board;
		}
		else solution=null;
		return solution;
	}
}
