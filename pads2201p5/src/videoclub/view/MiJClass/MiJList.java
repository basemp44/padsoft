package videoclub.view.MiJClass;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class MiJList extends JList<String> {
	private DefaultListModel<String> model = new DefaultListModel<String>();
	
	public MiJList(){
		this.setModel(model);
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}
	
	public void addElement(String s){
		this.model.addElement(s);
	}
}
