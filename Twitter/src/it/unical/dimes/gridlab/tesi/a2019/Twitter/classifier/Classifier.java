package it.unical.dimes.gridlab.tesi.a2019.Twitter.classifier;
import java.util.*;

import it.unical.dimes.gridlab.tesi.a2019.Twitter.classifier.AccessDataSet.DataSet;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher.PanelAlert;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import twitter4j.Status;
public class Classifier extends Thread{

	
	private static final int MAXTRUE=100;
	private static final int MINTRUE=70;
	private static final int MAXTRUEFALSE=69;
	private static final int MINTRUEFALSE=31;
	private static final int MAXFALSE=30;
	private static final int MINFALSE=0;
	
	/**
	 * Richiamato dal viewController per ogni iterazione
	 * Scandisce la lista degli stati confrontandoli con i dataset
	 * @param status
	 * @return Restituisce la lista dei soli stati che sono simili al dataset positivo
	 */
	public void algorithm(LinkedList<Status>status, PanelAlert panel) {
		ModelTweet[]positive=AccessDataSet.getDataSetFrom(DataSet.TRUE);
		ModelTweet[]negative=AccessDataSet.getDataSetFrom(DataSet.FALSE);
		LinkedList<ModelTweet>listModel=new LinkedList<ModelTweet>();
		for(Status s:status) {
			//***************************
			//1. Creazione del ModelTweet 
			//***************************
			ModelTweet model=UtilityClassifier.createModelTweet(s);
			
			//***********************
			//2. Filtraggio del tweet
			//***********************
			String tweet=UtilityClassifier.filterToStatus(s.getText());
			model.setTweet(tweet);
			
			//***********************************************************
			//3. Assegnazione della probabilità classificata per lo stato
			//***********************************************************
			int prob=UtilityClassifier.getProbability(model, positive, negative);
					
			//***************************************************
			//4. Inserimento nel DataSet in base alla probabilità 
			//***************************************************
			try {
				if(prob<=MAXTRUE && prob>=MINTRUE) {
					AccessDataSet.addStatusToDataSet(model, DataSet.TRUE);
					listModel.add(model);
				}
				else if(prob<=MAXTRUEFALSE && prob>=MINTRUEFALSE) {
					AccessDataSet.addStatusToDataSet(model, DataSet.TRUEFALSE);
				}
				else if(prob<=MAXFALSE && prob>=MINFALSE) {
					AccessDataSet.addStatusToDataSet(model, DataSet.FALSE);
				}
			}catch(WriteException| BiffException e) {
				e.printStackTrace();
			}
		}
		
		//***********************************************
		//5. Aggiornamento tabella con gli stati positivi
		//***********************************************
		for(ModelTweet m:listModel) {
			//"AUTORE","DATA POST","STATO","LUOGO", "LATITUDINE", "LONGITUDINE"
			Object[]obj= {m.getAuthor(),m.getDate(), m.getTweet(), 
					(m.getPlace()=="")?"":m.getPlace(), (m.getLatitude()=="")?"":m.getLatitude(),
					(m.getLongitude()=="")?"":m.getLongitude() };
			panel.dtm.addRow(obj);
		}
		
		//*******************************************************************************
		//6. Inserire sulla piattaformaWeb la lista dei tweet classificati come "positivi
		//*******************************************************************************
		
		//TODO
		
	}//algorithm
	
	@Override
	public void run() {
		//NOTHING TO DO
	}//run
	

}//Classifier
