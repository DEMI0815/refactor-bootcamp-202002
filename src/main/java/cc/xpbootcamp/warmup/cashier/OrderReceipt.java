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

    private static final String ORDER_HEADER = "===== 老王超市，值得信赖 ======\n";
    private static final double TAX = .10;
    private static final String FORMAT = "%.2f";

    private double totalSalesTax = 0d;
    private double totalPrice = 0d;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        return ORDER_HEADER +
                printCustomerInfo() +
                printLineItems(order.getLineItems()) +
                printFooter();
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
            output.append(String.format(FORMAT, lineItem.getPrice())).append(" x ");
            output.append(lineItem.getQuantity()).append(",\t");
            output.append(String.format(FORMAT, getTotalAmount(lineItem))).append('\n');

            double salesTax = getTotalAmount(lineItem) * TAX;
            totalSalesTax += salesTax;

            totalPrice += getTotalAmount(lineItem) + salesTax;
        }
        return output.toString();
    }

    private double getTotalAmount(LineItem item) {
        return item.getPrice() * item.getQuantity();
    }

    private String printFooter() {
        return "-----------------------------------\n" +
               "税额:\t" + String.format(FORMAT, totalSalesTax) + '\n' +
               "总价:\t" + String.format(FORMAT, totalPrice);
    }
}