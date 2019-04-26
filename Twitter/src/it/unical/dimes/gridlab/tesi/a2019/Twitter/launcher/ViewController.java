package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import it.unical.dimes.gridlab.tesi.a2019.Twitter.saving.Saving;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.taking.Searching;
import twitter4j.Status;
import twitter4j.TwitterException;

public class ViewController implements ViewControllerInteface {
	private ThreadSearching threadSearching=new ThreadSearching();
	
	@Override
	public void search(Searching searching, JTextField hashtag, JTextField latitudine, JTextField longitudine,
			JTextField area, JTextField data, String pathFile, PanelTable panelTable, Saving saving) {
		if(pathFile.equals("Nessun file scelto")|| pathFile.equals(""))
			JOptionPane.showMessageDialog(null, "File non selezionato!","Errore", JOptionPane.ERROR_MESSAGE);
		if(hashtag.getText().equals("")|| hashtag.getText().equals("Scrivi qui uno o piu' hashtag separati da virgole")) {
			JOptionPane.showMessageDialog(null, "Dati Errati. Inserire nuovamente","Errore", JOptionPane.ERROR_MESSAGE);
			hashtag.setText("");
		}
		else if(!hashtag.getText().equals("")&& !hashtag.getText().equals("Scrivi qui uno o piu' hashtag separati da virgole")
				&& !latitudine.getText().equals("") && !latitudine.getText().equals("Es. 39.3099931")
				&& !longitudine.getText().equals("") && !longitudine.getText().equals("Es. 16.2501929")
				&& !area.getText().equals("") && !area.getText().equals("Specificare l'area di circonferenza, in miglia")) {
			try {
				double lat=Double.parseDouble(latitudine.getText());
				double lon=Double.parseDouble(longitudine.getText());
				long x=(long) lat;
				long y=(long)lon;
				int km=Integer.parseInt(area.getText());
				while(true) {
					Date currentDate = new Date();
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
			        String currentTime=sdf.format(currentDate);
			        List<Status>status=searching.getTweetFromHashtagAndLocation(hashtag.getText(), 100, x, y, km, currentTime);
			        for(Status s:status) {
						System.out.println(s);
						Object[]obj= {currentTime, s.getText().toString()};
						panelTable.dtm.addRow(obj);
					}
					saving.saveListOnCSV(status, pathFile);
					Thread.sleep(10000);
				}
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Dati Errati. Inserire nuovamente","Errore", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();

			}						
		}
		else if(!hashtag.getText().equals("") && !hashtag.getText().equals("Scrivi qui uno o piu' hashtag separati da virgole")){
			if(!pathFile.equals("")) {
				String keyWord=hashtag.getText();
				List<Status>status=null;
				try {
					while(true) {
						status=null;
						status=searching.getTweetFromHashtag(keyWord, 100);
						Date currentDate = new Date();
				        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				        String currentTime=sdf.format(currentDate);
				        threadSearching.sleepAndUpdate(900000, panelTable, currentTime, status);
						saving.saveStatusAndImageAndOthers(status, pathFile);	
						panelTable.repaint();
					//	Thread.sleep(900000);	//15 minuti
					}
				}catch(TwitterException | IOException e1) {
					JOptionPane.showMessageDialog(null, "Dati Errati. Inserire nuovamente","Errore", JOptionPane.ERROR_MESSAGE);

					e1.printStackTrace();
				}
			}
		}
	}//search
}//Controller
