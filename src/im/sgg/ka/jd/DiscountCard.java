package im.sgg.ka.jd;

/**
 * Created by sergiy on 11.03.16.
 * Java Developer lessons
 * kademika.com
 */
public class DiscountCard {
    private int number;
    private int discountSize;
    private static Shop shop;

    public DiscountCard() {
        this(shop.getDiscountCard().length,0);
    }

    public DiscountCard(int number, int discountSize) {
        this.number = number;
        this.discountSize = discountSize;
    }
}

