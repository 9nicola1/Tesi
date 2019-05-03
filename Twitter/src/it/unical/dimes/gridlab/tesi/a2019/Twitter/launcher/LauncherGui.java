package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

import it.unical.dimes.gridlab.tesi.a2019.Twitter.saving.Saving;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.saving.SavingAbstract;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.taking.Searching;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.taking.SearchingAbstract;
import jdk.nashorn.tools.Shell;
import twitter4j.Status;
import twitter4j.TwitterException;

public class LauncherGui extends JFrame{
	private String APIKey="hn3UBOFrP4WBLFVjqsCcYB3Dt";										//Application Settings/Consumer Key (API key)
	private String APISecret="ovCke2aWfdW6TvhZ0dggL5mO320mzMlLZwDgcvNVUqlUsZbp9J";	  //Application Settings/Consumer Secret (API Secret)
	private String AccessToken="1113747010694340608-csRTyytaX9TqM6zJLN3yPl5O0V2Yos";					 //Your Access Token/Access Token
	private String AccessTokenSecret="QB57vcKjJLXQbqQXKQ4I5n6wfvVFuUBkS3MfhOC81sjzA";			  //Your Access Token/Access Token Secret
	private static Searching searching=null;
	private static Saving saving=new Saving();
	private JTextField hashtag=new JTextField("Scrivi qui uno o piu' hashtag separati da virgole");
	private JLabel hashtagLabel=new JLabel("HashTag");
	private JButton buttonFile=new JButton("SCEGLI DOVE SALVARE");
	private JLabel checkLabel=new JLabel("Ricerca completa");
	private JCheckBox check=new JCheckBox();
	private JTextField latitudine=new JTextField("Es. 39.3099931");
	private JLabel latitudineLabel=new JLabel("Latitudine");
	private JTextField longitudine=new JTextField("Es. 16.2501929");
	private JLabel longitudineLabel=new JLabel("Longitudine");
	private JTextField area=new JTextField("Area in miglia");
	private JLabel areaLabel=new JLabel("Area");
	private JLabel pathFileLabel=new JLabel("Nessun file scelto");
	private JLabel giornoLabel=new JLabel("Giorno");
	private JTextField giorno=new JTextField("yyyy_mm_dd");
	private String pathFile="";
	private JButton search=new JButton("AVVIA");
	private JPanel containerTable=new JPanel();
	private JPanel containerText=new JPanel();
	private PanelTable panelTable=new PanelTable();
	private ViewController controller=new ViewController();
	public LauncherGui() {
		searching=new Searching(APIKey, APISecret, AccessToken, AccessTokenSecret);
		//Dimension d=getMaximumSize(); 
		//setSize(d.width, d.height);
		setSize(1920,1080);
		setTitle("Twitter Analysis");
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new InitialPanel());
		giorno.setEditable(false);
		latitudine.setEditable(false);
		longitudine.setEditable(false);
		area.setEditable(false);
	}//Constructor
	
	class InitialPanel extends JPanel{
		public InitialPanel() {
		//	GridBuilder gb = new GridBuilder(this,    new double[] { 0, 0, 0, 1, 0 },   new double[] { 0, 1}, 3); 
		//	GridBagConstraints constraints = new GridBagConstraints();
			setLayout(new GridLayout(1,2));
			containerText.add(hashtagLabel, BorderLayout.NORTH);
			containerText.add(hashtag, BorderLayout.NORTH);
			containerText.add(giornoLabel, BorderLayout.CENTER);
			containerText.add(giorno, BorderLayout.CENTER);
			containerText.add(latitudineLabel,BorderLayout.NORTH);
			containerText.add(latitudine,BorderLayout.NORTH);
			containerText.add(longitudineLabel, BorderLayout.NORTH);
			containerText.add(longitudine,BorderLayout.NORTH);
			containerText.add(areaLabel,BorderLayout.NORTH);
			containerText.add(area,BorderLayout.NORTH);
			containerText.add(pathFileLabel, BorderLayout.CENTER);
			containerText.add(buttonFile, BorderLayout.CENTER);
			containerText.add(checkLabel, BorderLayout.CENTER);
			containerText.add(check, BorderLayout.CENTER);
			containerText.add(search, BorderLayout.CENTER);
			containerTable.add(panelTable, BorderLayout.NORTH);
			add(containerText);
			add(containerTable);
			check.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					if(check.isSelected()) {
						giorno.setEditable(true);
						latitudine.setEditable(true);
						longitudine.setEditable(true);
						area.setEditable(true);
					}
					else if(!check.isSelected()) {
						giorno.setText("yyyy_mm_dd");
						latitudine.setText("Es. 39.3099931");
						longitudine.setText("Es. 16.2501929");
						area.setText("Area in miglia");
						giorno.setEditable(false);
						latitudine.setEditable(false);
						longitudine.setEditable(false);
						area.setEditable(false);
					}
				}});
			hashtag.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					hashtag.setText("");						
				}});
			latitudine.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					latitudine.setText("");						
				}});
			longitudine.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					longitudine.setText("");						
				}});
			area.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					area.setText("");						
				}});
			giorno.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					giorno.setText("");						
				}});
			buttonFile.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JFileChooser fileChooser=new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					int returnValue = fileChooser.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						pathFile=selectedFile.getPath();
						pathFileLabel.setText(pathFile);
					}
				}
			});
			search.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					controller.normalSearch(searching, hashtag, latitudine, longitudine, area, giorno, pathFile, panelTable, saving, check);
				}});
		}//Constructor
	}//InitialPanel
	
	public static void main(String[]args) {
		LauncherGui lG=new LauncherGui();
	}//main

}//LauncherGui
