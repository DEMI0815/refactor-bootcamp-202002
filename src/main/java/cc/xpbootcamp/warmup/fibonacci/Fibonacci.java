package cc.xpbootcamp.warmup.fibonacci;

public class Fibonacci {

    public long getValueAt(int position) {
        long firstNum = 1;
        long secondNum = 1;
        long result = 1;

        if (position <= 2)
            return result;

        for (int i = 3; i <= position; i++) {
            result = firstNum + secondNum;
            firstNum = secondNum;
            secondNum = result;
        }
        return result;
    }

}
