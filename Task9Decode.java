
public class Task9Decode {
	//Liora Ifraimov ID:315389197 Last update: 1/12/17 00:15
	//This function gives value to a specific cell in a board([i][j])
	public static int cellValue(int[][][] map, int i, int j, boolean[] assignment) {
		int value=0;
		int Index;
		for(int k=0; k<map[i][j].length&value==0;k=k+1){
			Index=map[i][j][k];
			if(assignment[Index])        //if the SAT solver gives true to this variable
			    value=k+1;
		}
		if(value==0)       					//If there isn't any value to the cell, throw exception
			throw new IllegalArgumentException("EROOR");
		return value;
	}
	//This function gives value to every cell of a board (using map)
	public static int[][] mapToBoard(int[][][] map, int n, boolean[] assignment) {
		int [][] BoardM=new int [n][n];
		for(int i=0;i<map.length;i=i+1)
		    for(int j=0;j<map.length;j=j+1){
		        BoardM[i][j]=cellValue(map,i,j,assignment); //value to a specific cell
            }
		return BoardM ;
	}
}
