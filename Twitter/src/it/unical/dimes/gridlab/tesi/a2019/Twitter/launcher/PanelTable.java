package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelTable extends JPanel{
	public String[]columnNames={"Orario prelievo", "Stato"};
	public Object[][]data={};
	public DefaultTableModel dtm=new DefaultTableModel(data, columnNames){
		@Override
		public boolean isCellEditable(int row, int column){
			//all cells false
			return false;
		}
	};;
	public JTable table=new JTable(dtm);
	public PanelTable(){
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(700,780));
		table.setFillsViewportHeight(true);
		add(scrollPane);
	}
}//PanelTable
