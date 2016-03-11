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
    private Goods[] goods;
    private Customer[] customer;
    private DiscountCard[] discountCard;

    public Shop() {
        this("","","");
    }

    public Shop(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
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

    public Goods[] getGoods() {
        return goods;
    }

    public void setGoods(Goods[] goods) {
        this.goods = goods;
    }

    public Customer[] getCustomer() {
        return customer;
    }

    public void setCustomer(Customer[] customer) {
        this.customer = customer;
    }

    public void setDiscountCard(DiscountCard[] discountCard) {
        this.discountCard = discountCard;
    }

    public DiscountCard[] getDiscountCard() {
        return discountCard;
    }

}
