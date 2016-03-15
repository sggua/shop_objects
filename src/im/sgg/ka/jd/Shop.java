package im.sgg.ka.jd;

/**
 * Created by sergiy on 11.03.16.
 * Java Developer lessons
 * kademika.com
 */
public class Shop {
    private String name;
    private String address;
    private String phone;
    private Stock[] stock;
    private Sold[] sold;
    private Customer[] customer;
    private int qtyOfRecords;
    private int cash;

    public Shop() {
        this("","","");
    }

    public Shop(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.stock = new Stock[10];
        this.sold = new Sold[10];
        this.customer = new Customer[10];
        this.qtyOfRecords=0;
        this.cash=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Stock[] getStock() {
        return stock;
    }

    public void setStock(Stock[] stock) {
        this.stock = stock;
    }

    public Sold[] getSold() {
        return sold;
    }

    public void setSold(Sold[] sold) {
        this.sold = sold;
    }

    public Customer[] getCustomer() {
        return customer;
    }

    public void setCustomer(Customer[] customer) {
        this.customer = customer;
    }

    public int getQtyOfRecords() {
        return qtyOfRecords;
    }

    public void setQtyOfRecords(int qtyOfRecords) {
        this.qtyOfRecords = qtyOfRecords;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }
}
