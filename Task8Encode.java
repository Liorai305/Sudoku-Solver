public class Task8Encode {
	//Liora Ifraimov ID:315389197 Last update: 1/12/17 00:15
	//This function adds clauses to the SAT solver with the right restrictions
	public static void encode(int sqrtN, int[][] hints, int[][][] map){
		AddRows(sqrtN,map);        //adds restrictions on rows
		AddColumns(sqrtN,map);		//adds restrictions on columns
		AddBlocks(sqrtN,map);		//adds restrictions on blocks
		AddCells(sqrtN,map);		//adds restrictions on cells
		AddHints(hints,map);		//adds the hints
	}
	//This function adds clauses to the SAT solver with restrictions on cells
    public static void AddCells(int sqrtN,int [][][] map) {
    for(int i=0;i<map.length;i=i+1)
        for(int j=0;j<map.length;j=j+1)
            SATSolver.addClauses(Task6Cnf.exactlyOne(map[i][j]));

    }
	//This function adds clauses to the SAT solver with restrictions on rows
	public static void AddRows(int sqrtN,int [][][] map){
            for (int i = 0; i < map.length; i = i + 1) {
                for (int k = 0; k < sqrtN * sqrtN; k = k + 1) {
                    int[] vars = new int[sqrtN * sqrtN];
                    for (int j = 0; j < map.length; j = j + 1)
                        vars[j] = map[i][j][k];
                    SATSolver.addClauses(Task6Cnf.exactlyOne(vars));
                }
            }
        }
	//This function adds clauses to the SAT solver with restrictions on columns
	public static void AddColumns(int sqrtN, int [][][] map){
		int [][][] newmapC=columns(sqrtN,map);   //turns columns into rows
		AddRows(sqrtN,newmapC);
	}
	//This function turns columns into rows(same as TaskArrays only in 3D)
	public static int[][][] columns(int sqrntN, int[][][] map) {
		// we may assume that matrix!=null, and it's size is matrix.length*matrix.length
		int[][][] matCols = new int[map.length][map.length][sqrntN*sqrntN];
		for (int i = 0; i < map.length; i = i + 1)
			for (int j = 0; j < map.length; j = j + 1)
				matCols[i][j] = map[j][i];
		return matCols;
	}
	//This function adds clauses to the SAT solver with restrictions on blocks
	 public static void AddBlocks(int sqrntN, int[][][] map){
		int [][][] newmapB=Blocks(sqrntN,map);
		AddRows(sqrntN,newmapB);
	 }
	//This function turns blocks into rows(same as TaskArrays only in 3D)
	public static int[][][] Blocks(int sqrtN,int [][][] map) {
		//the size of matrix is sqrtN^2*sqrtN^2
		//i assume matrix!=null, sqrtN>=2
		int[][][] matBlocks = new int[map.length][map.length][sqrtN*sqrtN];
		int i = 0;
		int modulo = 0;
		while (i < map.length) {
			int limit = 0;
			int n = sqrtN;
			int count = 0;
			while (n <= map.length) {
				for (int j = count; j < n; j = j + 1) {
					if (modulo == limit)
						matBlocks[i][j] = map[i][j];
					else {
						if (modulo == 0)
							matBlocks[i + limit][j - (sqrtN * limit)] = map[i][j];
						else {
							if (modulo > limit)
								matBlocks[i - modulo + limit][j + (sqrtN * (modulo - limit))] = map[i][j];
							else
								matBlocks[i - modulo + limit][j - (sqrtN * modulo)] = map[i][j];
						}
					}
				}
				count = count + sqrtN;
				n = n + sqrtN;
				limit = limit + 1;
			}

			i = i + 1;
			modulo = i % sqrtN;
		}

		return matBlocks;
	}
	//This function add hints to the SAT solver
	public static void AddHints(int [][] hints,int [][][] map){
	     int [][] vars=new int[hints.length][1];
	     for(int i=0; i<hints.length; i=i+1)
             vars[i][0]=map[hints[i][0]][hints[i][1]][hints[i][2]-1];
        SATSolver.addClauses(vars);


}
}
