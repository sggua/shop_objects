package im.sgg.ka.jd;

/**
 * Created by sergiy on 11.03.16.
 * Java Developer lessons
 * kademika.com
 */
public class Customer {
    private String name;
    private String firstName;
    private int birthDay;
    private int birthMonth;
    private int birthYear;
    private enum Sex {NONE,MALE,FEMALE}
    private String phone;
    private String email;
    private DiscountCard discountCard;
    private Purchase[] purchases;

}
