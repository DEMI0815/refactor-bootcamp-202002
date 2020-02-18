package cc.xpbootcamp.warmup.cashier;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Order {
    private String customerName;
    private String customerAddress;
    private List<LineItem> lineItems;
    private Date createdDate;
}
