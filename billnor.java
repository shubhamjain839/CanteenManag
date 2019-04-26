import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

public class billnor  {
    public static void main(String Arg[])throws IOException{
        FileWriter fw=new FileWriter("bill_no.txt");
        Scanner sc=new Scanner(System.in);
        int bill;
        System.out.print("Enter bill No (Starting) :");
            bill=sc.nextInt();
            fw.write(bill);
        fw.close();
        
    }
}