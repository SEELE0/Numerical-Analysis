package question_1;

//简化的牛顿法
public class simplified_Newton_method {
    private double x;
    private double e_x;
    double iteration_x;
    double Derivative_fX0;

    simplified_Newton_method(double x) {
        this.x = x;
        e_x = Math.pow(Math.E, x);
        iteration_x = x - (f(x) / Derivative_f(x));  //迭代公式
        Derivative_fX0=Derivative_f(x);   //x0的导数

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
            x = iteration_x;
            e_x = Math.pow(Math.E, x);
            iteration_x = x - (f(x) / Derivative_fX0);
        }
        System.out.println("简化牛顿法的结果是"+iteration_x);
    }

    public static void main(String[] args) {
        simplified_Newton_method NM = new simplified_Newton_method(0.5);
        NM.iteration();
    }
}
