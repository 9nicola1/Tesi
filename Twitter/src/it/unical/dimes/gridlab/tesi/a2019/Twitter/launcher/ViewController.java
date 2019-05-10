package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import it.unical.dimes.gridlab.tesi.a2019.Twitter.heuristic.Iteration;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.heuristic.MonitorIteration;
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
	private int clock=15000;
	private boolean normal=false;
	private boolean advance=false;
	private boolean stop=false;
	private HashSet<Status>statusSaved=new HashSet<Status>();
	private ThreadPanel threadPanel=new ThreadPanel();
	private boolean checkIniziale=false;
	private MonitorIteration threadMonitorIteration=null;
	
	public ViewController() {
    	threadPanel.start();
	}//Constructor

	public void setStopFalse() {
		this.stop=false;
	}//setStopFalse
	
	public void setStopTrue() {
		this.stop=true;
	}//setStopTrue
	
	public void setClock(int clock) {
		this.clock=clock;
	}//setClock

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
			threadMonitorIteration=null;
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
					while(!stop) {
						Date currentDate = new Date();
				        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				        String currentTime=sdf.format(currentDate);
						List<Status>status=searching.getTweetFromListHashtag(listKey, 100);
				        List<Status>tmp=new LinkedList<Status>();
				        for(Status s:status) {
				        	if(!statusSaved.contains(s)) {
				        		statusSaved.add(s);
				        		tmp.add(s);
				        	}
				        }
				        if(status.size()!=0) {
				        	if(tmp.size()!=0) {
						        for(Status s:tmp) {
									Object[]obj= {currentTime,s.getUser().getScreenName(), s.getText().toString(), 
											(s.getPlace()==null)?"":s.getPlace().getFullName(), (s.getGeoLocation()==null)?"":s.getGeoLocation().getLatitude(),
											(s.getGeoLocation()==null)?"":s.getGeoLocation().getLongitude() };
								//	panelTable.dtm.addRow(obj);
						        	threadPanel.update(obj, panelTable);
								}
				        	}
				        	//Se non c'è alcun tweet evita di aprire il file
					        if(tmp.size()!=0) {
						        saving.saveListOnCSV(tmp, pathFile);
						        saving.saveListOnTXT(tmp, "it.unical.dimes.gridlab.tesi.a2019.Twitter.source\\Staus.txt");
						        if(checkIniziale) {
							        if(tmp!=null) {
										threadMonitorIteration=new MonitorIteration(new Iteration((LinkedList<Status>)tmp,5));
										threadMonitorIteration.start();
										threadMonitorIteration.check();
							        }
								}else
									checkIniziale=true;
					        }
					        Thread.sleep(clock);
					    }else{
					    	JOptionPane.showMessageDialog(null, "La ricerca non ha prodotto alcun risultato","Nessuno Stato", JOptionPane.INFORMATION_MESSAGE);
					    	stop=true;
			
					    }
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Dati Errati. Inserire nuovamente","Errore", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}			
			}
			avvia.setText(""); //Da settare ad "AVVIA" con vecchia grafica
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
					while(!stop) {
						Date currentDate = new Date();
				        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				        String currentTime=sdf.format(currentDate);
				        List<Status>status=searching.getTweetFromListHashtag(listKey, 100, x, y, km, data.getText());
				        List<Status>tmp=new LinkedList<Status>();
				        for(Status s:status) {
				        	if(!statusSaved.contains(s)) {
				        		statusSaved.add(s);
				        		tmp.add(s);
				        	}
				        }
				        if(status.size()!=0) {
				        	if(tmp.size()!=0) {
						        for(Status s:tmp) {
									Object[]obj= {currentTime,s.getUser().getName(), s.getText().toString(), 
											(s.getPlace()==null)?"":s.getPlace().getFullName(), (s.getGeoLocation()==null)?"":s.getGeoLocation().getLatitude(),
											(s.getGeoLocation()==null)?"":s.getGeoLocation().getLongitude() };
								//	panelTable.dtm.addRow(obj);
									threadPanel.update(obj, panelTable);
								}
					        }
					        if(tmp.size()!=0) {
						        saving.saveListOnCSV(tmp, pathFile);
						        saving.saveListOnTXT(tmp, "it.unical.dimes.gridlab.tesi.a2019.Twitter.source\\Staus.txt");
					        }
					        Thread.sleep(clock);
					        
					    }else{
					    	JOptionPane.showMessageDialog(null, "La ricerca non ha prodotto alcun risultato","Nessuno Stato", JOptionPane.INFORMATION_MESSAGE);
					    	stop=true;
					    }
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Dati Errati. Inserire nuovamente","Errore", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}	
			}
			avvia.setText(""); //Da settare ad "AVVIA" con vecchia grafica
			avvia.setEnabled(true);
		}
	}//run

}//ViewController
