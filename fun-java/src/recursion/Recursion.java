package recursion;

import java.math.BigInteger;

public class Recursion {

    public static void main(String... args) {
        //fibonacciCycle(BigInteger.ZERO, BigInteger.ONE);
        fibonacciRecursion(BigInteger.ZERO, BigInteger.ONE);
    }

    public static void fibonacciRecursion(BigInteger prev, BigInteger next) {
        System.out.println(prev + "");
        fibonacciRecursion(next, prev.add(next));
    }

    public static void fibonacciCycle(BigInteger prev, BigInteger next) {
        BigInteger previousNumber = prev;
        BigInteger nextNumber = next;

        while (true) {
            System.out.println(previousNumber + " ");

            BigInteger sum = previousNumber.add(nextNumber);
            previousNumber = nextNumber;
            nextNumber = sum;
        }
    }
}
