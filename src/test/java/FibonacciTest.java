import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    Fibonacci f;

    @BeforeEach
    public void beforeMethod() {
        f = new Fibonacci();
    }

    @DisplayName("Testing fib method will be correct")
    @Test
    void fib() {
        int expected = 2;


        int actual = f.fib(3);

        assertEquals(expected, actual);
    }

    @DisplayName("Testing fib method will throw an Error")
    @Test
    void fib_throws() {
        List<Integer> testcases = Arrays.asList(-1, -10, -30, -1000);

        for (int n : testcases) {
            assertThrows(IndexOutOfBoundsException.class, () -> f.fib(n));
        }
    }
}