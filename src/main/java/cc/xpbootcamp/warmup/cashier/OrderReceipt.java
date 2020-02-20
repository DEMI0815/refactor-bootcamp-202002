package cc.xpbootcamp.warmup.cashier;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static cc.xpbootcamp.warmup.cashier.Constants.*;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 *
 */
public class OrderReceipt {
    private Order order;

    private double totalSalesTax = 0d;
    private double totalPrice = 0d;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        output.append(printHeader())
                .append(printLineItems(order.getLineItems()))
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

    private String printLineItems(List<LineItem> LineItems) {
        StringBuilder output = new StringBuilder();

        for (LineItem lineItem : LineItems) {
            output.append(lineItem.getDescription()).append(",\t");
            output.append(String.format(DECIMAL_FORMAT, lineItem.getPrice())).append(" x ");
            output.append(lineItem.getQuantity()).append(",\t");
            output.append(String.format(DECIMAL_FORMAT, getTotalAmount(lineItem))).append('\n');

            double salesTax = getTotalAmount(lineItem) * TAX;
            totalSalesTax += salesTax;

            totalPrice += getTotalAmount(lineItem) + salesTax;
        }
        return output.toString();
    }

    private double getTotalAmount(LineItem item) {
        return item.getPrice() * item.getQuantity();
    }

    private boolean isWednesday() {
        Date date = order.getCreatedDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.CHINA);
        return dateFormat.format(date).equals(WEDNESDAY);
    }

    private String printFooter() {
        StringBuilder output = new StringBuilder();
        double discount = (isWednesday() ? totalPrice * DISCOUNT : 0);

        output.append("-----------------------------------\n").
                append("税额:\t").append(String.format(DECIMAL_FORMAT, totalSalesTax)).append('\n')
                .append((isWednesday() ? "折扣:\t" + String.format(DECIMAL_FORMAT, discount) + '\n' : ""))
                .append("总价:\t").append(String.format(DECIMAL_FORMAT, totalPrice - discount));

        return output.toString();
    }
}