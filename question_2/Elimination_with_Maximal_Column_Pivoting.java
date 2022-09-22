package question_2;

import java.util.Arrays;

//列主元高斯消去法
public class Elimination_with_Maximal_Column_Pivoting {
    public  static  double[][] Am = new double[4][5];//增广矩阵
    static double[][] init() {//初始化
        double[] init = {2, 4, 2, 6, 9, 4, 9, 6, 15, 23, 2, 6, 9, 18, 22, 6, 15, 18, 40, 47};
        for (int i = 0; i < 4; i++) {
            System.arraycopy(init, i * 5, Am[i], 0, 5);//五个一组复制到二维数组实现二维数组初始化
        }
        return Am;
    }
    // 选第k列的主元
    private void sort(int k) {
        double maxVal = Math.abs(Am[k][k]);    //最大元素值
        int maxpivo = k;  //所对应的下标记录当前运算选取主元更换的行
        for (int i = k + 1; i < 4; ++i) {// 查找值最大的行下标
            double val = Math.abs(Am[i][k]);
            if (val > maxVal) {
                maxVal = val;
                maxpivo = i;
            }
        }
        if(maxpivo !=k){
            for(int j = k; j < 5; j++) {
                double t=Am[k][j];
                Am[k][j]=Am[maxpivo][j];
                Am[maxpivo][j]=t;
            }
        }
//        System.out.println(Arrays.deepToString(Am));
    }



    double[][] turn_upper_tri_matrix() {//变上三角矩阵
        init();
        int i_init = 0;
        int j_init = 0;
        while (i_init < 4) { //总共运算四轮
            sort(i_init);
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
        double[][] Am = turn_upper_tri_matrix();
        System.out.println("列主元后行列式为"+Arrays.deepToString(Am));//打印数组
        double[] x = new double[4];//把结果存在数组中
        Arrays.fill(x, 0);
        x[3] = Am[3][4] / Am[3][3];
        x[2] = (Am[2][4] - x[3] * Am[2][3]) / Am[2][2];
        x[1] = (Am[1][4] - x[3] * Am[1][3] - x[2] * Am[1][2]) / Am[1][1];
        x[0] = (Am[0][4] - x[3] * Am[0][3] - x[2] * Am[0][2] - x[1] * Am[0][1]) / Am[0][0];  //这个地方可以写个for循环来优化
        System.out.println("列主元高斯消去法结果是" + Arrays.toString(x));
    }

    public static void main(String[] args) {
        Elimination_with_Maximal_Column_Pivoting ewmcp=new Elimination_with_Maximal_Column_Pivoting();
        ewmcp.compute();
    }
}
