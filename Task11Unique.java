
public class Task11Unique {
    //Liora Ifraimov ID:315389197 Last update: 1/12/17 00:15
    //This function checks if a given sudoko has only one solution
    public static int[][] solveUnique(int sqrtN, int[][] hints) {
        int[][] solution;
        int n = sqrtN * sqrtN;
        int[][][] map = Task7Map.varsMap(n);        //creates map for given variable
        SATSolver.init(n * n * n);              //Initialize the SAT solver
        Task8Encode.encode(sqrtN, hints, map);          //add the right clauses to the SAT solver
        boolean[] assignment = SATSolver.getSolution();
        if (assignment == null)
            throw new RuntimeException("time out 3 minutes");
        if (assignment.length == 0)
            throw new IllegalArgumentException("There is no assignment for the soduko");
        int[][] board = Task9Decode.mapToBoard(map, n, assignment);         //converts the map to a soduko board
        boolean isSolution = Task5Verify.isSolution(sqrtN, hints, board);   //checks if the board is correct
        if (!isSolution)
            throw new IllegalArgumentException("The board is illegal");
        SATSolver.init(n*n*n);              //Initialize again the SAT solver(the SAT solver "closed" after "getsolution())
        Task8Encode.encode(sqrtN, hints, map);      //add the right clauses to the SAT solver
        solution = board;
        AddSolution(sqrtN,board,map);               //add solution to the SAT solver
        boolean[] assignment2 = SATSolver.getSolution();
        if (assignment2 == null)
            throw new RuntimeException("time out 3 minutes");
        if(assignment2.length!=0)  //if there is another solution to the sudoko return null
            solution=null;
        return solution;
    }
    // This function adds solution to the SAT solver to make sure that the solution from
    // SAT will be different from the previous solution
    public static void AddSolution(int sqrntN, int[][] board,int [][][] map) {
        int [] vars=new int[board.length*board.length];
        int Index=0;
        for(int i=0; i<board.length; i=i+1)
            for(int j=0;j<board[i].length;j=j+1){
                int cnfV=Task7Map.varName(i,j,board[i][j]-1,sqrntN*sqrntN);
                vars[Index]=cnfV*-1;  //its enough that one variable will be different
                Index=Index+1;
            }
        SATSolver.addClause(vars);
    }
}