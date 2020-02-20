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
        return ORDER_HEADER +
                printDate() +
                printCustomerInfo() +
                printLineItems(order.getLineItems()) +
                printFooter();
    }

    private String printDate() {
        Date date = order.getCreatedDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.CHINA);
        return dateFormat.format(date);
    }

    private String printCustomerInfo() {
        String result = "";
        if (order.getCustomerName() != null)
            result += order.getCustomerName();
        if (order.getCustomerAddress() != null)
            result += order.getCustomerAddress();
        return result;
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
        double discount = (isWednesday() ? totalPrice * DISCOUNT : 0);

        return "-----------------------------------\n" +
               "税额:\t" + String.format(DECIMAL_FORMAT, totalSalesTax) + '\n' +
               (isWednesday() ? "折扣:\t" + String.format(DECIMAL_FORMAT, discount) + '\n' : "") +
               "总价:\t" + String.format(DECIMAL_FORMAT, totalPrice - discount);
    }
}