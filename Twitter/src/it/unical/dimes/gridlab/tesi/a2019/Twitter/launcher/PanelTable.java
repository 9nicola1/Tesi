package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.prism.paint.Color;

import javafx.scene.layout.Border;

public class PanelTable extends JPanel{
	public String[]columnNames={"Orario prelievo","Autore", "Stato", "Place", "Latitudine", "Longitudine" };
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
		setBorder(new EtchedBorder(EtchedBorder.RAISED));
		setLayout(new GridBagLayout());
		JLabel label=new JLabel("RISULTATI RICERCA");
		JButton button=new JButton("SVUOTA TABELLA",new ImageIcon(getClass().getResource("trash.png")));
		Font font = new Font("Courier", Font.BOLD,22);
		label.setFont(font);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
		add(label, gbc);
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(700,780));
		table.setFillsViewportHeight(true);
        gbc.gridx = 0;
        gbc.gridy = 2;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
		add(scrollPane, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridwidth = 2;
		add(button, gbc);
        button.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				if(dtm.getRowCount()==0) {
					JOptionPane.showMessageDialog(null, "Tabella vuota!","ATTENZIONE", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					while(dtm.getRowCount()!=0)
						dtm.removeRow(0);
				}
			}});
	}//Constructor
}//PanelTable
