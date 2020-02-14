package cc.xpbootcamp.warmup.cashier;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LineItem {
	private String description;
	private double price;
	private int quantity;

    public double getTotalAmount() {
        return price * quantity;
    }
}