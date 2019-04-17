package Twitter.launcher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

import Twitter.saving.Saving;
import Twitter.saving.SavingAbstract;
import Twitter.taking.Searching;
import Twitter.taking.SearchingAbstract;
import twitter4j.Status;
import twitter4j.TwitterException;

public class LauncherGui extends JFrame{
	private String APIKey="hn3UBOFrP4WBLFVjqsCcYB3Dt";										//Application Settings/Consumer Key (API key)
	private String APISecret="ovCke2aWfdW6TvhZ0dggL5mO320mzMlLZwDgcvNVUqlUsZbp9J";	  //Application Settings/Consumer Secret (API Secret)
	private String AccessToken="1113747010694340608-csRTyytaX9TqM6zJLN3yPl5O0V2Yos";					 //Your Access Token/Access Token
	private String AccessTokenSecret="QB57vcKjJLXQbqQXKQ4I5n6wfvVFuUBkS3MfhOC81sjzA";			  //Your Access Token/Access Token Secret
	private static SearchingAbstract searching=null;
	private static SavingAbstract saving=new Saving();
	private JTextField hashtag=new JTextField("Scrivi qui uno o piu' hashtag separati da virgole");
	private JLabel hashtagLabel=new JLabel("HashTag");
	private JButton buttonFile=new JButton("SCEGLI DOVE SALVARE");
	private JTextField latitudine=new JTextField("Es. 39.3099931");
	private JLabel latitudineLabel=new JLabel("Latitudine");
	private JTextField longitudine=new JTextField("Es. 16.2501929");
	private JLabel longitudineLabel=new JLabel("Longitudine");
	private JTextField area=new JTextField("Specificare l'area di circonferenza, in miglia");
	private JLabel areaLabel=new JLabel("Area");
	private JLabel pathFileLabel=new JLabel("Nessun file scelto");
	private String pathFile="";
	private JButton search=new JButton("AVVIA");
	private JPanel container=new JPanel();
	private PanelTable panelTable=new PanelTable();
	public LauncherGui() {
		searching=new Searching(APIKey, APISecret, AccessToken, AccessTokenSecret);
		setSize(900,700);
		setTitle("TWITTER");
		setLocation(800, 300);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new InitialPanel());
	}//Constructor
	
	class InitialPanel extends JPanel{
		public InitialPanel() {
			add(hashtagLabel, BorderLayout.NORTH);
			add(hashtag, BorderLayout.NORTH);
			add(latitudineLabel,BorderLayout.NORTH);
			add(latitudine,BorderLayout.NORTH);
			add(longitudineLabel, BorderLayout.NORTH);
			add(longitudine,BorderLayout.NORTH);
			add(areaLabel,BorderLayout.NORTH);
			add(area,BorderLayout.NORTH);
			add(buttonFile, BorderLayout.CENTER);
			add(pathFileLabel, BorderLayout.CENTER);
			add(search, BorderLayout.CENTER);
			container.add(panelTable, BorderLayout.NORTH);
			add(container, BorderLayout.SOUTH);
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
					if(hashtag.getText().equals("")|| hashtag.getText().equals("Scrivi qui uno o piu' hashtag separati da virgole")) {
						JOptionPane.showMessageDialog(null, this, "ERRORE!", JOptionPane.ERROR_MESSAGE);
						hashtag.setText("");
					}
					else if(!hashtag.getText().equals("")&& !hashtag.getText().equals("Scrivi qui uno o piu' hashtag separati da virgole")
							&& !latitudine.getText().equals("") && !latitudine.getText().equals("Es. 39.3099931")
							&& !longitudine.getText().equals("") && !longitudine.getText().equals("Es. 16.2501929")
							&& !area.getText().equals("") && !area.getText().equals("Specificare l'area di circonferenza, in miglia")) {
						try {
							long lat=(Long)Long.parseLong(latitudine.getText());
							long lon=Long.parseLong(longitudine.getText());
							int km=Integer.parseInt(area.getText());
							Date currentDate = new Date();
					        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					        String currentTime=sdf.format(currentDate);
							System.out.println("latitudine "+lat);
							System.out.println("longitudine "+lon);
							System.out.println("data "+currentTime);
					        List<Status>status=searching.getTweetFromHashtagAndLocation(hashtag.getText(), 100, lat, lon, km, currentTime);
						}catch(Exception e1) {
							JOptionPane.showMessageDialog(null, this, "DATI ERRATI!", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();

						}						
					}
					else if(!hashtag.getText().equals("") && !hashtag.getText().equals("Scrivi qui uno o piu' hashtag separati da virgole")){
						if(!pathFile.equals("")) {
							String keyWord=hashtag.getText();
							List<Status>status=null;
							try {
								status=searching.getTweetFromHashtag(keyWord, 100);
								Date currentDate = new Date();
						        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						        String currentTime=sdf.format(currentDate);
								for(Status s:status) {
									System.out.println(s);
									Object[]obj= {currentTime, s.getText().toString()};
									panelTable.dtm.addRow(obj);
								}
								saving.saveList(status, pathFile);		
							}catch(TwitterException | IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			});
		}//Constructor
	}//InitialPanel
	
	class PanelTable extends JPanel{
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
	
	public static void main(String[]args) {
		LauncherGui lG=new LauncherGui();
	}//main

}//LauncherGui
