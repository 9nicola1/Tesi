package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import it.unical.dimes.gridlab.tesi.a2019.Twitter.saving.Saving;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.taking.Searching;
import twitter4j.Status;

public class ViewController extends Thread implements ViewControllerInteface{
	private Searching searching;
	private DefaultListModel<String> listModel;
	private JTextField latitudine;
	private JTextField longitudine;
	private JTextField area; 
	private JTextField data;
	private String pathFile;
	private PanelTable panelTable;
	private Saving saving;
	private JButton avvia;
	private boolean normal=false;
	private boolean advance=false;


	@Override
	public void normalSearch(Searching searching, DefaultListModel<String> listModel, String pathFile,PanelTable panelTable, Saving saving, JButton avvia) {
		this.searching=searching;
		this.listModel=listModel;
		this.pathFile=pathFile;
		this.panelTable=panelTable;
		this.saving=saving;
		this.avvia=avvia;
		normal=true;
		advance=false;
		Thread thread=new Thread(this);
		thread.start();
	}//normalSearch

	@Override
	public void advanceSearch(Searching searching, DefaultListModel<String> listModel, JTextField latitudine,
			JTextField longitudine, JTextField area, JTextField data, String pathFile, PanelTable panelTable,
			Saving saving, JButton avvia) {
		this.searching=searching;
		this.listModel=listModel;
		this.latitudine=latitudine;
		this.longitudine=longitudine;
		this.area=area;
		this.data=data;
		this.pathFile=pathFile;
		this.panelTable=panelTable;
		this.saving=saving;
		this.avvia=avvia;
		normal=false;
		advance=true;
		Thread thread=new Thread(this);
		thread.start();;
	}//advanceSearch
	
	@Override
	public void run() {
		if(normal) {
			avvia.setText("Ricerca in corso...");
			avvia.setEnabled(false);
			if(listModel.size()==0) {
				JOptionPane.showMessageDialog(null, "Nessuna parola chiave inserita. Ripetere","Errore", JOptionPane.ERROR_MESSAGE);
			}
			else if(pathFile.equals("")) {
				JOptionPane.showMessageDialog(null, "Nessun file selezionato su cui salvare.","Attenzione", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				LinkedList<String>listKey=new LinkedList<String>();
				for(int i=0; i<listModel.size(); i++) {
					listKey.add(listModel.getElementAt(i));
				}
				try {
					Date currentDate = new Date();
			        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			        String currentTime=sdf.format(currentDate);
			        List<Status>status=searching.getTweetFromListHashtag(listKey, 100);
			        if(status.size()!=0) {
				        for(Status s:status) {
							Object[]obj= {currentTime,s.getUser().getName(), s.getText().toString(), 
									(s.getPlace()==null)?"":s.getPlace().getFullName(), (s.getGeoLocation()==null)?"":s.getGeoLocation().getLatitude(),
									(s.getGeoLocation()==null)?"":s.getGeoLocation().getLongitude() };
							panelTable.dtm.addRow(obj);
						}
				        saving.saveStatusAndImageAndOthers(status, pathFile);
				        saving.saveListOnTXT(status, "it.unical.dimes.gridlab.tesi.a2019.Twitter.source\\Staus.txt");
				    }else{
				    	JOptionPane.showMessageDialog(null, "La ricerca non ha prodotto alcun risultato","Nessuno Stato", JOptionPane.INFORMATION_MESSAGE);
		
				    }
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Dati Errati. Inserire nuovamente","Errore", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}	
			}
			avvia.setText("AVVIA");
			avvia.setEnabled(true);
		}else if(advance) {
			avvia.setText("Ricerca in corso...");
			avvia.setEnabled(false);
			if(listModel.size()==0) {
				JOptionPane.showMessageDialog(null, "Nessuna parola chiave inserita. Ripetere","Errore", JOptionPane.ERROR_MESSAGE);
			}
			LinkedList<String>listKey=new LinkedList<String>();
			for(int i=0; i<listModel.size(); i++) {
				listKey.add(listModel.getElementAt(i));
			}
			if(!latitudine.getText().equals("") && !latitudine.getText().equals("Es. 39.3099931")
					&& !longitudine.getText().equals("") && !longitudine.getText().equals("Es. 16.2501929")
					&& !area.getText().equals("") && !area.getText().equals("Specificare l'area di circonferenza, in miglia")) {
				try {
					double lat=Double.parseDouble(latitudine.getText());
					double lon=Double.parseDouble(longitudine.getText());
					long x=(long) lat;
					long y=(long)lon;
					int km=Integer.parseInt(area.getText());
				//	while(true) {
						Date currentDate = new Date();
				        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				        String currentTime=sdf.format(currentDate);
				        List<Status>status=searching.getTweetFromListHashtag(listKey, 100, x, y, km, data.getText());
				        if(status.size()!=0) {
					        for(Status s:status) {
								Object[]obj= {currentTime,s.getUser().getName(), s.getText().toString(), 
										(s.getPlace()==null)?"":s.getPlace().getFullName(), (s.getGeoLocation()==null)?"":s.getGeoLocation().getLatitude(),
										(s.getGeoLocation()==null)?"":s.getGeoLocation().getLongitude() };
								panelTable.dtm.addRow(obj);
							}
					        saving.saveStatusAndImageAndOthers(status, pathFile);
					        saving.saveListOnTXT(status, "it.unical.dimes.gridlab.tesi.a2019.Twitter.source\\Staus.txt");
					//		Thread.sleep(10000);
					    }else{
					    	JOptionPane.showMessageDialog(null, "La ricerca non ha prodotto alcun risultato","Nessuno Stato", JOptionPane.INFORMATION_MESSAGE);
		
					    }
				//	}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Dati Errati. Inserire nuovamente","Errore", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}	
			}
			avvia.setText("AVVIA");
			avvia.setEnabled(true);
		}
	}//run

}//ViewController
