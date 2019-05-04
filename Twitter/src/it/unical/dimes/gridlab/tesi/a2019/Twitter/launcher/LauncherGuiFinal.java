package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.portable.InputStream;

import it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher.LauncherGui.InitialPanel;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.saving.Saving;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.saving.SavingAbstract;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.taking.Searching;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.taking.SearchingAbstract;
import javafx.scene.paint.Color;
import jdk.nashorn.tools.Shell;
import twitter4j.Status;
import twitter4j.TwitterException;
public class LauncherGuiFinal extends JFrame {
	private String APIKey="hn3UBOFrP4WBLFVjqsCcYB3Dt";										//Application Settings/Consumer Key (API key)
	private String APISecret="ovCke2aWfdW6TvhZ0dggL5mO320mzMlLZwDgcvNVUqlUsZbp9J";	  //Application Settings/Consumer Secret (API Secret)
	private String AccessToken="1113747010694340608-csRTyytaX9TqM6zJLN3yPl5O0V2Yos";					 //Your Access Token/Access Token
	private String AccessTokenSecret="QB57vcKjJLXQbqQXKQ4I5n6wfvVFuUBkS3MfhOC81sjzA";			  //Your Access Token/Access Token Secret
	private static Searching searching=null;
	private static Saving saving=new Saving();
	private JTextField hashtag=new JTextField("");
	private JLabel hashtagLabel=new JLabel("HashTag / Parole Chiavi");
	private JButton buttonFile=new JButton("SCEGLI DOVE SALVARE");
	private JButton buttonFileAvanzata=new JButton("SCEGLI DOVE SALVARE");
	private JLabel checkLabel=new JLabel("Ricerca avanzata");
	private JCheckBox check=new JCheckBox();
	private JTextField latitudine=new JTextField("Es. 39.3099931");
	private JLabel latitudineLabel=new JLabel("Latitudine");
	private JTextField longitudine=new JTextField("Es. 16.2501929");
	private JLabel longitudineLabel=new JLabel("Longitudine");
	private JTextField area=new JTextField("Area in miglia");
	private JLabel areaLabel=new JLabel("Area");
	private JLabel pathFileLabel=new JLabel("Nessun file scelto");
	private JLabel pathFileLabelAvanzata=new JLabel("Nessun file scelto");
	private JLabel giornoLabel=new JLabel("Giorno");
	private JTextField giorno=new JTextField("yyyy_mm_dd");
	private String pathFile="";
	private String pathFileAvanzata="";
	private JButton search=new JButton("AVVIA");
	private JButton searchAvanzata=new JButton("AVVIA");
	private JLabel listHashtagLabel=new JLabel("HashTag / Parole Chiavi");
	private JTextField listHashtag=new JTextField(12);
	private JButton insertHashtagAvanzata=new JButton("INSERISCI HASHTAG");
	private JButton insertHashtag=new JButton("INSERISCI HASHTAG");
	private JButton removeHashtagAvanzata=new JButton("SVUOTA HASHTAG");
	private JButton removeHashtag=new JButton("SVUOTA HASHTAG");
	private JPanel panelContainer=new JPanel();
	private JPanel containerTable=new JPanel();
	private JPanel containerDati=new JPanel();
	private JPanel normalResearch=new JPanel();
	private JPanel advanceResearch=new JPanel();
	private PanelTable panelTable=new PanelTable();
	private DefaultListModel<String> listModel = new DefaultListModel<>();
	private DefaultListModel<String> listModelAvanzata = new DefaultListModel<>();
	private ViewController controller=new ViewController();
	private Object frame;
	public LauncherGuiFinal() {
		searching=new Searching(APIKey, APISecret, AccessToken, AccessTokenSecret);
		//Dimension d=getMaximumSize(); 
		//setSize(d.width, d.height);
		ImageIcon img = new ImageIcon("icon.png");
		setIconImage(img.getImage());
		setSize(1480,920);
		setTitle("Twitter Analysis");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new InitialPanel());
		giorno.setEditable(false);
		latitudine.setEditable(false);
		longitudine.setEditable(false);
		area.setEditable(false);
		listHashtag.setEditable(false);
		insertHashtagAvanzata.setEnabled(false);
		buttonFileAvanzata.setEnabled(false);
		removeHashtagAvanzata.setEnabled(false);
		searchAvanzata.setEnabled(false);
		removeHashtag.setEnabled(false);

		
	}//Constructor
	
	class InitialPanel extends JPanel{
		public InitialPanel() {
			panelContainer.setLayout(new GridLayout(1,2));
			setLayout(new GridLayout(1,2));
			normalResearch.setLayout(new GridBagLayout());
			JLabel normalLabel=new JLabel("RICERCA STANDARD");
			Font font = new Font("Courier", Font.BOLD,22);
			normalLabel.setFont(font);
			GridBagConstraints gbcNR = new GridBagConstraints();
			gbcNR.fill = GridBagConstraints.HORIZONTAL;
	        gbcNR.gridx = 0;
	        gbcNR.gridy = 0;
			normalResearch.add(normalLabel, gbcNR);
	        gbcNR.gridx = 0;
	        gbcNR.gridy = 1;
			normalResearch.add(hashtagLabel, gbcNR);
			gbcNR.gridx = 0;
	        gbcNR.gridy = 2;
			normalResearch.add(hashtag, gbcNR);
			gbcNR.gridx = 1;
	        gbcNR.gridy = 2;
			normalResearch.add(insertHashtag, gbcNR);
	        insertHashtag.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					if(!hashtag.getText().equals("")) {
						listModel.addElement(hashtag.getText());
						hashtag.setText("");	
						removeHashtag.setEnabled(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Nessun hashtag inserito","Errore", JOptionPane.ERROR_MESSAGE);
					}
				}});
	        final JList<String> listKey =new JList<String>(listModel);
			JScrollPane scrollPane=new JScrollPane(listKey);
			scrollPane.setPreferredSize(new Dimension(50,50));
	        gbcNR.gridx = 0;
	        gbcNR.gridy = 3;
	        normalResearch.add(scrollPane, gbcNR);
	        gbcNR.gridx = 1;
	        gbcNR.gridy = 3;
	        normalResearch.add(removeHashtag, gbcNR);
	        
			gbcNR.gridx = 0;
		    gbcNR.gridy = 4;
		    gbcNR.fill = GridBagConstraints.HORIZONTAL;
	        gbcNR.gridwidth = 2;
			normalResearch.add(buttonFile,gbcNR);
			gbcNR.gridx = 0;
		    gbcNR.gridy = 5;
		    gbcNR.fill = GridBagConstraints.HORIZONTAL;
	        gbcNR.gridwidth = 2;
			normalResearch.add(pathFileLabel, gbcNR);
			gbcNR.gridx = 0;
		    gbcNR.gridy = 6;
		    gbcNR.fill = GridBagConstraints.HORIZONTAL;
	        gbcNR.gridwidth = 2;
			normalResearch.add(search, gbcNR);
			gbcNR.gridx = 0;
		    gbcNR.gridy = 7;
			normalResearch.add(checkLabel, gbcNR);
			gbcNR.gridx = 1;
		    gbcNR.gridy = 7;
			normalResearch.add(check, gbcNR);

			advanceResearch.setLayout(new GridBagLayout());
			GridBagConstraints gbcAR = new GridBagConstraints();
			JLabel labelAvanzata=new JLabel("RICERCA AVANZATA");
			labelAvanzata.setFont(font);
			gbcAR.fill = GridBagConstraints.HORIZONTAL;
			gbcAR.gridx = 0;
	        gbcAR.gridy = 0;
	        advanceResearch.add(labelAvanzata, gbcAR);
	        gbcAR.gridx = 0;
	        gbcAR.gridy = 1;
	        advanceResearch.add(giornoLabel, gbcAR);
	        gbcAR.gridx = 1;
	        gbcAR.gridy = 1;
	        advanceResearch.add(giorno, gbcAR);
	        gbcAR.gridx = 0;
	        gbcAR.gridy = 2;
	        advanceResearch.add(latitudineLabel, gbcAR);
	        gbcAR.gridx = 1;
	        gbcAR.gridy = 2;
	        advanceResearch.add(latitudine, gbcAR);
	        gbcAR.gridx = 0;
	        gbcAR.gridy = 3;
	        advanceResearch.add(longitudineLabel, gbcAR);
	        gbcAR.gridx = 1;
	        gbcAR.gridy = 3;
	        advanceResearch.add(longitudine, gbcAR);
	        gbcAR.gridx = 0;
	        gbcAR.gridy = 4;
	        advanceResearch.add(areaLabel, gbcAR);
	        gbcAR.gridx = 1;
	        gbcAR.gridy = 4;
	        advanceResearch.add(area, gbcAR);
	        gbcAR.gridx = 0;
	        gbcAR.gridy = 5;
	        advanceResearch.add(listHashtagLabel, gbcAR);
	        gbcAR.gridx = 0;
	        gbcAR.gridy = 6;
	        advanceResearch.add(listHashtag, gbcAR);
	        gbcAR.gridx = 1;
	        gbcAR.gridy = 6;
	        advanceResearch.add(insertHashtagAvanzata, gbcAR);
	        insertHashtagAvanzata.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					if(!listHashtag.getText().equals("")) {
						listModelAvanzata.addElement(listHashtag.getText());
						listHashtag.setText("");	
						removeHashtagAvanzata.setEnabled(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Nessun hashtag inserito","Errore", JOptionPane.ERROR_MESSAGE);
					}
				}});
	        final JList<String> listKeyAvanzata =new JList<String>(listModelAvanzata);
			JScrollPane scrollPaneAvanzata=new JScrollPane(listKeyAvanzata);
			scrollPaneAvanzata.setPreferredSize(new Dimension(50,50));
	        gbcAR.gridx = 0;
	        gbcAR.gridy = 7;
	        advanceResearch.add(scrollPaneAvanzata, gbcAR);
	        gbcAR.gridx = 1;
	        gbcAR.gridy = 7;
	        advanceResearch.add(removeHashtagAvanzata, gbcAR);
	        
			gbcAR.gridx = 0;
		    gbcAR.gridy = 8;
		    gbcAR.fill = GridBagConstraints.HORIZONTAL;
	        gbcAR.gridwidth = 2;
	        advanceResearch.add(buttonFileAvanzata,gbcAR);
			gbcAR.gridx = 0;
		    gbcAR.gridy = 9;
		    gbcAR.fill = GridBagConstraints.HORIZONTAL;
	        gbcAR.gridwidth = 2;
	        advanceResearch.add(pathFileLabelAvanzata, gbcAR);
			gbcAR.gridx = 0;
		    gbcAR.gridy = 10;
		    gbcAR.fill = GridBagConstraints.HORIZONTAL;
	        gbcAR.gridwidth = 2;
	        advanceResearch.add(searchAvanzata, gbcAR);
			
	        removeHashtagAvanzata.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					listModelAvanzata.removeAllElements();
					removeHashtagAvanzata.setEnabled(false);
					}});
	        removeHashtag.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					listModel.removeAllElements();
					removeHashtag.setEnabled(false);
					}});
			containerTable.add(panelTable, BorderLayout.NORTH);
			containerDati.setLayout(new GridLayout(3,1));
			containerDati.add(normalResearch);
			containerDati.add(advanceResearch);
			String url="icon.png";
			ImageIcon icone=new ImageIcon(url);
			JLabel label=new JLabel(icone, JLabel.CENTER);
			containerDati.add(label);
			add(panelContainer);
			panelContainer.add(containerDati);
			panelContainer.add(panelTable);
			check.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					if(check.isSelected()) {
						giorno.setEditable(true);
						latitudine.setEditable(true);
						longitudine.setEditable(true);
						area.setEditable(true);
						listHashtag.setEditable(true);
						insertHashtagAvanzata.setEnabled(true);
						hashtag.setEditable(false);
						buttonFileAvanzata.setEnabled(true);
						buttonFile.setEnabled(false);
					//	removeHashtagAvanzata.setEnabled(true);
						search.setEnabled(false);
						searchAvanzata.setEnabled(true);
						insertHashtag.setEnabled(false);
						removeHashtag.setEnabled(false);
						if(listModelAvanzata.isEmpty())
							removeHashtagAvanzata.setEnabled(false);
						else
							removeHashtagAvanzata.setEnabled(true);
					}
					else if(!check.isSelected()) {
						giorno.setText("yyyy_mm_dd");
						latitudine.setText("Es. 39.3099931");
						longitudine.setText("Es. 16.2501929");
						area.setText("Area in miglia");
						hashtag.setEditable(true);
						giorno.setEditable(false);
						latitudine.setEditable(false);
						longitudine.setEditable(false);
						area.setEditable(false);
						listHashtag.setEditable(false);
						insertHashtagAvanzata.setEnabled(false);
						buttonFileAvanzata.setEnabled(false);
						buttonFile.setEnabled(true);
						removeHashtagAvanzata.setEnabled(false);
						search.setEnabled(true);
						searchAvanzata.setEnabled(false);
						insertHashtag.setEnabled(true);
					//	removeHashtag.setEnabled(true);
						if(listModel.isEmpty())
							removeHashtag.setEnabled(false);
						else
							removeHashtag.setEnabled(true);
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
			buttonFileAvanzata.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JFileChooser fileChooser=new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					int returnValue = fileChooser.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						pathFileAvanzata=selectedFile.getPath();
						pathFileLabelAvanzata.setText(pathFileAvanzata);
					}
				}
			});
			search.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					controller.normalSearch(searching, listModel, pathFile, panelTable, saving);
				}});
			searchAvanzata.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					controller.advanceSearch(searching, listModelAvanzata, latitudine, longitudine, area, giorno, pathFileAvanzata, panelTable, saving);
				}});
		}//Constructor
	}//InitialPanel
	public static void main(String...args) throws IOException {
		LauncherGuiFinal launcher=new LauncherGuiFinal();
	}
}
