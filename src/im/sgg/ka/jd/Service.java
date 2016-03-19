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

    public void sell(String name, int qty, String date, String time, Customer cust){
        textOut("Trying to sell "+qty+" of "+name+"...");
        Stock stock = getStockbyName(name);
        if (stock == null) {
            textOut("We can not sell " + qty + " of "+name);
            return;
        }
        sell( stock, qty, date, time, cust);
    }

    public void sell(Stock s, int qty, String date, String time, Customer cust){
        if (isArray(s) && sellArticle(s,qty,date,time,cust)) {
            textOut("\nSold out: "+ s.getName() +" "+qty+" pcs");
            textOut("Each for "+ s.getPrice());
            textOut("Income: "+qty*s.getPrice());
        } else textOut("We can not sell " + qty + " of " + s.getName());
        printCashdesk();
    }

    private Stock getStockbyName(String name){
        for (Stock s : shop.getStock()) {
            if ( s!=null  && s.getName().equals(name)) return s;
        }
        return null;
    }

    private void printCashdesk() {
        textOutLogged(lineDivider(12)+"\nMoney in cashdesk: "+shop.getCash()+"\n"+lineDivider(12));
    }

    private String lineDivider(int qty) {
        return stringClone(stringOfChar('=', 8), qty)+"\n";
    }

    private String stringClone(String str, int q) {
        if (! isString(str) || q<1) return "";
        String result="";
        for (int i=0;i<q;i++) result+=str;
        return result;
    }

    private String stringOfChar(char ch, int q) {
        if (! isString(""+ch) || q<1) return "";
        String result="";
        for (int i=0;i<q;i++) result+=ch;
        return result;
    }

    private boolean isInStock(Stock[] st, Stock s) {
        if (st==null || s== null) return false;
        for (Stock test:st) if (test!=null && test.equals(s)) return true;
        return false;
    }

