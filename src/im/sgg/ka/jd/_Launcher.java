package im.sgg.ka.jd;

public class _Launcher {

    public static void main(String[] args) {

        Shop shop = new Shop("Snacks & Drinks","123, 5 ave", "+1 (212) 555 6543");

        shop.addToStock(new Stock(GoodsType.SHOES,"Geox W54",    10, 1219.90));
        shop.addToStock(new Stock(GoodsType.SHOES,"Geox F12",     4, 1229.90));
        shop.addToStock(new Stock(GoodsType.SHOES,"Geox W54",     1, 1219.90));
        shop.addToStock(new Stock(GoodsType.SHOES,"Merrells 354", 3, 1349.90));
        shop.addToStock(new Stock(GoodsType.TSHIRTS,"Benetton XL",6, 589.90));
        shop.getService().printAllPrices();

        shop.addCustomer(new Customer("Rapid", "Dan", "+1 (212) 555 1231", new DiscountCard()));
        shop.addCustomer(new Customer("Brown", "Bow", "+1 (212) 555 1232", new DiscountCard()));
        shop.addCustomer(new Customer("Slippy","Joy", "+1 (212) 555 1233", new DiscountCard()));

        shop.getService().printAllStock();  shop.getService().printCashState();

        shop.sell("Benetton XL",3,shop.getCustomer().get(1));
        shop.getService().printAllStock();  shop.getService().printCashState();

        shop.sell("Geox W54",1,shop.getCustomer().get(2));
        shop.getService().printAllStock();  shop.getService().printCashState();

        shop.sell("Something wrong",1,shop.getCustomer().get(3));
        shop.getService().printAllStock();  shop.getService().printCashState();

        System.out.println(Service.dateIndex("1978-09-30"));
        System.out.println(Service.dateIndex("2016-03-14"));

        String date = Service.currDate();
        shop.getService().printSoldForDate(date);

    }
}
