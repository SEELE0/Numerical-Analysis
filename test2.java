package question1;

import java.math.*;

class Init {
    double a, b, n;//a 下限，b上限 ，n步长
    double h;  //步长

    Init(double a, double b, double n) {
        this.a = a;
        this.b = b;
        this.n = n;
        h = (b - a) / n;
    }

    public static double f_X(double x) {
        return Math.sqrt(x);
    }
}

class composite_T {   //复化梯形公式
    double math_T() {
        Init i = new Init(1, 9, 4);
        double x;
        double t = 0;
        for (int k = 1; k <= i.n - 1; k++) {
            x = i.a + k  * i.h;
            t += Init.f_X(x);
        }
        double T_n;
        T_n = i.h / 2 * (Init.f_X(i.a) + 2 * t + Init.f_X(i.b));
        return T_n;
    }

}

class composite_S {  //复化辛普森公式
    double math_T() {
        Init i = new Init(1, 9, 4);
        double x;
        double t = 0,t_mid = 0;
        for (int k = 1; k <= i.n - 1; k++) {
            x = i.a + (k ) * i.h;
            t += Init.f_X(x);
        }
        for (int k = 0; k <= i.n - 1; k++) {
            x = i.a + k * i.h+i.h/2;
            t_mid += Init.f_X(x);
        }
        double T_n;
        T_n = i.h / 6 * (Init.f_X(i.a) + 4*t_mid + 2 * t + Init.f_X(i.b));
        return T_n;
    }
}
class Guass_Legendre{  //两点高斯勒让德公式
    Init i =new Init(1,9,4);
    double X(double t){//因为1-9不在【-1，1】内所以要先转换
        return ((i.b-i.a)/2)*t+(i.a+i.b)/2;
    }
    double math_T(){
        double t_1=-1/Math.sqrt(3),t_2=1/Math.sqrt(3);
        return (i.b-i.a)/2*(Init.f_X(X(t_1))+Init.f_X(X(t_2)));
    }
}
public class test2 {
    public static void main(String[] args) {
//        Init ct=new Init(1,9,4);
        composite_T ct = new composite_T();
        System.out.println("复化梯形公式解的" + ct.math_T());
        composite_S cs= new composite_S();
        System.out.println("复化辛普森公式解的" + cs.math_T());
        Guass_Legendre gs=new Guass_Legendre();
        System.out.println("两点高斯-勒让德求积结果为"+gs.math_T());
    }

}
