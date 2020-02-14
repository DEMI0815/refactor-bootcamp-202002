package cc.xpbootcamp.warmup.fibonacci;

public class Fibonacci {

    public long getValueAt(int position) {
        if (position == 1)
            return 1;
        if (position == 2)
            return 1;
        return getValueAt(position - 1) + getValueAt(position - 2);
    }

}
