package im.sgg.ka.jd;

import java.util.Calendar;

/**
 * Created by sergiy on 15.03.16.
 * Java Developer lessons
 * kademika.com
 */
public class Service {
    private Shop shop;

    public Service() {
    }

    public Service(Shop shop) {
        this.shop = shop;
    }

    public long getNewSku() {
        shop.setLastSKU(shop.getLastSKU() + 1);
        return shop.getLastSKU();
    }

    public double getPrice(int sku) {
        return getPrice(shop.getStock().get(sku));
    }

    public double getPrice(Stock g) {
        return g.getPrice();
    }

    public String getName(int sku) {
        return getName(shop.getStock().get(sku));
    }

    public String getName(Stock s) {
        return s.getName();
    }

    public void printAllStock(){
        System.out.println();
        for (Stock s : this.shop.getStock())
            if (goodsOk(s)) {
                printStock(s);
            }

    }

    public void printAllPrices() {
        System.out.println();
        for (Stock s : this.shop.getStock())
            if (goodsOk(s)) {
                printPrice(s);
            }
    }

    public long getSKU(Stock s) {
        return s.getSKU();
    }

    public void printSoldForDate(String strDate){
        System.out.println("\tSKU\tCustomer\tGoods");
        for (Sold s : this.shop.getSold())
            if (goodsOk(s)) {
                printSold(s);
            }
    }

    public void printCashState(){
        System.out.println("\nCashdesk state: "+shop.getCash());
    }

    public void printPrice(Stock s) {
        System.out.println(getName(s) + ": " + getPrice(s) + " sku:" + getSKU(s));
    }

    public void printStock(Stock s){
        System.out.println("["+s.getSKU()+"] \""+ s.getName() + "\" -  price:"
                + s.getPrice() + " qty:" + s.getQuantity());
    }
    public void printSold(Sold s){
        System.out.println("["+s.getSKU()+"] "
                + " \""+ s.getCustomer().getName()+" "+s.getCustomer().getFirstName()
                +" ("+s.getCustomer().getDiscountCard().getNumber()+", "+s.getCustomer().getDiscountCard().getDiscountSize()+"%)"
                + " \""+ s.getDate()+" "+ s.getTime()
                + " \""+ s.getName() + "\"" + s.getPrice() +"\t"+ s.getQuantity());
    }


    public int getStockSize() {
        System.out.println(shop.getStock().size());
        return shop.getStock().size();
    }


//    private boolean goodsOk(Stock s) {
//        return s != null && !s.getName().equals("") && s.getSKU() > 0;
//    }

    public static String currDate() {
        Calendar datetime = Calendar.getInstance();
        int YYYY = datetime.get(Calendar.YEAR);
        int MM = datetime.get(Calendar.MONTH) + 1;
        int D = datetime.get(Calendar.DAY_OF_MONTH);

        return "" + YYYY + "-" + leadZero(MM, 2) + "-" + leadZero(D, 2);

    }

    // "YYYY-MM-DD" -> days from 1999-12-31
    public static int dateIndex(String strDate) {
        int y= Integer.parseInt(strDate.substring(0,4))-1900;
        int m= Integer.parseInt(strDate.substring(5,7));
        int d= Integer.parseInt(strDate.substring(8,10));
        int [] mm = {
                0,  31,  59,
                90, 120, 151,
                181, 212, 243,
                273, 304, 334
        };
        return y/4*1461 + y%4*365 + mm[m-1]+d;

    }
    public static String currTime() {
        Calendar datetime = Calendar.getInstance();
        int H = datetime.get(Calendar.HOUR_OF_DAY);
        int M = datetime.get(Calendar.MINUTE);
        int S = datetime.get(Calendar.SECOND);
        int MS = datetime.get(Calendar.MILLISECOND);

        return   "" + leadZero(H, 2) + ":" + leadZero(M, 2) +
                ":" + leadZero(S, 2) + "." + leadZero(MS, 3);

    }
    private static  String leadZero(int a, int len){
        byte sign=1;
        if (a<0) {sign=-1;}
        String result=String.valueOf(a*sign);
        while (result.length()<len) result="0"+result;
        if (a<0) result="-"+result;
        return result;
    }

}
