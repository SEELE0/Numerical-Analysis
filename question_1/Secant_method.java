package question_1;

//割线法
public class Secant_method {
    private double x;
    private double x_plus;
    double iteration_x;  //迭代公式 X  K+1
//取x是X0=0.5 x_plus是x1=0.51
    Secant_method(double x ,double x_plus) {
        this.x = x;
        this.x_plus=x_plus;
        iteration_x = x_plus - (f(x_plus) / (f(x_plus)-f(x)))*(x_plus-x);  //迭代公式 初次解的X2
    }
    double e_x(double x){
        return Math.pow(Math.E, x);
    }

    double f(double x) { //原函数

        return x * e_x(x) - 1;
    }

    void iteration() {
        while (Math.abs(iteration_x - x_plus) >= 1e-6)
        {
            x=x_plus;
            x_plus = iteration_x;   //x 下标K  初次为X2

            iteration_x = x_plus - (f(x_plus) /(f(x_plus)-f(x)))*(x_plus-x);  // X下标K+1   初次解的X3
        }
        System.out.println("割线法的结果是"+iteration_x);
    }

    public static void main(String[] args) {
        Secant_method SM = new Secant_method(0.5,0.6); //取x0是0.5 x1 是0.51
        SM.iteration();
    }
}