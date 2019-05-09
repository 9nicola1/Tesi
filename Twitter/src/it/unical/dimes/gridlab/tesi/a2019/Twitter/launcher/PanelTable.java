package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;

import java.awt.Color;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import javafx.scene.layout.Border;

public class PanelTable extends JPanel{
	public String[]columnNames={"ORARIO PRELIEVO","AUTORE", "STATO", "LUOGO", "LATITUDINE", "LONGITUDINE" };
	public Object[][]data={};
	private JLabel label=new JLabel(new ImageIcon(getClass().getResource("risultatiRicerca.png")));
	private JButton button=new JButton("",new ImageIcon(getClass().getResource("svuotaTabella.png")));
	private Font font = new Font("Courier", Font.BOLD,22);
	private Font font2 = new Font("Courier", Font.ITALIC,12);
	private Font font3 = new Font("Arial", Font.BOLD,12);
	public DefaultTableModel dtm=new DefaultTableModel(data, columnNames){
		@Override
		public boolean isCellEditable(int row, int column){
			//all cells false
			return false;
		}
	};;
	public JTable table=new JTable(dtm);
	public PanelTable(){
		
		table.setForeground(Color.DARK_GRAY);
		setBorder(new LineBorder(Color.DARK_GRAY,3));
		setLayout(new GridBagLayout());
		setBackground(new Color(95,95,95));
		table.setFont(font2);
		table.setForeground(Color.WHITE);
		
		label.setBorder(new LineBorder(Color.WHITE, 1));
		label.setForeground(Color.WHITE);
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
		scrollPane.setBackground(new Color(95,95,95));
		scrollPane.getViewport().getView().setBackground(new Color(95,95,95));
		JScrollBar horizontalBar=scrollPane.getVerticalScrollBar();
		horizontalBar.setBackground(Color.DARK_GRAY);

		
		scrollPane.setBorder(new LineBorder(Color.DARK_GRAY, 3));
		table.setFillsViewportHeight(true);
		JTableHeader jth=table.getTableHeader();
		jth.setBackground(Color.DARK_GRAY);
		jth.setFont(font3);
		jth.setForeground(Color.WHITE);
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
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
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
