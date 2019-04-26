/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canteenmanagv2;

/**
 *
 * @author Shubham Jain
 */
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
class prd{
    public String prod;
    public int rate;
    public String qt;
    public int qtn;
    public prd(){
        rate=0;
        prod="";
        qt="0";
        qtn=100;
    }
}
class billing{
    public String bill;
    public billing(){
        bill="empty";
    }  
    public static void billprint(billing bl[],String bill) throws IOException{
                    Document document=new Document(PageSize.A4);
                    System.out.println("Creating File ........");
                    System.out.println("Writing Data to File ........");
                    System.out.println("Making PDF Document ........");
                    FileWriter fw=new FileWriter(bill+".txt");
                    String pdf=new String();
                    for(int i=0;i<19;i++){
                        pdf=pdf+bl[i].bill+"\n";
                        fw.write(bl[i].bill+"\n");
                    }
                     try{
                        PdfWriter.getInstance(document,new FileOutputStream(bill+".pdf"));
                        document.open();
                        document.add(new Paragraph(pdf));
                        }
                        catch(Exception e){
                         System.out.println("Exception :"+e);
                        }
                     System.out.println("Operations Done !");
                    fw.close();
                    document.close();
    }
}
public class CanteenManagv2 {
    /**
     * @param args the command line arguments
     */
    public static void logcheck(String log) throws IOException{
        int flag=1;
        BufferedReader br=new BufferedReader (new InputStreamReader(System.in));
        do{
            String tlog;
            System.out.println("============AIR FOURCE WIVES WELFARE ASSOCIATION=============");
            System.out.print("LOGIN_ID :");
            tlog=br.readLine();
            if (!tlog.equals(log)){
             System.out.println("Invalid Login ID : "+tlog);
             continue ;
            }
            flag=0;
        } while(flag!=0);
        
    }
    public static void billmake(prd inv[],billing bl[]){
                    int sum=0;
                    for(int i=0;i<10;i++){
                        if(inv[i].qt=="0")
                            continue;
                        bl[i+6].bill=inv[i].qt+"\t"+inv[i].prod+"\t\t"+inv[i].rate+"\t"+(Integer.parseInt(inv[i].qt)*inv[i].rate);
                        sum=sum+(Integer.parseInt(inv[i].qt)*inv[i].rate);
                    }
                    bl[17].bill="\t\tTOTAL\t\t\t"+sum;
                    for(int i=0;i<20;i++){
                        if(bl[i].bill=="empty")
                            continue;
                        System.out.println(bl[i].bill);
                    }
        
    }
    public static void main(String[] args) throws IOException {
        FileReader fr2,fr1=new FileReader("inv.txt");
        prd inv[]=new prd[10];
        for(int i=0;i<10;i++){
            inv[i]=new prd();
            inv[i].qtn=(fr1.read());
        }
        fr1.close();
        billing bl[]=new billing[20];
        for(int i=0;i<20;i++)
            bl[i]=new billing();
        String ch="n",log="SHU839",swp;
        int flag=1,count=0;
        DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        Date dt=new Date();
        BufferedReader br=new BufferedReader (new InputStreamReader(System.in));
        inv[0].prod="BISCUIT\t";
        inv[1].prod="PEPSODENT";
        inv[2].prod="DENIM POWDER";
        inv[3].prod="MUNCH\t";
        inv[4].prod="PARK AVENUE ";
        inv[5].prod="COCONUT OIL";
        inv[6].prod="TITAN WATCH";
        inv[7].prod="SURF EXCEL";
        inv[8].prod="DOVE SOAP";
        inv[9].prod="SHOE\t";
        inv[0].rate=10;
        inv[1].rate=30;
        inv[2].rate=110;
        inv[3].rate=8;
        inv[4].rate=110;
        inv[5].rate=90;
        inv[6].rate=1199;
        inv[7].rate=28;
        inv[8].rate=109;
        inv[9].rate=248;
        bl[0].bill="===========================================================";
        bl[4].bill="===========================================================";
        bl[6].bill="===========================================================";
        bl[16].bill="===========================================================";
        bl[17].bill="0";
        bl[18].bill="===========================================================";
        logcheck(log);
        String cus,paym,num,date,bno,bill;
        System.out.println("=================Enter Customer Detail===============");
        System.out.print("Customer Name :");
        cus="Customer Name :";
        cus=cus + br.readLine();
        System.out.print("\nPayment method :\n1.Cash \n2.Credit\n*Enter Your Choice :");
        paym="Payment Method :";
        swp=br.readLine();
        switch(swp){
            case "1":
                paym=paym+"Cash";
                break;
            case "2":
                paym=paym+"Credit";
                break;
            default :
                System.out.print("Enter Valid Choice !");
        }
        System.out.print("\nPhone Number :");
        num="Phone Number :";
        num=num + br.readLine();
        date="Date :"+df.format(dt);
        bno="Bill No :";
        fr2=new FileReader("bill_no.txt");
        bill=String.valueOf(fr2.read()+1);
        //bill=br.readLine();
        bno=bno + bill;
        fr2.close();
        System.out.print("\nBill No :"+bill);
        bl[1].bill=cus+"\t\t"+date;
        bl[2].bill=num+"\t"+paym;
        bl[3].bill=bno;
        bl[5].bill="QTY\tPARTICULARS\t\tRATE\tTOTAL";
        do {
            System.out.println("\n\n\n\n=============Menu============");
            System.out.print("\n1.Product List\n2.Available Quantity\n3.Print Bill\n4.Print Bill(PDF) \n5.Exit\n*Enter your Choice : ");
            ch=br.readLine();
            switch(ch){
                case "1":
                    String pch;
                    System.out.println("ITEM\t\t\t\t\tRATE");
                    System.out.print("1."+inv[0].prod+"\t\t\t"+inv[0].rate+"\n");
                    System.out.print("2."+inv[1].prod+"\t\t\t\t"+inv[1].rate+"\n");
                    System.out.print("3."+inv[2].prod+"\t\t\t\t"+inv[2].rate+"\n");
                    System.out.print("4."+inv[3].prod+"\t\t\t\t"+inv[3].rate+"\n");
                    System.out.print("5."+inv[4].prod+"\t\t\t\t"+inv[4].rate+"\n");
                    System.out.print("6."+inv[5].prod+"\t\t\t\t"+inv[5].rate+"\n");
                    System.out.print("7."+inv[6].prod+"\t\t\t\t"+inv[6].rate+"\n");
                    System.out.print("8."+inv[7].prod+"\t\t\t\t"+inv[7].rate+"\n");
                    System.out.print("9."+inv[8].prod+"\t\t\t\t"+inv[8].rate+"\n");
                    System.out.print("10."+inv[9].prod+"\t\t\t\t"+inv[9].rate+"\n");
                    System.out.print("\n\nDo you want to purchase (Y/N) :");
                    pch=br.readLine();
                    switch(pch){
                    //if(pch.charAt(0)=='n' || pch.charAt(0)=='N')
                        case "n":
                            continue;
                        case "N":
                            continue;
                        case "y": 
                    //else if (pch.charAt(0)=='y' || pch.charAt(0)=='Y'){
                            System.out.print("\n"+inv[0].prod+" : ");
                            inv[0].qt=br.readLine();
                            System.out.print(inv[1].prod+" : ");
                            inv[1].qt=br.readLine();
                            System.out.print(inv[2].prod+" : ");
                            inv[2].qt=br.readLine();
                            System.out.print(inv[3].prod+" : ");
                            inv[3].qt=br.readLine();
                            System.out.print(inv[4].prod+" : ");
                            inv[4].qt=br.readLine();
                            System.out.print(inv[5].prod+" : ");
                            inv[5].qt=br.readLine();
                            System.out.print(inv[6].prod+" : ");
                            inv[6].qt=br.readLine();
                            System.out.print(inv[7].prod+" : ");
                            inv[7].qt=br.readLine();
                            System.out.print(inv[8].prod+" : ");
                            inv[8].qt=br.readLine();
                            System.out.print(inv[9].prod+" : ");
                            inv[9].qt=br.readLine();
                            count++;
                            break;
                        case "Y":
                            System.out.print("\n"+inv[0].prod+" : ");
                            inv[0].qt=br.readLine();
                            System.out.print(inv[1].prod+" : ");
                            inv[1].qt=br.readLine();
                            System.out.print(inv[2].prod+" : ");
                            inv[2].qt=br.readLine();
                            System.out.print(inv[3].prod+" : ");
                            inv[3].qt=br.readLine();
                            System.out.print(inv[4].prod+" : ");
                            inv[4].qt=br.readLine();
                            System.out.print(inv[5].prod+" : ");
                            inv[5].qt=br.readLine();
                            System.out.print(inv[6].prod+" : ");
                            inv[6].qt=br.readLine();
                            System.out.print(inv[7].prod+" : ");
                            inv[7].qt=br.readLine();
                            System.out.print(inv[8].prod+" : ");
                            inv[8].qt=br.readLine();
                            System.out.print(inv[9].prod+" : ");
                            inv[9].qt=br.readLine();
                            count++;
                            break;
                         default:
                            System.out.println("Enter valid choice !");
                        }
                        break;    
                case "2":
                    if(count!=0){
                        FileWriter fw2,fw1=new FileWriter("inv.txt");
                        fw2=new FileWriter("bill_no.txt");
                       for(int i=0;i<10;i++){
                            int temp=0;
                            temp=Integer.parseInt(inv[i].qt);
                            inv[i].qtn=inv[i].qtn-temp;
                            fw1.write(inv[i].qtn);
                        }
                       fw2.write(Integer.parseInt(bill));
                       fw2.close();
                       fw1.close();
                    }
                    System.out.println("\n\n==================Available Quantity=====================\n");
                    System.out.println("1."+inv[0].prod+"\t\t\t"+inv[0].qtn);
                    System.out.println("2."+inv[1].prod+"\t\t\t\t"+inv[1].qtn);
                    System.out.println("3."+inv[2].prod+"\t\t\t\t"+inv[2].qtn);
                    System.out.println("4."+inv[3].prod+"\t\t\t\t"+inv[3].qtn);
                    System.out.println("5."+inv[4].prod+"\t\t\t\t"+inv[4].qtn);
                    System.out.println("6."+inv[5].prod+"\t\t\t\t"+inv[5].qtn);
                    System.out.println("7."+inv[6].prod+"\t\t\t\t"+inv[6].qtn);
                    System.out.println("8."+inv[7].prod+"\t\t\t\t"+inv[7].qtn);
                    System.out.println("9."+inv[8].prod+"\t\t\t\t"+inv[8].qtn);
                    System.out.println("10."+inv[9].prod+"\t\t\t\t"+inv[9].qtn);
                    break;
                case "3":
                    billmake(inv,bl);
                    
                    /*int sum=0;
                    for(int i=0;i<10;i++){
                        if(inv[i].qt=="0")
                            continue;
                        bl[i+6].bill=inv[i].qt+"\t"+inv[i].prod+"\t\t"+inv[i].rate+"\t"+(Integer.parseInt(inv[i].qt)*inv[i].rate);
                        sum=sum+(Integer.parseInt(inv[i].qt)*inv[i].rate);
                    }
                    bl[17].bill="\t\tTOTAL\t\t\t"+sum;
                    for(int i=0;i<20;i++){
                        if(bl[i].bill=="empty")
                            continue;
                        System.out.println(bl[i].bill);
                    }*/
                    break;
                case "4":
                    
                    billing.billprint(bl,bill);/*
                    Document document=new Document(PageSize.A4);
                    System.out.println("creating document .....");
                    FileWriter fw=new FileWriter("Bill.txt");
                    String pdf=new String();
                    for(int i=0;i<19;i++){
                        pdf=pdf+bl[i].bill+"\n";
                        fw.write(bl[i].bill+"\n");
                    }
                     try{
                        PdfWriter.getInstance(document,new FileOutputStream("Bill.pdf"));
                        document.open();
                        document.add(new Paragraph(pdf));
                        }
                        catch(Exception e){
                         System.out.println("Exception :"+e);
                        }
                    fw.close();
                    document.close();*/
                    break;
                case "5":
                    ch="n";   
            }    
        }while (ch.charAt(0)!='n' && ch.charAt(0)!='N');
    }
}