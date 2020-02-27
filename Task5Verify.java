
public class Task5Verify {
	//Liora Ifraimov ID:315389197 Last update: 1/12/17 00:15
	//This function checks if the sudoko board is correct.
	public static boolean isSolution(int sqrtN, int[][] hints, int[][] board) {
		boolean GoodAns=true;
		if(board==null||board.length!=sqrtN*sqrtN)       //checks if the board is null or not in the right size
			throw new IllegalArgumentException("The board is incorect");
		for(int i=0; i<board.length;i=i+1)				//checks if every cell in the board is null or not in the right size
			if(board[i]==null||board[i].length!= sqrtN*sqrtN)
				throw new IllegalArgumentException("The board is incorect");
			else
				for(int j=0; j<board[i].length; j=j+1)  //checks if all the numbers in the array are between 1 and sqrntN*sqrntN
					if(board[i][j]>sqrtN*sqrtN&board[i][j]<1)
						throw new IllegalArgumentException("The board is incorect");
		for(int j=0; j<hints.length;j=j+1)                     //checks if the hints are included in the board
			if(board[hints[j][0]][hints[j][1]]!=hints[j][2])
				GoodAns=false;
		for(int i=0;i<board.length&GoodAns; i=i+1)      //every line isdiff
			GoodAns = TasksArrays.isAllDiff(board[i]);
		int[][] brdCols=TasksArrays.columns(board);                    //turn every column into line
		for(int j=0;j<brdCols.length&GoodAns; j=j+1)    //every column isdiff
			GoodAns =TasksArrays.isAllDiff(brdCols[j]);
		int[][] brdblocks=TasksArrays.blocks(board,sqrtN);                //every block into column
		for(int k=0;k<brdblocks.length&GoodAns; k=k+1)
			GoodAns =TasksArrays.isAllDiff(brdblocks[k]);
		return GoodAns ;
	}
}

