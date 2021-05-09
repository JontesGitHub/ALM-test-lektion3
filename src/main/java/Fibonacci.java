public class Fibonacci {

    public int fib(int n) {
        if (n < 0) {
            throw new IndexOutOfBoundsException("Could not get fib of: " + n);
        }
        if (n == 1 || n == 0) {
            return n;
        }

        return fib(n -1) + fib(n -2);
    }
}
