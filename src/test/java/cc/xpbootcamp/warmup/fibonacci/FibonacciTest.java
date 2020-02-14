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

}