package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import it.unical.dimes.gridlab.tesi.a2019.Twitter.saving.Saving;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.taking.Searching;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.URLEntity;

public class ViewController extends SwingWorker<String, Object> implements ViewControllerInteface {
	private ThreadSearching threadSearching=new ThreadSearching();
	
	@Override
	public void search(Searching searching, JTextField hashtag, JTextField latitudine, JTextField longitudine,
			JTextField area, JTextField data, String pathFile, PanelTable panelTable, Saving saving, JCheckBox check) {
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
			        if(status.size()!=0) {
				        for(Status s:status) {
							System.out.println(s);
							Object[]obj= {currentTime, s.getText().toString()};
							panelTable.dtm.addRow(obj);
						}
						saving.saveListOnCSV(status, pathFile);
						Thread.sleep(10000);
				    }else{
				    	JOptionPane.showMessageDialog(null, "La ricerca non ha prodotto alcun risultato","Nessuno Stato", JOptionPane.INFORMATION_MESSAGE);
						hashtag.setText("");
				    }
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
				//	while(true) {
						status=null;
						status=searching.getTweetFromHashtag(keyWord, 100);
						Date currentDate = new Date();
				        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				        String currentTime=sdf.format(currentDate);
				    //    threadSearching.sleepAndUpdate(900000, panelTable, currentTime, status);
						saving.saveStatusAndImageAndOthers(status, pathFile);	
						if(status.size()!=0) {
							for(Status s:status) {
								Object[]obj= {currentTime,s.getUser().getName(), s.getText().toString(), 
										(s.getPlace()==null)?"":s.getPlace().getFullName(), (s.getGeoLocation()==null)?"":s.getGeoLocation().getLatitude(),
										(s.getGeoLocation()==null)?"":s.getGeoLocation().getLongitude() };
								panelTable.dtm.addRow(obj);
							}
							panelTable.repaint();

						//	Thread.sleep(900000);	//15 minuti
						}else{
							JOptionPane.showMessageDialog(null, "La ricerca non ha prodotto alcun risultato","Nessuno Stato", JOptionPane.INFORMATION_MESSAGE);
							hashtag.setText("");
						}
			//		}
				}catch(TwitterException | IOException e1) {
					JOptionPane.showMessageDialog(null, "Dati Errati. Inserire nuovamente","Errore", JOptionPane.ERROR_MESSAGE);

					e1.printStackTrace();
				}
			}
		}
	}//search
	
	
	@Override
	protected String doInBackground() throws Exception {
		while(true) {
			
		}
	}
}//Controller
