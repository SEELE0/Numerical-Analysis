package question_2;

import java.util.Arrays;

//高斯消去法
public class Gauss_elimination {
    static double[][] init() {//初始化
        double[][] Augmented_matrix = new double[4][5];//增广矩阵
        double[] init = {2, 4, 2, 6, 9, 4, 9, 6, 15, 23, 2, 6, 9, 18, 22, 6, 15, 18, 40, 47};
        for (int i = 0; i < 4; i++) {
            System.arraycopy(init, i * 5, Augmented_matrix[i], 0, 5);//把一维数组init中的数据五个一组复制到二维数组实现二维数组初始化
        }
        return Augmented_matrix;
    }
    double[][] turn_upper_tri_matrix() {//变上三角矩阵
        double[][] Am=init(); //增广矩阵
        int i_init=0;
        int j_init=0;
        while(i_init<4) {
            for (int i = i_init + 1; i < 4; i++) {
                double t = Am[i][j_init]; //防止Am[i][j_init]进入第二个for循环后永久变为0
                for (int j = 0; j < 5; j++) {
                    Am[i][j] = Am[i][j] - ((t / Am[i_init][j_init]) * Am[i_init][j]);
                }
            }
            i_init++;
            j_init++;
        }
        return Am;
    }
    void compute(){
        double[][]Am=turn_upper_tri_matrix();
        System.out.println(Arrays.deepToString(Am));//打印数组
        double []x=new double[4];//把结果存在数组中
        Arrays.fill(x,0);
        int i_init=3;//初始初始
        int j_init=4;//初始列数
        x[3]=Am[3][4]/Am[3][3];
        x[2]=(Am[2][4]-x[3]*Am[2][3])/Am[2][2];
        x[1]=(Am[1][4]-x[3]*Am[1][3]-x[2]*Am[1][2])/Am[1][1];
        x[0]=(Am[0][4]-x[3]*Am[0][3]-x[2]*Am[0][2]-x[1]*Am[0][1])/Am[0][0];  //这个地方可以写个for循环来优化
        System.out.println("高斯消去法结果是"+Arrays.toString(x));
    }

    public static void main(String[] args) {
        Gauss_elimination a = new Gauss_elimination();
        a.compute();
    }
}
