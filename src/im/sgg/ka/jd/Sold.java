package im.sgg.ka.jd;

/**
 * Created by sergiy on 11.03.16.
 * Java Developer lessons
 * kademika.com
 */
public class Sold extends Goods {
    private Customer customer;
    private int dateIndex;

    public Sold(){
    }

    public Sold(Customer customer, int dateIndex) {
        this.customer = customer;
        this.dateIndex = dateIndex;
    }

    public Sold(GoodsType type, String name, Customer customer, int dateIndex) {
        super(type, name);
        this.customer = customer;
        this.dateIndex = dateIndex;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getDateIndex() {
        return dateIndex;
    }

    public void setDateIndex(int dateIndex) {
        this.dateIndex = dateIndex;
    }
}
