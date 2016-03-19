package im.sgg.ka.jd;

public class _Launcher {

    public static void main(String[] args) {

        Shop shop = new Shop("Shoes & Clothes","123, 5 ave", "+1 (212) 555 6543");
        Service srv = new Service(shop);

        srv.addToStock(new Stock(GoodsType.SHOES,  "Geox No. W54", 1219.90,20));
        srv.addToStock(new Stock(GoodsType.SHOES,  "Geox No. F12", 1229.90,15));
        srv.addToStock(new Stock(GoodsType.SHOES,  "Geox No. W54", 1219.90,11));
        srv.addToStock(new Stock(GoodsType.SHOES,  "Merrells 354", 1349.90, 3));
        srv.addToStock(new Stock(GoodsType.TSHIRTS,"Benetton XL",   589.90, 6));
        srv.printAllPrices();
        srv.printAllStock();

//        srv.addCustomer(new Customer("Rapid", "Dan", "+1 (212) 555 1231"));
//        srv.addCustomer(new Customer("Brown", "Bow", "+1 (212) 555 1232"));
//        srv.addCustomer(new Customer("Slippy","Joy", "+1 (212) 555 1233"));
//
//        srv.printAllStock();  srv.printCashState();
//
        srv.sell("Benetton XL", 1,"2016-03-10","10:10:10", new Customer("Rapid", "Dan", "+1 (212) 555 1231"));
        srv.sell("Benetton XL", 1,"2016-03-11","11:11:11", new Customer("Rapid", "Dan", "+1 (212) 555 1231"));
        srv.sell("Benetton XL", 1,"2016-03-12","12:12:12", new Customer("Rapid", "Dan", "+1 (212) 555 1231"));
        srv.sell("Geox No. W54",1,"2016-03-13","13:13:13", new Customer("Brown", "Bow", "+1 (212) 555 1232"));
        srv.sell("Geox No. W54",2,"2016-03-14","14:14:14", new Customer("Brown", "Bow", "+1 (212) 555 1232"));
        srv.sell("Geox No. W54",2,"2016-03-15","15:15:15", new Customer("Slippy","Joy", "+1 (212) 555 1233"));
        srv.sell("Geox No. W54",2,"2016-03-16","16:16:16", new Customer("Slippy","Joy", "+1 (212) 555 1233"));
        srv.sell("Geox No. W54",2,"2016-03-17","17:17:17", new Customer("Brown", "Bow", "+1 (212) 555 1232"));
        srv.sell("Geox No. W54",2,"2016-03-18","18:18:18", new Customer("Rapid", "Dan", "+1 (212) 555 1231"));
        srv.sell("Geox No. W54",2,"2016-03-19","19:19:19", new Customer("Rapid", "Dan", "+1 (212) 555 1231"));
        srv.sell("Geox No. W54",2,"2016-03-20","20:20:20", new Customer("Rapid", "Dan", "+1 (212) 555 1231"));

        srv.sell("Something wrong",1,"2016-03-10","10:10:10",new Customer("Slippy","Joy", "+1 (212) 555 1233"));
        srv.printAllStock();  srv.printCashState();

        srv.printSoldForLast ( 1);
        srv.printSoldForLast ( 3);
        srv.printSoldForLast ( 5);
        srv.printSoldForLast ( 7);
        srv.printSoldForLast (14);

        srv.printSoldForDate(Service.currDate());


//        System.out.println(Service.dateIndex("1978-09-30"));
//        System.out.println(Service.dateIndex("2016-03-14"));
//
//        String date = Service.currDate();
//        System.out.println(date+" - "+Service.dateIndex(date)+" - "+Service.dateIndexToString(Service.dateIndex(date)));
//        String time = Service.currTime();
//        System.out.println(time+" - "+Service.timeIndex(time)+" - "+Service.timeIndexToString(Service.timeIndex(time)));


    }
}
