import numpy as np
import math
import matplotlib.pyplot as plt
from numpy import long

x = np.zeros((25), dtype=int)
y = np.zeros((25), dtype=int)


# 导入数据
def init_x():
    global x
    for i in range(25):
        x[i] = i
        i += 1
    return x


def init_y():
    init_y = [14, 13, 13, 13, 13, 14, 15, 17, 19, 21, 22, 24, 27, 30, 31, 30, 28, 26, 24, 23, 21, 19, 17, 16, 15]
    global y
    y = np.asarray(init_y)
    return y


def init_s(n):
    k = 0
    s = np.zeros((2 * n + 1), dtype=np.int64)
    i = 0
    while k <= 2 * n:
        while i < 25:
            s[k] += pow(x[i], k)
            i = i + 1
        k = k + 1
        i = 0  # 初始化i进入二次循环
    return s


def init_u(n, m):
    k = 0
    u = np.zeros((n + 1), dtype=np.float64)
    i = 0
    x = init_x()
    y = init_y()
    fit_theory_function_turn()
    while k <= n:
        while i < 25:
            # m为0 是默认拟合多项式  不为零为转换多项式
            if m == 0:
                u[k] += y[i] * pow(x[i], k)
            else:
                u[k] += Y[i] * pow(x[i], k)
            i = i + 1
        k = k + 1
        i = 0  # 初始化i进入二次循环
    return u


def least_squares_method(n, m):
    u = init_u(n, m)
    s = init_s(n)
    S = np.zeros((n + 1, n + 1), dtype=np.int64)
    a = np.zeros(n, dtype=np.float64)
    plus = 0  # plus来规定矩阵的行和列来填入值
    # 将数组s转为矩阵S
    for init in range((n + 1) * (n + 1)):
        S[init // (n + 1)][init % (n + 1)] = s[init % (n + 1) + plus]
        if init % (n + 1) == n:
            plus = plus + 1  # 每次换行plus加一
    u = np.transpose(u)  # 转置矩阵
    a = np.linalg.solve(S, u)  # 使用numpy库函数快速进行矩阵运算
    return a, S


# 第四小题
# 拟合函数变换
def fit_theory_function_turn():
    # 两边同取ln进行变换
    # 拟合函数变为ln y=(ln a-b*c^2)+2*b*c*t-b*t^2 t是自变量
    global Y
    Y = np.zeros((25), dtype=float)
    y = init_y()
    for i in range(25):
        Y[i] = math.log(y[i])
    return Y


# 求解拟合函数
def solve_ft_function_turn():
    A, S = least_squares_method(2, 1)
    b = -A[2]
    c = A[1] / (2 * b)
    a = math.exp(A[0] + b * (c ** 2))
    print(A)
    print("解的原函数中a,b,c分别为", a, "\n", b, "\n", c)
    print("原函数为", "y(t)=", a, "e^(-", b, "(t-", c, ")^2）")
    return a, b, c


def print_all():
    print("初始数据点横坐标x", init_x())
    print("初始数据点纵坐标y", init_y())
    a, S = least_squares_method(2, 0)
    print("最小二乘法二次多项式进行拟合解的法方程中矩阵S \n", S)
    print("最小二乘法二次多项式进行拟合解的法方程中矩阵u \n", init_u(2, 0))
    print("最小二乘法二次多项式各项系数为 \n", a)
    print("函数表达式为", a[0], "+", a[1], "t+", a[2], "t^2", "\n")
    a, S = least_squares_method(3, 0)
    print("最小二乘法三次多项式进行拟合解的法方程中矩阵S \n", S)
    print("最小二乘法三次多项式进行拟合解的法方程中矩阵u \n", init_u(3, 0))
    print("最小二乘法三次多项式各项系数为 \n", a)
    print("函数表达式为", a[0], "+", a[1], "t+", a[2], "t^2", a[3], "t^3", "\n")
    a, S = least_squares_method(4, 0)
    print("最小二乘法四次多项式进行拟合解的法方程中矩阵S \n", S)
    print("最小二乘法四次多项式进行拟合解的法方程中矩阵u \n", init_u(4, 0))
    print("最小二乘法四次多项式各项系数为 \n", a)
    print("函数表达式为", a[0], "+", a[1], "t+", a[2], "t^2", a[3], "t^3", a[4], "t^4", "\n")


def draw_():
    x = np.arange(-5, 30)
    y = init_y()
    x_ = init_x()
    a, S = least_squares_method(2, 0)
    y_2 = a[0] + a[1] * x + a[2] * x ** 2
    a, S = least_squares_method(3, 0)
    y_3 = a[0] + a[1] * x + a[2] * x ** 2 + a[3] * x ** 3
    a, S = least_squares_method(4, 0)
    y_4 = a[0] + a[1] * x + a[2] * x ** 2 + a[3] * x ** 3 + a[4] * x ** 4
    a, b, c = solve_ft_function_turn()
    y_t = a * math.e**(-b * ((x - c) ** 2))
    plt.plot(x, y_2 ,label=" quadratic polynomial") #二次多项式
    plt.plot(x, y_3,label="	cubic polynomial")    #三次多项式
    plt.plot(x, y_4,)
    plt.plot(x, y_t,'b',label="special_fit_theory_function")  #特定拟合函数变换
    plt.scatter(x_, y)
    plt.title("输出图像对比")
    plt.legend(loc="lower center")
    plt.show()


print_all()
solve_ft_function_turn()
draw_()
