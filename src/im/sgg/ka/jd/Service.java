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
//////////////////////////////////////////////////////////////////

    public void addToStock(Stock s){
        Stock[] st = shop.getStock();
        if (isStock(s) && !isInStock(st,s)) {
            addData(st,s);
        }
    }

    private boolean isInStock(Stock[] st, Stock s) {
        if (st==null || s== null) return false;
        for (Stock test:st) if (test!=null && test.equals(s)) return true;
        return false;
    }

    public double getPrice(Stock s) {
        return s.getPrice();
    }

    public String getName(Stock s) {
        return s.getName();
    }

    public void printAllStock(){
        System.out.println();
        for (Stock s : this.shop.getStock())
            if (isStock(s)) {
                printStock(s);
            }

    }

    public void printAllPrices() {
        System.out.println();
        for (Stock s : this.shop.getStock())
            if (isStock(s)) {
                printPrice(s);
            }
    }
//
//    public long getSKU(Stock s) {
//        return s.getSKU();
//    }

    public void printSoldForDate(String strDate){
        System.out.println("\tSKU\tCustomer\tGoods");
        for (Sold s : this.shop.getSold())
            if (isStock(s)) {
                printSold(s);
            }
    }

    public void printCashState(){
        System.out.println("\nCashdesk state: "+shop.getCash());
    }

    public void printPrice(Stock s) {
        System.out.println(getName(s) + ": " + getPrice(s));
    }

    public void printStock(Stock s){
        System.out.println("\""+ s.getName() + "\" -  price:"
                + s.getPrice() + " qty:" + s.getQuantity());
    }
    public void printSold(Sold s){
        System.out.println(""
                + " \""+ s.getCustomer().getName()+" "+s.getCustomer().getFirstName()
                +" ("+s.getCustomer().getContact()+")"
                + " \""+ s.getDateIndex()+" "+ s.getTimeIndex()
                + " \""+ s.getName() + "\"" + s.getPrice() +"\t"+ s.getQuantity());
    }


    public void textOut(String text){
        if (isString(text)) System.out.println(text);
    }
    public void textOutLogged(String text){
        if (isString(text)) System.out.println("\n["+timeStamp()+"]\n"+text);
    }
    public void error(String error){
        if (isString(error)) System.out.println("\n["+timeStamp()+"] "+"\nERROR:\t"+error+"\n");
    }

    public String timeStamp(){
        Calendar datetime=Calendar.getInstance();
        int YYYY=datetime.get(Calendar.YEAR);
        int MM=datetime.get(Calendar.MONTH)+1;
        int D=datetime.get(Calendar.DAY_OF_MONTH);
        int H=datetime.get(Calendar.HOUR_OF_DAY);
        int M=datetime.get(Calendar.MINUTE);
        int S=datetime.get(Calendar.SECOND);
        int MS=datetime.get(Calendar.MILLISECOND);

        return ""+YYYY+"-"+leadZero(MM,2)+"-"+leadZero(D, 2)+
                " "+leadZero(H, 2)+":"+leadZero(M, 2)+
                ":"+leadZero(S, 2)+"."+leadZero(MS, 3);

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
    public static int timeIndex(String strTime) {
        int h= Integer.parseInt(strTime.substring(0,2));
        int m= Integer.parseInt(strTime.substring(3,5));
        int s= Integer.parseInt(strTime.substring(6,8));
        int ms=Integer.parseInt(strTime.substring(9,12));
        return ((h*60+m)*60+s)*1000+ms;

    }
    public static String currDate() {
        Calendar datetime = Calendar.getInstance();
        int YYYY = datetime.get(Calendar.YEAR);
        int MM = datetime.get(Calendar.MONTH) + 1;
        int D = datetime.get(Calendar.DAY_OF_MONTH);

        return "" + YYYY + "-" + leadZero(MM, 2) + "-" + leadZero(D, 2);

    }
    public String currTime() {
        Calendar datetime = Calendar.getInstance();
        int H = datetime.get(Calendar.HOUR_OF_DAY);
        int M = datetime.get(Calendar.MINUTE);
        int S = datetime.get(Calendar.SECOND);
        int MS = datetime.get(Calendar.MILLISECOND);

        return   "" + leadZero(H, 2) + ":" + leadZero(M, 2) +
                ":" + leadZero(S, 2) + "." + leadZero(MS, 3);

    }

    public static String leadZero(int a, int len){
        byte sign=1;
        if (a<0) {sign=-1;}
        String result=String.valueOf(a*sign);
        while (result.length()<len) result="0"+result;
        if (a<0) result="-"+result;
        return result;
    }

    ////////////    PRIVATE    ZONE   /////////////////////
    private void addData(Stock[] stockDB, Stock newdata){
        if (! isEnoughFreeSpace(stockDB)) addFreeCells(stockDB);
        stockDB[shop.getQtyOfRecords()]=newdata;
        if (isInStock(stockDB, newdata)) qtyOfRecordInc();
    }

    private int getQtyOfRecords(){
        return shop.getQtyOfRecords();
    }

    private void qtyOfRecordInc(){
        shop.setQtyOfRecords(getQtyOfRecords()+1);
    }

    private void addFreeCells(Stock[] stockDB){
        int records=shop.getQtyOfRecords();
        int newSize=(int)(stockDB.length*1.30);	// +30%
        while (newSize-records<3) newSize++;	// tail for 3 records if short array
        Stock[] newArray = new Stock[newSize];
        System.arraycopy(stockDB, 0, newArray, 0, records);
        shop.setStock(newArray);
    }

    private boolean isEnoughFreeSpace(Stock[] stockDB) {
        return isStockDBOK(stockDB) && stockDB.length - shop.getQtyOfRecords() > 2;
    }

    private boolean isStockDBOK(Stock[] stockDB){
        if (! isArray(stockDB)) {
            error("stockDB=null or empty");
            return false;
        }
        return true;
    }

    private boolean isArray(Stock[] stockDB) {
        if (stockDB==null || stockDB.length<1 )	{
            error("stockDB=null or shorter than 1");
            return false;
        } else{
            return true;
        }
    }

    private boolean isArray(int[] intArray){
        if (intArray==null || intArray.length<1 )	{
            error("intArray=null or shorter than 1");
            return false;
        } else{
            return true;
        }
    }
    private boolean isArray(String[][] strArray){
        if (strArray==null || strArray.length<1 )	{
            error("strArray=null or shorter than 1");
            return false;
        } else{
            return true;
        }
    }

    private boolean isArray(String[] strArray){
        if (strArray==null || strArray.length<1 )	{
            error("strArray=null or shorter than 1");
            return false;
        } else{
            return true;
        }
    }




    private String dateIndexToString(int dateIndex){
        int y = dateIndex/1461; y*=4;   // 19     76
        dateIndex-=y/4*1461;            // 1003
        int y2 = dateIndex/365; y+=y2;  // 2   78
        dateIndex-= y2*365;              //  273
        int [] mm = {
                0,  31,  59,
                90, 120, 151,
                181, 212, 243,
                273, 304, 334, 365
        };
        int [] md = {
                0, 31, 28, 31,
                   30, 31, 30,
                   31, 31, 30,
                   31, 30, 31
        };
        int m=0;
        for (int i=1; i<=11;i++) if (mm[i]>=dateIndex) m=i;
        int d=md[m]-(mm[m] - dateIndex);

        return "" + y + "-" + leadZero(m, 2) + "-" + leadZero(d, 2);
    }

    private String timeIndexToString(int timeIndex){
        int ms = timeIndex%1000; timeIndex/=1000;
        int s  = timeIndex%60;   timeIndex/=60;
        int m  = timeIndex%60;   timeIndex/=60;
        int h  = timeIndex;
        return   "" + leadZero(h, 2) + ":" + leadZero(m, 2) +
                ":" + leadZero(s, 2) + "." + leadZero(ms, 3);
    }

    private boolean isString(String str){
        if (str==null || str.length()<1 )	{
            error("str=null or shorter than 1");
            return false;
        } else{
            return true;
        }
    }
    private boolean isStock(Stock s) {
        return s != null && !s.getName().equals("");
    }

}
