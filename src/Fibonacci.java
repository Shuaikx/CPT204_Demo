public class Fibonacci {
    private static int steps = 0;
    public static  int fib1(int index){ // Recursive Fib
        steps ++;
        if (index == 0) return 0;
        else if (index == 1) return 1;
        else return fib1(index - 1) + fib1(index - 2);
    }

    public static int fib2(int index){// Non-recursive Fib
        if(index == 0) return 0;
        else if (index == 1 || index == 2) return 1;
        int f0 = 0;
        int f1 = 1;
        int f2 = 1;
        steps = 3;
        for(int i = 3; i<=index; i++){
            steps ++;
            f0 = f1;
            f1 = f2;
            f2 = f0 + f1;
        }
        return f2;
    }

    public static void main(String[] args){
        int input = 20;
        int result = fib1(input);
        System.out.println("Fib(" + input + "): " + result + " with steps:" + steps);
    }
}
