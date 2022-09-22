package question_1;
//牛顿下山法
public class Newton_down_hill_method {

    private double x;
    private double e_x;
    double iteration_x;
    double r=1; //下山因子

    Newton_down_hill_method(double x) {
        this.x = x;
        e_x = Math.pow(Math.E, x);
        iteration_x = x - (f(x) / Derivative_f(x));  //迭代公式
    }
    double f(double x) { //原函数

        return x * e_x - 1;
    }

    double Derivative_f(double x) {  //导函数
        return e_x + x * e_x;
    }

    void iteration() {
        while (Math.abs(iteration_x - x) >= 1e-6)
        {
            r=1;
            x = iteration_x;
            e_x = Math.pow(Math.E, x);
            iteration_x = x - r*(f(x) / Derivative_f(x));
            while(Math.abs(f(x))<=Math.abs(f(iteration_x))){
                r=r/2;
                iteration_x=x-r*(f(x)/Derivative_f(x));
            }
        }
        System.out.println("牛顿下山法的结果是"+iteration_x);
    }

    public static void main(String[] args) {
        Newton_down_hill_method NM = new Newton_down_hill_method(0.5);
        NM.iteration();
    }
}
