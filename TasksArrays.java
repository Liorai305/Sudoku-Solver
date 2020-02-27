
public class TasksArrays {
    //Liora Ifraimov ID:315389197 Last update: 1/12/17 00:15
    //the function checks if all the numbers in the array are different.
    public static boolean isAllDiff(int[] array) {
        int[] Newarray = new int[array.length];
        copy(array, Newarray);                 //copy the array, so I won't make changes in the original array.
        boolean IsDiff = true;
        int check = array.length - 1;
        while (check > 0 & IsDiff) {
            for (int i = 0; i < check & IsDiff; i = i + 1) {
                if (Newarray[i] == Newarray[i + 1])
                    IsDiff = false;
                else
                    swap(Newarray, i, i + 1);
            }
            check = check - 1; //we already checked that the rest of the numbers is different from the number in array[check]
        }
        return IsDiff;
    }
    //This function makes a copy of a given array
    public static void copy(int[] array, int[] Newarray) {
        for (int i = 0; i < array.length; i = i + 1)
            Newarray[i] = array[i];
    }
    //This function swaps between two values in an array
    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
//This function checks if all the numbers in the matrix are between min and max
    public static boolean isMatrixBetween(int[][] matrix, int n, int min, int max) {
        boolean IsRight = true;    //Incorrect קלט
        if (matrix == null)
            IsRight = false;
        else if (matrix.length != n)
            IsRight = false;
        if (min > max)
            IsRight = false;
        if (n < 0)
            IsRight = false;
        int[] Newmatrix = new int[n];
        for (int i = 0; i < n & IsRight; i = i + 1) {
            if (matrix[i] == null || matrix[i].length != n)
                IsRight = false;
            else {
                copy(matrix[i], Newmatrix);     //copies the original matrix and sort the copied matrix
                sort(Newmatrix);
                if (Newmatrix[0] < min || Newmatrix[n - 1] > max)//In a sorted array, we need to check only the edges
                    IsRight = false;
            }
        }
        return IsRight;
    }


    //sorting
    public static void sort(int[] a) {
        for (int i = 0; i < a.length - 1; i = i + 1) {
            int min = min(a, i);
            if (min != i)   //index
                swap(a, i, min);
        }

    }

    //finding min index from index i
    public static int min(int[] a, int i) {
        int min = a[i];
        int minI = i;
        for (int j = i + 1; j < a.length; j = j + 1)
            if (a[j] < min) {
                min = a[j];
                minI = j;
            }
        return minI;
    }
    //This function turns the columns into rows
    public static int[][] columns(int[][] matrix) {
        // we may assume that matrix!=null, and it's size is matrix.length*matrix.length
        int[][] matCols = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i = i + 1)
            for (int j = 0; j < matrix.length; j = j + 1)
                matCols[i][j] = matrix[j][i];

        return matCols;
    }
    //This function turns blocks into rows
    public static int[][] blocks(int[][] matrix, int sqrtN) {
        //the size of matrix is sqrtN^2*sqrtN^2
        //i assume matrix!=null, sqrtN>=2
        int[][] matBlocks = new int[matrix.length][matrix.length];
        int i = 0;
        int modulo = 0;             //line%sqrntN
        while (i < matrix.length) {
            int limit = 0;
            int n = sqrtN;         //end of the block
            int count = 0;          //start of the blocks
            while (n <= matrix.length) {
                for (int j = count; j < n; j = j + 1) {
                    if (modulo == limit)
                        matBlocks[i][j] = matrix[i][j];
                    else {
                        if (modulo == 0)
                            matBlocks[i + limit][j - (sqrtN * limit)] = matrix[i][j];
                        else {
                            if (modulo > limit)
                                matBlocks[i - modulo + limit][j + (sqrtN * (modulo - limit))] = matrix[i][j];
                            else
                                matBlocks[i - modulo + limit][j - (sqrtN * modulo)] = matrix[i][j];
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

    }