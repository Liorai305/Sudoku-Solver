
public class Task6Cnf {
    //Liora Ifraimov ID:315389197 Last update: 1/12/17 00:15
    //creates clause that makes sure that at least one variable is true
    public static int[][] atLeastOne(int[] vars) {
		int[][] formula=new int[1][vars.length];
		for(int i=0;i<vars.length; i=i+1){
            formula[0][i]=vars[i];              //sends all the variables without changes

        }
        return formula;
	}

    //this function makes sure that at most one variable gets true value
    public static int[][] atMostOne(int[] vars) {
        int[][] formula=new int[LengthF(vars.length)][2]; //the length of the formula is the amount of the couples
        int comb=vars.length-1;
        int k,i=0,count;
        for(int j=0; j<vars.length&comb>0; j=j+1){
            k=j;
            count=comb;
            while(count>0){
                    formula[i][0]=vars[j]*-1;
                    formula[i][1]=vars[k+1]*-1;//creates couples (-x,-y)
                k=k+1;
                i=i+1;
                count=count-1;
            }
            comb=comb-1;
        }
        return formula;
    }
    //This function calculates the number of the couples from a given number of variables
    public static int LengthF(int a){
        int output;
        if(a==2)
            output=1;
        else
            output=a-1+LengthF(a-1);
        return output;
    }
	//This function makes sure that exactly one variable gets the value true.
    public static int[][] exactlyOne(int[] vars) {
		int[][] atleast=atLeastOne(vars);
		int[][] atmost=atMostOne(vars);
		int[][] exactly=combine(atmost, atleast); //combines between the two arrays
		return exactly ;
	}
    //This function combines between two arrays with different length
	public static int[][] combine(int[][] atmost, int[][] atleast) {
        int[][] combine = new int[atmost.length + 1][];
        for(int i=0;i<combine.length;i=i+1){
            if(i==atmost.length)
                combine[i]=atleast[0];
            else
                combine[i]=atmost[i];
        }
    return combine;
    }

}
