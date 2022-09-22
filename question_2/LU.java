package question_2;

import java.util.Arrays;

class LU {

    static int MAX = 100;
    static String s = "";
    static void luDecomposition(int[][] mat, int n)  //mat是原矩阵
    {
        int[][] lower = new int[n][n];   //L矩阵
        int[][] upper = new int[n][n];   //U矩阵

        //将矩阵分解为Upper和Lower
        // 三角矩阵
        for (int i = 0; i < n; i++)
        {
            // Upper Triangular
            for (int k = i; k < n; k++)
            {
                // 求和 L(i, j) * U(j, k)
                int sum = 0;
                for (int j = 0; j < i; j++)
                    sum += (lower[i][j] * upper[j][k]);

                // 求出 U(i, k)
                upper[i][k] = mat[i][k] - sum;
            }

            // Lower Triangular
            for (int k = i; k < n; k++)
            {
                if (i == k)
                    lower[i][i] = 1; // 单位下三角矩阵对角线是  1
                else
                {
                    // Summation of L(k, j) * U(j, i)
                    int sum = 0;
                    for (int j = 0; j < i; j++)
                        sum += (lower[k][j] * upper[j][i]);

                    // Evaluating L(k, i)
                    lower[k][i]
                            = (mat[k][i] - sum) / upper[i][i];
                }
            }
        }

        // 打印输出L,U矩阵
        System.out.println(setw(2) + "     Lower Triangular"
                + setw(10) + "Upper Triangular");

        // Displaying the result :
        for (int i = 0; i < n; i++)
        {
            // Lower
            for (int j = 0; j < n; j++)
                System.out.print(setw(4) + lower[i][j]
                        + "\t");
            System.out.print("\t");

            // Upper
            for (int j = 0; j < n; j++)
                System.out.print(setw(4) + upper[i][j]
                        + "\t");
            System.out.print("\n");
        }
        double []b={9,23,22,47};
        double []y=new double[4];//把结果存在数组中
        Arrays.fill(y,0);        //Ly=b
        y[0]=b[0]/lower[0][0];
        y[1]=(b[1]-y[0]*lower[1][0])/lower[1][1];
        y[2]=(b[2]-y[0]*lower[2][0]-y[1]*lower[2][1])/lower[2][2];
        y[3]=(b[3]-y[0]*lower[3][0]-y[1]*lower[3][1]-y[2]*lower[3][2])/lower[3][3];  //这个地方可以写个for循环来优化
        double []x=new double[4];
        Arrays.fill(x,0);  //Ux=y
        x[3]=y[3]/upper[3][3];
        x[2]=(y[2]-x[3]*upper[2][3])/upper[2][2];
        x[1]=(y[1]-x[3]*upper[1][3]-x[2]*upper[1][2])/upper[1][1];
        x[0]=(y[0]-x[3]*upper[0][3]-x[2]*upper[0][2]-x[1]*upper[0][1])/upper[0][0];  //这个地方可以写个for循环来优化
        System.out.println("y="+Arrays.toString(y)+"\n");
        System.out.println("x="+Arrays.toString(x));
    }
    static String setw(int noOfSpace)
    {
        s = "";
        for (int i = 0; i < noOfSpace; i++)
            s += " ";
        return s;
    }

    // Driver code
    public static void main(String arr[])
    {
        int[][] mat = { { 2, 4, 2, 6 },
                { 4, 9, 6 ,15 },
                { 2, 6, 9 ,18 },
                {6, 15, 18, 40}};

        luDecomposition(mat, 4);
    }
}