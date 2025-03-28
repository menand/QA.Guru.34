public class HomeWork6 {
    public static void main(String[] args) {
        int a=1, b=2;
        double myPi=3.1415926;

        System.out.println("Арифметические операции над int");
        System.out.println("Результат сложения a + b = " + (a + b));
        System.out.println("Результат умножения b * a = " + (b * a));

        System.out.println("Арифметические операции над int и double");
        System.out.println("Площадь круга = " +b*b*2*myPi);

        System.out.println("Логические операции");
        System.out.println("3>2 = "+(3>2));
        System.out.println("3<=2 = "+(3<=2));

        System.out.println("Пример переполнения");
        System.out.println("переполнение для int = "+(2147483647+a));
    }
}

