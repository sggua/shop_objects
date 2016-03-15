package im.sgg.ka.jd;

public class _Launcher {

    public static void main(String[] args) {

        Shop shop = new Shop("Snacks & Drinks","123, 5 ave", "+1 (212) 555 6543");
        Service srv = new Service(shop);

        srv.addToStock(new Stock(GoodsType.SHOES,"Geox W54",     1219.90,10));
        srv.addToStock(new Stock(GoodsType.SHOES,"Geox F12",     1229.90, 4));
        srv.addToStock(new Stock(GoodsType.SHOES,"Geox W54",     1219.90, 1));
        srv.addToStock(new Stock(GoodsType.SHOES,"Merrells 354", 1349.90, 3));
        srv.addToStock(new Stock(GoodsType.TSHIRTS,"Benetton XL", 589.90, 6));
        srv.printAllPrices();

        srv.addCustomer(new Customer("Rapid", "Dan", "+1 (212) 555 1231"));
        srv.addCustomer(new Customer("Brown", "Bow", "+1 (212) 555 1232"));
        srv.addCustomer(new Customer("Slippy","Joy", "+1 (212) 555 1233"));

        srv.printAllStock();  srv.printCashState();

        srv.sell("Benetton XL",3,srv.getCustomer().get(1));
        srv.printAllStock();  srv.printCashState();

        srv.sell("Geox W54",1,srv.getCustomer().get(2));
        srv.printAllStock();  srv.printCashState();

        srv.sell("Something wrong",1,srv.getCustomer().get(3));
        srv.printAllStock();  srv.printCashState();

        System.out.println(Service.dateIndex("1978-09-30"));
        System.out.println(Service.dateIndex("2016-03-14"));

        String date = Service.currDate();
        srv.printSoldForDate(date);

    }
}
