package cc.xpbootcamp.warmup.cashier;

import java.util.List;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 *
 */
public class OrderReceipt {
    private Order order;
    private static final String ORDER_HEADER = "======Printing Orders======\n";

    double totalSalesTax = 0d;
    double totalPrice = 0d;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        return ORDER_HEADER +
                order.getCustomerName() +
                order.getCustomerAddress() +
                printLineItems(order.getLineItems()) +
                "Sales Tax" + '\t' + totalSalesTax +
                "Total Amount" + '\t' + totalPrice;
    }

    private String printLineItems(List<LineItem> LineItems) {
        StringBuilder output = new StringBuilder();

        for (LineItem lineItem : LineItems) {
            output.append(lineItem.getDescription());
            output.append('\t');
            output.append(lineItem.getPrice());
            output.append('\t');
            output.append(lineItem.getQuantity());
            output.append('\t');
            output.append(lineItem.totalAmount());
            output.append('\n');

            double salesTax = lineItem.totalAmount() * .10;
            totalSalesTax += salesTax;

            totalPrice += lineItem.totalAmount() + salesTax;
        }
        return output.toString();
    }
}