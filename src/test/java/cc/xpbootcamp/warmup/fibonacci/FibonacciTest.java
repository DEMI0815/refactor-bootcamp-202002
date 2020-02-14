package cc.xpbootcamp.warmup.fibonacci;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    @Test
    void should_return_1_when_calculate_given_position_is_1() {
        Fibonacci fibonacci = new Fibonacci();

        int result = fibonacci.getValueAt(1);

        assertEquals(result, 1);
    }

    @Test
    void should_return_1_when_calculate_given_position_is_2() {
        Fibonacci fibonacci = new Fibonacci();

        int result = fibonacci.getValueAt(2);

        assertEquals(result, 1);
    }

    @Test
    void should_return_2_when_calculate_given_position_is_3() {
        Fibonacci fibonacci = new Fibonacci();

        int result = fibonacci.getValueAt(3);

        assertEquals(result, 2);
    }

    @Test
    void should_return_3_when_calculate_given_position_is_4() {
        Fibonacci fibonacci = new Fibonacci();

        int result = fibonacci.getValueAt(4);

        assertEquals(result, 3);
    }

}