//    private boolean isInSold(Sold[] sold, sold s) {
//        if (sold==null || s== null) return false;
//        for (Stock test:sold) if (test!=null && test.equals(s)) return true;
//        return false;
//    }

    public double getPrice(Stock s) {
        return s.getPrice();
    }

    public String getName(Stock s) {
        return s.getName();
    }

    public void printAllStock(){
        textOut("");
        for (Stock s : this.shop.getStock())
            if (isStock(s)) {
                printStock(s);
            }

    }

    public void printAllPrices() {
        textOut("");
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
        int dI = dateIndex(strDate);
        textOut("Sold for "+strDate+"\n"
                +lineDivider(12)
                +"Date     \tTime         \tGoods            \tPrice  \tQty   \tCustomer"+"\n"
                + lineDivider(12));
        int counter=0;
        double sum=0;
        int salecounter=0;
        for (Sold s : shop.getSold())
            if (isStock(s) && s.getDateIndex()==dI) {
                printSoldDate(s);
                counter+=s.getQuantity();
                sum+=s.getPrice()*s.getQuantity();
                salecounter++;
            }
        textOut(lineDivider(12)
                + "Total sales:\t"+salecounter+"\n"
                + "Total pieces:\t"+counter+"\n"
                + "Total incomes:\t"+sum+"\n"
                + lineDivider(12)+"\n");
    }

    public void printSoldForLast(int qty){
        int today = dateIndex(currDate());
        textOut("Sold for last "+qty+" day(s)\n"
                +lineDivider(12)
                +"Date     \tTime         \tGoods            \tPrice  \tQty   \tCustomer"+"\n"
                + lineDivider(12));
        int counter=0;
        double sum=0;
        int salecounter=0;
        for (Sold s : shop.getSold())
            if (isStock(s)  && s.getDateIndex()>=today-qty) {
                printSoldDate(s);
                counter+=s.getQuantity();
                sum+=s.getPrice()*s.getQuantity();
                salecounter++;
            }
        textOut(lineDivider(12)
                + "Total sales:\t"+salecounter+"\n"
                + "Total pieces:\t"+counter+"\n"
                + "Total incomes:\t"+sum+"\n"
                + lineDivider(12)+"\n");
    }

    public void printCashState(){
        textOut("\nCashdesk state: "+shop.getCash());
    }

    public void printPrice(Stock s) {
        textOut(getName(s) + ": " + getPrice(s));
    }

    public void printStock(Stock s){
        textOut("\""+ s.getName() + "\" -  price: "
                + s.getPrice() + " - qty: " + s.getQuantity());
    }
    public void printSoldDate(Sold s){
        textOut(""
                + dateIndexToString(s.getDateIndex())
                +"\t" +timeIndexToString(s.getTimeIndex())
                +"\t\""+ s.getName() + "\"\t    " + s.getPrice() +"\t"+ s.getQuantity()
                +"\t("+ s.getCustomer().getName()+" "+s.getCustomer().getFirstName()
                +", "+s.getCustomer().getContact()+")"
        );
    }


    public void textOut(String text){
        if (isString(text)) System.out.println(text);
    }
    public void textOutLogged(String text){
        if (isString(text)) textOut("\n["+timeStamp()+"]\n"+text);
    }
    public void error(String error){
        if (isString(error)) textOut("\n["+timeStamp()+"] "+"\nERROR:\t"+error+"\n");
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
        int ms=0;
        if (strTime.length()==12) {
            ms=Integer.parseInt(strTime.substring(9,12));
        }
        return ((h*60+m)*60+s)*1000+ms;

    }
    public static String currDate() {
        Calendar datetime = Calendar.getInstance();
        int YYYY = datetime.get(Calendar.YEAR);
        int MM = datetime.get(Calendar.MONTH) + 1;
        int D = datetime.get(Calendar.DAY_OF_MONTH);

        return "" + YYYY + "-" + leadZero(MM, 2) + "-" + leadZero(D, 2);

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

    public static String leadZero(int a, int len){
        byte sign=1;
        if (a<0) {sign=-1;}
        String result=String.valueOf(a*sign);
        while (result.length()<len) result="0"+result;
        if (a<0) result="-"+result;
        return result;
    }

    ////////////    PRIVATE    ZONE   /////////////////////

    private boolean sellArticle(Stock s, int q,String date, String time, Customer cust){
        if (! isStockDBOK(shop.getStock()) || ! isArray(s) || s.getQuantity()<q) return false;
        // s.quantity > 0 && > q
        stockMinus(s,q);
        soldPlus(shop.getSold(),s,q,date,time,cust);
        moneyToCashdesk(s.getPrice()*q);
        return true;
    }

    private void moneyToCashdesk(double m) {
        if (m>0)	shop.setCash(shop.getCash()+m);
    }

    private void moneyFromCashdesk(double m){
        if (m>0 && m<=shop.getCash())	shop.setCash(shop.getCash()-m);
    }

    private void stockMinus(Stock s, int q) {
        if (! isArray(s) && ! isStockDBOK(shop.getStock())) return;
        Stock[] shopDB = shop.getStock();
        int index = getStockIndex(shopDB,s);
        shopDB[index].setQuantity(( shopDB[index].getQuantity()-q));

    }

    private void soldPlus(Sold[] sold, Stock stock, int q, String date, String time, Customer cust){
        if (! isArray(sold) ) addFreeCells(sold);
        int index = getFirstFree(sold);
        sold = shop.getSold();    // after adding cells we should switch the link to the new Sold object
        sold[index] = new Sold();
        sold[index].setType(stock.getType());
        sold[index].setName(stock.getName());
        sold[index].setPrice(stock.getPrice());
        sold[index].setQuantity(q);
        sold[index].setDateIndex(dateIndex(date));
        sold[index].setTimeIndex(timeIndex(time));
        sold[index].setCustomer(cust);
        qtyOfSoldInc();
    }

    private int getStockIndex(Stock[] shopDB, Stock s){
        int index=0;
        while (index<shopDB.length) {
            if (shopDB[index].equals(s)) return index;
            index++;
        }
        return -1; // not found

    }

    private void addData(Stock[] stockDB, Stock newdata){
        if (! isEnoughFreeSpace(stockDB)) addFreeCells(stockDB);
        stockDB[shop.getQtyStock()]=newdata;
        if (isInStock(stockDB, newdata)) qtyOfStockInc();
    }

    private int getFirstFree(Sold[] sold) {
        int index=findFreeFrom(sold,0);
        if (index >= 0) return index;
        else {
            int i=sold.length;
            addFreeCells(sold);
            return findFreeFrom(shop.getSold(),i);
        }
    }
    private int findFreeFrom(Sold[] sold,int start){
        for (int i=start;i<sold.length;i++) if (isEmpty(sold[i])) return i;
        return -1; // no free
    }

    private boolean isEmpty(Sold sold) {
        return sold == null || sold.getName().equals("") || sold.getQuantity() < 1;
    }

    private int getQtyOfStock(){
        return shop.getQtyStock();
    }

    private int getQtyOfSold(){
        return shop.getQtySold();
    }

    private void qtyOfStockInc(){
        shop.setQtyStock(getQtyOfStock()+1);
    }

    private void qtyOfSoldInc(){
        shop.setQtySold(getQtyOfSold()+1);
    }

    private void addFreeCells(Stock[] stockDB){
        int records=shop.getQtyStock();
        int newSize=(int)(stockDB.length*1.30);	// +30%
        while (newSize-records<3) newSize++;	// tail for 3 records if short array
        Stock[] newArray = new Stock[newSize];
        System.arraycopy(stockDB, 0, newArray, 0, records);
        shop.setStock(newArray);
    }
    private void addFreeCells(Sold[] sold){
        int records=shop.getQtySold();
        int newSize=(int)(sold.length*1.30);	// +30%
        while (newSize-records<3) newSize++;	// tail for 3 records if short array
        Sold[] newArray = new Sold[newSize];
        System.arraycopy(sold, 0, newArray, 0, records);
        sold=newArray;
        shop.setSold(sold);
    }

    private boolean isEnoughFreeSpace(Stock[] stockDB) {
        return isStockDBOK(stockDB) && stockDB.length - shop.getQtyStock() > 2;
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
    private boolean isArray(Sold[] solds) {
        if (solds==null || solds.length<1 )	{
            error("solds=null or shorter than 1");
            return false;
        } else{
            return true;
        }
    }
    private boolean isArray(Stock stock) {
        if (stock==null || stock.getQuantity()<1 )	{
            error("stock=null or less than 1");
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




    public static String dateIndexToString(int dateIndex){
        int y = dateIndex/1461; y*=4;   // 19     76
        dateIndex-=y/4*1461;            // 1003
        int y2 = dateIndex/365; y+=y2+1900;  // 2   78
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
        int i=1;
        while (i<12 && mm[i]<dateIndex) m = ++i;
        int d=md[m]-(mm[m] - dateIndex);

        return "" + y + "-" + leadZero(m, 2) + "-" + leadZero(d, 2);
    }

    public static String timeIndexToString(int timeIndex){
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
