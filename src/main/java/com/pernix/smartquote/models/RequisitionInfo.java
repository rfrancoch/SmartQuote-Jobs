package main.java.com.pernix.smartquote.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class RequisitionInfo {

    private int requisition_id;
    private String description;
    private String shipping_address;
    private double quantity;
    private double base_amount;
    private Date limit_date;
    private String title;
    private String email;

    public RequisitionInfo() {
    }

    public RequisitionInfo(int requisition_id, String description, String shipping_address, double quantity, double base_amount, Date limit_date, String title, String email) {
        this.description = description;
        this.shipping_address = shipping_address;
        this.quantity = quantity;
        this.base_amount = base_amount;
        this.limit_date = limit_date;
        this.title = title;
        this.email = email;
        this.requisition_id = requisition_id;
    }


    public int getRequisition_id() {
        return requisition_id;
    }

    public void setRequisition_id(int requisition_id) {
        this.requisition_id = requisition_id;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    @XmlElement
    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getDescription() {
        return description;
    }
    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public double getQuantity() {
        return quantity;
    }

    @XmlElement
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getBase_amount() {
        return base_amount;
    }

    @XmlElement
    public void setBase_amount(double base_amount) {
        this.base_amount = base_amount;
    }

    public Date getLimit_date() {
        return limit_date;
    }

    @XmlElement
    public void setLimit_date(Date limit_date) {
        this.limit_date = limit_date;
    }

    public String getTitle() {
        return title;
    }

    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }
}
