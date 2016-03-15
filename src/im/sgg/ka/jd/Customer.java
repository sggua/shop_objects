package im.sgg.ka.jd;

/**
 * Created by sergiy on 11.03.16.
 * Java Developer lessons
 * kademika.com
 */
public class Customer {
    private String name;
    private String firstName;
    private String contact;

    public Customer(String name, String firstName) {
        this(name,firstName, "");
    }

    public Customer(String name, String firstName, String contact) {
        this.name = name;
        this.firstName = firstName;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
