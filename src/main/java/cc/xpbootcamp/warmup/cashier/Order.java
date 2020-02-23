package cc.xpbootcamp.warmup.cashier;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static cc.xpbootcamp.warmup.cashier.Constants.*;

@Getter
@AllArgsConstructor
public class Order {
    private List<LineItem> lineItems;
    private Date createdDate;

    private double getSum() {
        return lineItems.stream().mapToDouble(LineItem::getTotalAmount).sum();
    }

    public double getTotalPrice() {
        return getSum() + getTotalSalesTax();
    }

    public double getTotalSalesTax() {
        return getSum() * TAX;
    }

    public double getDiscount() {
        return getTotalPrice() * DISCOUNT;
    }

    public String getCreatedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.CHINA);
        return dateFormat.format(createdDate);
    }

}
