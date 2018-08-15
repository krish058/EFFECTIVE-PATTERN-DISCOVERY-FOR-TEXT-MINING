package effective;
import javax.swing.JTable;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
public class Algorithm2
{
	JTable table;
	double min_sup=50;
	ArrayList<IPEBean> ipe = new ArrayList<IPEBean>();
	ArrayList<String> dup = new ArrayList<String>();
	DefaultTableModel dtm;
	double th = 2;
public void startProcess(JTable table){
	ipe.clear();
	dup.clear();
	for(int i=0;i<table.getRowCount();i++){
		String terms = table.getValueAt(i,0).toString();
		String paragraph = table.getValueAt(i,1).toString();
		double weight = Double.parseDouble(table.getValueAt(i,2).toString().trim());
		if(weight > th){
			IPEBean bean = new IPEBean();
			if(!dup.contains(paragraph)){
				bean.setSelected(paragraph);
				bean.setTerm(terms);
				dup.add(paragraph);
			}else{
				bean.setPrune(paragraph);
				bean.setTerm(terms);
			}
			ipe.add(bean);
		}
	}
	for(int i=0;i<ipe.size();i++){
		IPEBean bn = ipe.get(i);
		String para = bn.getSelected();
		if(para != null){
			System.out.println(para+" "+bn.getTerm());
		}
	}
}
public ArrayList<IPEBean> getBean(){
	return ipe;
}
}