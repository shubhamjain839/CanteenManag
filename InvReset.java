import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
public class InvReset  {
    public static void main(String Arg[])throws IOException{
        FileWriter fw=new FileWriter("inv.txt");
        prd inv[]=new prd[10];
        for(int i=0;i<10;i++){
            inv[i]=new prd();
            fw.write(inv[i].qtn);
        }
        
        fw.close();
        
    }
}