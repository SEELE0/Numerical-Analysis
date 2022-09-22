package question_2;



// Implementing Gauss Seidel Method in Java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class GFG {
    // 设置最大迭代次数以防止无限循环
    public static final int MAX_ITERATIONS = 100;
    private double[][] M;
    public GFG(double[][] matrix) { M = matrix; }

    public void print() // printing
    {
        int n = M.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++)
                System.out.print(M[i][j] + " ");
            System.out.println();
        }
    }

    // 满足所有条件后求矩阵解的方法
    public void solve()
    {
        int iterations = 0;
        int n = M.length;
        double epsilon = 1e-5;
        double[] X = new double[n]; // Approximations
        double[] P = new double[n]; // Prev
        Arrays.fill(X, 0);
        while (true) {
            for (int i = 0; i < n; i++) {
                double sum = M[i][n]; // b_n
                for (int j = 0; j < n; j++)
                    if (j != i)
                        sum -= M[i][j] * X[j];
                // 更新 xi 以用于下一行计算
                X[i] = 1 / M[i][i] * sum;
            }
            System.out.print("X" + iterations + " = {");
            for (int i = 0; i < n; i++)
                System.out.print(X[i] + " ");
            System.out.println("}");
            iterations++;
            if (iterations == 1)
                continue;
            boolean stop = true;
            for (int i = 0; i < n && stop; i++)
                if (Math.sqrt(Math.pow((X[i] - P[i]),2)) > epsilon)
                    stop = false;
            if (stop || iterations == MAX_ITERATIONS)
                break;
            P = (double[])X.clone();
        }
    }

    public static void main(String[] args)
            throws IOException
    {
        PrintWriter writer
                = new PrintWriter(System.out, true);
        int n = 2, k = 1;
        double[][] M = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++)
                M[i][j] = k++;
        }
        GFG gausSeidel = new GFG(M);
        writer.println();
        gausSeidel.print();
        gausSeidel.solve();
    }
}