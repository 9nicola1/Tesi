package it.unical.dimes.gridlab.tesi.a2019.Twitter.classifier;
import java.io.IOException;
import java.util.*;

import it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher.DrawGraph;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher.PanelAlert;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.saving.Saving;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import twitter4j.GeoLocation;
import twitter4j.Place;
import twitter4j.Status;
public class Classifier {
	private LinkedList<Status>alertList;
	private LinkedList<Status>notAlertList;
	private PanelAlert panelAlert;
	private PanelAlert panelNoAlert;
	private DrawGraph drawGraph;
	private Saving saving=new Saving();
	
	public void setPanel(PanelAlert panelAlert, PanelAlert panelNoAlert) {
		this.panelAlert=panelAlert;
		this.panelNoAlert=panelNoAlert;
	}//setPanek
	
	public void setDrawGraph(DrawGraph drawGraph) {
		this.drawGraph=drawGraph;
	}//setDrawGraph
	
	public Classifier() {
		alertList=new LinkedList<Status>();
		notAlertList=new LinkedList<Status>();
	}//Constructor
	
	
	/**
	 * Questo metodo classifica uno stato in base 
	 * all'algoritmo Naive-Bayes
	 * @param tmp
	 * @throws BiffException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public void classifierStatus(List<Status> tmp) throws RowsExceededException, WriteException, BiffException {
		alertList=new LinkedList<Status>();
		notAlertList=new LinkedList<Status>();
		for(Status status:tmp) {
			int character=status.getText().length();
			String pLength="";
			if(character>=1 && character<=80)
				pLength="Da1a80";
			else if(character>=81 && character<=160)
					pLength="Da81a160";
			else if(character>=161 && character<=240)
					pLength="Da161a240";
			GeoLocation geoLocation=status.getGeoLocation();
			String pGeoLocation=(geoLocation==null)?"GeoNo":"GeoSi";;
			Place place=status.getPlace();
			String pPlace=(place==null)?"PostoNo":"PostoSi";
			String tweet=status.getText();
			String pTweet=(UtilityClassifier.containKeyAfterHashtag(tweet)==true)?"ChiaveSi":"ChiaveNo";
			String pKeys=(UtilityClassifier.containKeyWords(tweet)==true)?"ParolaChiaveSi":"ParolaChiaveNo";
			double pAlert=UtilityClassifier.getProbabilityAlert(pLength, pGeoLocation, pPlace, pTweet, pKeys);	
//			System.out.println("PROB "+pAlert);
			Object[]obj= {status.getUser().getScreenName(), status.getCreatedAt().toString(), status.getText(),
					(status.getPlace()==null)?"":status.getPlace().getFullName(), (status.getGeoLocation()==null)?"":status.getGeoLocation().getLatitude(),
					(status.getGeoLocation()==null)?"":status.getGeoLocation().getLongitude() };			
			if((pAlert*100)>=50) {
			//	UtilityClassifier.writeOnFile(status, "C:\\Users\\Nicola\\Desktop\\tweetAlert.xls");
				this.alertList.add(status);
				panelAlert.dtm.addRow(obj);
			}
			else {
			//	UtilityClassifier.writeOnFile(status,"C:\\Users\\Nicola\\Desktop\\tweetNoAlert.xls");
				this.notAlertList.add(status);
				panelNoAlert.dtm.addRow(obj);
			}	
		}
		try {
			saving.saveListOnCSV(alertList, "C:\\Users\\Nicola\\Desktop\\tweetAlert.xls");
			saving.saveListOnCSV(notAlertList, "C:\\Users\\Nicola\\Desktop\\tweetNoAlert.xls");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.drawGraph.addTweetIteration(tmp.size(), alertList.size(), notAlertList.size());
		this.drawGraph.repaint();
	}//classifierStatus
	
	public static void main(String[]args) {
		String s="earthquake alle test";
		String r=(UtilityClassifier.containKeyAfterHashtag(s)==true)?"ChiaveSi":"ChiaveNo";
		double pAlert=UtilityClassifier.getProbabilityAlert("Da1a80", "GeoSi", "PostoSi",
				(UtilityClassifier.containKeyAfterHashtag(s)==true)?"ChiaveSi":"ChiaveNo",(UtilityClassifier.containKeyWords(s)==true)?"ParolaChiaveSi":"ParolaChiaveNo");	
		System.out.println("PROB "+pAlert);
//		System.out.println(r);
	}
	
}//Classifier
