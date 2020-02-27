
public class Task7Map {
	//Liora Ifraimov ID:315389197 Last update: 1/12/17 00:15
	//calculates variable name with a given function
	public static int varName(int i, int j, int k, int n) {
		// we may assume that the input 0<i,j,k<n
		int Varname=n*n*i+(n*j)+k+1;
		return Varname;
	}
	//This function calculates i,j,k for a given variable name and n
	public static int[] nameToIndex(int x, int n) {
		int i=0,k=0,j=0;
		boolean NoAns=false;
			for(int s=0;s<n&!NoAns;s=s+1){
				i=0;
				for(int m=0;m<n&!NoAns;m=m+1) {
					k = x - 1 - (n * n * i) - n * j;
					if (n * n * i + n * j + k + 1 == x&k<n&0<=k)
						NoAns = true;
					else
						i = i + 1;
				}
				if(!NoAns)
					j=j+1;

		}

		return new int[]{i, j, k};
	}
	//This function creates a map for cnf variables
	public static int[][][] varsMap(int n) {
		int [][][] map=new int [n][n][n];   //There are n*n*n variables
		for(int i=0; i<n; i=i+1)
			for(int j=0; j<n; j=j+1)
				for(int k=0; k<n; k=k+1)
					map[i][j][k]=varName(i, j, k, n);
		return map ;
	}
}
