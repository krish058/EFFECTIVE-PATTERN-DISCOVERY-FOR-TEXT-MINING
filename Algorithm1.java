package effective;
import javax.swing.JTable;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
public class Algorithm1
{
	JTable table;
	double min_sup=50;
	ArrayList<DPBean> dp = new ArrayList<DPBean>();
	ArrayList<DPBean> list = new ArrayList<DPBean>();
	ArrayList<String> dup = new ArrayList<String>();
	DefaultTableModel dtm;
public void startProcess(JTable table,DefaultTableModel dtm){
	this.table = table;
	this.dtm=dtm;
	dp.clear();
	dup.clear();
	for(int i=0;i<table.getRowCount();i++){
		String data = table.getValueAt(i,1).toString().trim();
		String terms[] = data.split(",");
		for(int j=0;j<terms.length;j++){
			String term = terms[j];
			if(!dup.contains(term)){
				dup.add(term);
				String result = getResult(term);
				if(!result.equals("none")){
					String out[]=result.split("#");
					DPBean bean = new DPBean();
					bean.setTerm(term);
					bean.setParagraph(out[0]);
					bean.setSupport(Double.parseDouble(out[1].trim()));
					dp.add(bean);
				}
			}
		}
		if(terms.length >= 2){
			splitTerms(terms);
		}
		if(terms.length >= 3){
			ThirdTerms(terms);
		}
	}
	dup.clear();
	int as[]=new int[dp.size()];
	for(int i=0;i<dp.size();i++){
		DPBean bean = dp.get(i);
		as[i] = bean.getTerm().split(",").length;
	}
	Arrays.sort(as);
	for(int i=as.length-1;i>=0;i--){
		for(int j=0;j<dp.size();j++){
			DPBean bean = dp.get(j);
			String tr = bean.getTerm();
			if(as[i] == tr.split(",").length && !dup.contains(tr)){
				DPBean bn = new DPBean();
				bn.setParagraph(bean.getParagraph());
				bn.setTerm(bean.getTerm());
				bn.setSupport(bean.getSupport());
				list.add(bn);
				dup.add(tr);
				j = dp.size();
			}
		}
	}
	
	for(int i=0;i<list.size();i++){
		DPBean bean = list.get(i);
		Object row[]={bean.getTerm(),bean.getParagraph(),bean.getSupport()};
		dtm.addRow(row);
	}
}

public void ThirdTerms(String[] terms){
	ArrayList<String> list = new ArrayList<String>();
	for(int i=0;i<terms.length;i++){
		for(int j=i+1;j<terms.length;j++){
			for(int k=j+1;k<terms.length;k++){
			String term = terms[i]+","+terms[j]+","+terms[k];
			if(!dup.contains(term)){
				dup.add(term);
				String result = getThirdResult(term,2);
				if(!result.equals("none")){
					String out[]=result.split("#");
					DPBean bean = new DPBean();
					bean.setTerm(term);
					bean.setParagraph(out[0]);
					bean.setSupport(Double.parseDouble(out[1].trim()));
					dp.add(bean);
				}
			}
			}
		}
	}
}
public String getThirdResult(String term,int end){
	StringBuffer sb=new StringBuffer();
	double count = 0;
	for(int i=0;i<table.getRowCount();i++){
		String terms[] = table.getValueAt(i,1).toString().trim().split(",");
		for(int j=0;j<terms.length;j++){
		for(int k=j+1;k<terms.length;k++){
			for(int m=k+1;m<terms.length;m++){
			String data = terms[j]+","+terms[k]+","+terms[m];
			if(term.equals(data)){
				count = count+1;
				sb.append(table.getValueAt(i,0).toString().trim()+",");
			}
		}
		}
		}
	}
	double sup = (count/table.getRowCount())*100;
	if(sup < min_sup){
		sb.delete(0,sb.length());
		sb.append("none");
	}else{
		sb.deleteCharAt(sb.length()-1);
		sb.append("#"+count);
	}
	return sb.toString();
}
public void splitTerms(String[] terms){
	ArrayList<String> list = new ArrayList<String>();
	for(int i=0;i<terms.length;i++){
		for(int j=i+1;j<terms.length;j++){
			String term = terms[i]+","+terms[j];
			if(!dup.contains(term)){
				dup.add(term);
				String result = getCompleteResult(term,2);
				if(!result.equals("none")){
					String out[]=result.split("#");
					DPBean bean = new DPBean();
					bean.setTerm(term);
					bean.setParagraph(out[0]);
					bean.setSupport(Double.parseDouble(out[1].trim()));
					dp.add(bean);
				}
			}
		}
	}
}
public String getCompleteResult(String term,int end){
	StringBuffer sb=new StringBuffer();
	double count = 0;
	for(int i=0;i<table.getRowCount();i++){
		String terms[] = table.getValueAt(i,1).toString().trim().split(",");
		for(int j=0;j<terms.length;j++){
		for(int k=j+1;k<terms.length;k++){
			String data = terms[j]+","+terms[k];
			if(term.equals(data)){
				count = count+1;
				sb.append(table.getValueAt(i,0).toString().trim()+",");
			}
		}
		}
	}
	double sup = (count/table.getRowCount())*100;
	if(sup < min_sup){
		sb.delete(0,sb.length());
		sb.append("none");
	}else{
		sb.deleteCharAt(sb.length()-1);
		sb.append("#"+count);
	}
	return sb.toString();
}

public String getResult(String term){
	StringBuffer sb=new StringBuffer();
	double count=0;
	for(int i=0;i<table.getRowCount();i++){
		String terms[] = table.getValueAt(i,1).toString().trim().split(",");
		for(int j=0;j<terms.length;j++){
			if(terms[j].equals(term)){
				count = count+1;
				sb.append(table.getValueAt(i,0).toString().trim()+",");
			}
		}
	}
	double sup = (count/table.getRowCount())*100;
	if(sup < min_sup){
		sb.delete(0,sb.length());
		sb.append("none");
	}else{
		sb.deleteCharAt(sb.length()-1);
		sb.append("#"+count);
	}
	return sb.toString();
}
}