package Twitter.launcher;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

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
	private JButton search=new JButton("SEARCH");
	public LauncherGui() {
		searching=new Searching(APIKey, APISecret, AccessToken, AccessTokenSecret);
		setSize(350,400);
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
					if(!hashtag.getText().equals("")&& !hashtag.getText().equals("Scrivi qui uno o piu' hashtag separati da virgole")) {
						if(!pathFile.equals("")) {
							String keyWord=hashtag.getText();
							List<Status>status=null;
							try {
								status=searching.getTweetFromHashtag(keyWord, 100);
								for(Status s:status)
									System.out.println(s);
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
	
	public static void main(String[]args) {
		LauncherGui lG=new LauncherGui();
	}//main

}//LauncherGui
