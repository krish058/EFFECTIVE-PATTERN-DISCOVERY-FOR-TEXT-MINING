package effective;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import org.jfree.ui.RefineryUtilities;
public class ReadChart
{
	ArrayList<String> pdr = new ArrayList<String>();
	ArrayList<String> poh = new ArrayList<String>();
	int d,s,r,sb,rb;
public ReadChart(){
	try{
		String s1=null;
		BufferedReader br = new BufferedReader(new FileReader("routing/routing.txt"));
		while((s1=br.readLine()) != null){
			String arr[]=s1.split(",");
			if(arr[4].equals("d"))
				d = d + 1;
			if(arr[4].equals("s")){
				s = s + 1;
				sb = sb+Integer.parseInt(arr[3].trim());
			}
			if(arr[4].equals("r")){
				r = r + 1;
				rb = rb+Integer.parseInt(arr[3].trim());
			}
		}
		br.close();
		double pdratio = r/s;
		double pover = rb/sb;
		pdr.add(pdratio+",Packet delivery ratio");
		pdr.add(pover+",Packet Overhead");
		Chart chart1 = new Chart("Binary Isolation",pdr);
		chart1.pack();
		RefineryUtilities.centerFrameOnScreen(chart1);
		chart1.setVisible(true);
	}catch(Exception e){
		e.printStackTrace();
	}
}
}