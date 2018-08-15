package effective;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.UIManager;
import java.io.FileWriter;
import javax.swing.table.TableColumn;
import org.jfree.ui.RefineryUtilities;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.CardLayout;
import javax.swing.ImageIcon;
public class Patterns extends JFrame implements ActionListener
{
	JLabel l1,l2;
	JTextField tf1;
	Font f1,f2;
	JPanel p1,p2,p3,p4;
	JFileChooser chooser;
	JTable table1,table2;
	DefaultTableModel dtm1,dtm2;
	JScrollPane jsp1,jsp2;
	String column1[]={"Paragraph","Terms"};
	String column2[]={"Frequent Pattern","Covering Set","Support"};
	JButton b1;
	String filedata;
	CardLayout card;
	JPanel panel; 
	JMenuBar bar;
	JMenu m1,m2,m3;
	JMenuItem i1,i2,i3,i4,i5,i6,i7;
	Algorithm2 alg;
public Patterns()
{
	setTitle("Effective Pattern Discovery for Text Mining");
	panel = new JPanel();
	card = new CardLayout();
	panel.setLayout(card);

	p4 = new JPanel();
	p4.setLayout(new BorderLayout());
	ImageIcon icon=new ImageIcon("img/images.jpg");
	l2 = new JLabel(icon);
	p4.add(l2);

	
	bar = new JMenuBar();
	setJMenuBar(bar);
	m1 = new JMenu("Upload DB File");
	bar.add(m1);

	i1 = new JMenuItem("Upload File");
	m1.add(i1);
	i1.addActionListener(this);

	m2 = new JMenu("Pattern Taxonomy");
	bar.add(m2);

	i2 = new JMenuItem("Generate Pattern");
	m2.add(i2);
	i2.addActionListener(this);

	m3 = new JMenu("PTM IPE");
	bar.add(m3);

	i3 = new JMenuItem("Pattern Taxonomy Model");
	m3.add(i3);
	i3.addActionListener(this);

	i6 = new JMenuItem("Partial Conflict Tree");
	m3.add(i6);
	i6.addActionListener(this);

	i7 = new JMenuItem("Complete Conflict Tree");
	m3.add(i7);
	i7.addActionListener(this);

	i4 = new JMenuItem("View Chart");
	m3.add(i4);
	i4.addActionListener(this);

	i5 = new JMenuItem("Exit");
	m3.add(i5);
	i5.addActionListener(this);

	p1 = new JPanel();
	p1.setLayout(new FlowLayout());
	f2 = new Font("Courier New",Font.PLAIN,16);

	l1 = new JLabel("Choose DB File");
	l1.setFont(f2);
	p1.add(l1);

	tf1 = new JTextField(25);
	tf1.setFont(f2);
	p1.add(tf1);

	chooser = new JFileChooser();
	b1 = new JButton("Browse");
	b1.setFont(f2);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			if(ae.getSource() == b1){
				int option = chooser.showOpenDialog(Patterns.this);
				if(option == JFileChooser.APPROVE_OPTION){
					clearTable();
					File file = chooser.getSelectedFile();
					tf1.setText(file.getPath());
					loadDB(file);
				}
			}
		}
		});
		p1.add(b1,"wrap");

		
		
		p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		dtm1 = new DefaultTableModel(){
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};
		table1 = new JTable(dtm1);
		jsp1 = new JScrollPane(table1);
		dtm1.addColumn(column1[0]);
		dtm1.addColumn(column1[1]);
		table1.setRowHeight(40);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn col = table1.getColumnModel().getColumn(0);
		col.setPreferredWidth(100);
		col = table1.getColumnModel().getColumn(1);
		col.setPreferredWidth(350);
		p2.add(jsp1);

		p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		dtm2 = new DefaultTableModel(){
			public boolean isCellEditable(int r,int c){
				return false;
			}
		};
		table2 = new JTable(dtm2);
		jsp2 = new JScrollPane(table2);
		dtm2.addColumn(column2[0]);
		dtm2.addColumn(column2[1]);
		dtm2.addColumn(column2[2]);
		table2.setRowHeight(40);
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		col = table2.getColumnModel().getColumn(0);
		col.setPreferredWidth(100);
		col = table2.getColumnModel().getColumn(1);
		col.setPreferredWidth(250);
		p3.add(jsp2);

		panel.add(p1,"p1");
		panel.add(p2,"p2");
		panel.add(p3,"p3");
		panel.add(p4,"p4");

		card.show(panel,"p4");
		 
		getContentPane().add(panel,BorderLayout.NORTH);
		
		
}
public void actionPerformed(ActionEvent ae){
	if(ae.getSource() == i1){
		card.show(panel,"p1");
	}
	if(ae.getSource() == i2){
		 Algorithm1 alg = new Algorithm1();
		 alg.startProcess(table1,dtm2);
		card.show(panel,"p2");
	}
	if(ae.getSource() == i3){
		card.show(panel,"p3");
	}
	if(ae.getSource() == i6){
		 alg = new Algorithm2();
		 alg.startProcess(table2);
		 ViewTree vt = new ViewTree();
		 vt.addNode(alg.getBean());
		 panel.add(vt,"vt");
		 card.show(panel,"vt");
	}
	if(ae.getSource() == i7){
		 CompleteTree vt = new CompleteTree();
		 vt.addNode(alg.getBean());
		 panel.add(vt,"vt");
		 card.show(panel,"vt");
	}
	if(ae.getSource() == i5){
		System.exit(0);
	}
	if(ae.getSource() == i4){
		int count1=0;int count2=0;
		for(int i=0;i<alg.getBean().size();i++){
		IPEBean bn = alg.getBean().get(i);
		String para = bn.getSelected();
		if(para != null){
			count1+=1;
		}
		}
		for(int i=0;i<alg.getBean().size();i++){
		IPEBean bn = alg.getBean().get(i);
		String para = bn.getPrune();
		if(para != null){
			count2+=1;
		}
		}
		ArrayList<String> list = new ArrayList<String>();
		list.add(count1+",Partial Conflict");
		list.add(count2+",Complete Conflict");
		Chart chart1 = new Chart("PATTERN TAXONOMY MODEL",list);
		chart1.pack();
		RefineryUtilities.centerFrameOnScreen(chart1);
		chart1.setVisible(true);
	}
}
public void clearTable(){
	for(int i=dtm1.getRowCount()-1;i>=0;i--){
		dtm1.removeRow(i);
	}
	for(int i=dtm2.getRowCount()-1;i>=0;i--){
		dtm2.removeRow(i);
	}
}

 public void loadDB(File file){
	 try{
		 String str=null;
		 int count=1;
		 BufferedReader br=new BufferedReader(new FileReader(file));
		 while ((str =br.readLine()) != null) {
			 Object row[]={"dp"+count,str};
			 dtm1.addRow(row);
			 count++;
		 }
		 br.close();
		
	 }catch(Exception e){
		 e.printStackTrace();
	 }
}


 public static void main(String a[])throws Exception{
	 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	 Patterns screen = new Patterns();
	 screen.setVisible(true);
	 screen.setResizable(false);
	 screen.pack();
 }
}