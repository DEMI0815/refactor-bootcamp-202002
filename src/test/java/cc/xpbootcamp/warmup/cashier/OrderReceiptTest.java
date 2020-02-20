package cc.xpbootcamp.warmup.cashier;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

class OrderReceiptTest {

    @Test
    public void should_print_line_item_and_sales_tax_information() throws ParseException {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("milk", 10.0, 2));
            add(new LineItem("biscuits", 5.0, 5));
            add(new LineItem("chocolate", 20.0, 1));
        }};
        OrderReceipt receipt = new OrderReceipt(new Order(lineItems, new SimpleDateFormat("yyyy-MM-dd").parse("2020-2-17")));

        String output = receipt.printReceipt();

        assertThat(output, containsString("milk,\t10.00 x 2,\t20.00\n"));
        assertThat(output, containsString("biscuits,\t5.00 x 5,\t25.00\n"));
        assertThat(output, containsString("chocolate,\t20.00 x 1,\t20.00\n"));
        assertThat(output, containsString("税额:\t6.50\n"));
        assertThat(output, containsString("总价:\t71.50"));
    }

    @Test
    public void should_print_discount_when_Wednesday() throws ParseException {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("milk", 10.0, 2));
            add(new LineItem("biscuits", 5.0, 5));
            add(new LineItem("chocolate", 20.0, 1));
        }};
        OrderReceipt receipt = new OrderReceipt(new Order(lineItems, new SimpleDateFormat("yyyy-MM-dd").parse("2020-2-19")));

        String output = receipt.printReceipt();
        System.out.println(output);

        assertThat(output, containsString("milk,\t10.00 x 2,\t20.00\n"));
        assertThat(output, containsString("biscuits,\t5.00 x 5,\t25.00\n"));
        assertThat(output, containsString("chocolate,\t20.00 x 1,\t20.00\n"));
        assertThat(output, containsString("税额:\t6.50\n"));
        assertThat(output, containsString("折扣:\t1.43\n"));
        assertThat(output, containsString("总价:\t70.07"));
    }

}