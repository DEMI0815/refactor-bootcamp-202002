package cc.xpbootcamp.warmup.cashier;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static cc.xpbootcamp.warmup.cashier.Constants.*;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        output.append(printHeader())
                .append(printLineItems())
                .append(printFooter());
        return output.toString();
    }

    private String printHeader() {
        return ORDER_HEADER + printDate();
    }

    private String printDate() {
        Date date = order.getCreatedDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.CHINA);
        return dateFormat.format(date);
    }

    private String printLineItems() {
        StringBuilder output = new StringBuilder();
        order.getLineItems().forEach(item -> output.append(String.format("%s,\t%.2f x %d,\t%.2f\n",
                item.getDescription(), item.getPrice(), item.getQuantity(), item.getTotalAmount())));

        return output.toString();
    }

    private boolean isWednesday() {
        Date date = order.getCreatedDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.CHINA);
        return dateFormat.format(date).equals(WEDNESDAY);
    }

    private String formatPriceInformation(String title, double price) {
        return String.format("%s:\t%.2f\n", title, price);
    }

    private String printFooter() {
        StringBuilder output = new StringBuilder();
        double discount = (isWednesday() ? order.getDiscount() : 0);

        output.append(DIVIDER)
                .append(formatPriceInformation("税额", order.getTotalSalesTax()))
                .append(isWednesday() ? formatPriceInformation("折扣", discount) : "")
                .append(formatPriceInformation("总价", order.getTotalPrice() - discount));

        return output.toString();
    }
}