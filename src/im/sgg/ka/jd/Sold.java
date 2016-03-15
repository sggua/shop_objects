package im.sgg.ka.jd;

/**
 * Created by sergiy on 11.03.16.
 * Java Developer lessons
 * kademika.com
 */
public class Sold extends Stock {
    private Customer customer;
    private int dateIndex;
    private int timeIndex;

    public Sold(){
    }

    public Sold(Stock stock, int quantity, int dateIndex, int timeIndex) {
        this(stock,quantity,null,dateIndex,timeIndex);
    }

    public Sold(Stock stock, int quantity, Customer customer, int dateIndex, int timeIndex) {
        super(stock.type, stock.getName(), stock.getPrice(), quantity);
        this.customer = customer;
        this.dateIndex = dateIndex;
        this.timeIndex = timeIndex;
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

    public int getTimeIndex() {
        return timeIndex;
    }

    public void setTimeIndex(int timeIndex) {
        this.timeIndex = timeIndex;
    }
}
