package effective;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
public class ViewTree extends JPanel
{
	JTree tree;
	DefaultMutableTreeNode node;
	JScrollPane jsp;
	
public ViewTree(){
	setLayout(new BorderLayout());
	node = new DefaultMutableTreeNode("ROOT");
	tree = new JTree(node);
	tree.setEditable(false);
	jsp = new JScrollPane(tree);
	add(jsp,BorderLayout.CENTER);
}
public void addNode(ArrayList<IPEBean> bean){
	for(int i=0;i<bean.size();i++){
		IPEBean bn = bean.get(i);
		String para = bn.getSelected();
		if(para != null){
			String p[]=para.split(",");
			StringBuffer sb = new StringBuffer();
			for(int j=0;j<p.length;j++){
				sb.append(Character.toString(p[j].charAt(p[j].length()-1))+",");
			}
			if(sb.length() > 0)
				sb.deleteCharAt(sb.length()-1);
			DefaultMutableTreeNode tr = new DefaultMutableTreeNode("{"+bn.getTerm()+"}["+sb.toString()+"]");
			node.add(tr);
		}
	}
	int row = 0;
    while (row < tree.getRowCount()) {
      tree.expandRow(row);
      row++;
      }
}


}