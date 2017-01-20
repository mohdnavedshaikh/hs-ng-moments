package in.hopscotch.moments.entity.helper;

import java.io.Serializable;

public class CustomerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String customerId;
    
    private String customerName;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
}
