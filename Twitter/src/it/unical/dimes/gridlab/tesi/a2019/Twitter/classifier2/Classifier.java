package it.unical.dimes.gridlab.tesi.a2019.Twitter.classifier2;
import java.util.*;

import twitter4j.GeoLocation;
import twitter4j.Place;
import twitter4j.Status;
public class Classifier {
	private LinkedList<Status>alertList;
	private LinkedList<Status>notAlertList;
	
	public Classifier() {
		alertList=new LinkedList<Status>();
		notAlertList=new LinkedList<Status>();
	}
	
	
	/**
	 * Questo metodo classifica uno stato in base 
	 * all'algoritmo Naive-Bayes
	 * @param list
	 */
	public void classifierStatus(LinkedList<Status> list) {
		for(Status status:list) {
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
			String pTweet=(UtilityClassifier.getKeyAfterHashtag(tweet)==true)?"ChiaveSi":"ChiaveNo";
			String pKeys=(UtilityClassifier.getKeyWords(tweet)==true)?"ParolaChiaveSi":"ParolaChiaveNo";
			int pAlert=UtilityClassifier.getProbabilityAlert(pLength, pGeoLocation, pPlace, pTweet, pKeys);
			if(pAlert>=50) {
				UtilityClassifier.writeOnFile(status, "it.unical.dimes.gridlab.tesi.a2019.Twitter.classifier\\AlertStatus.txt");
				this.alertList.add(status);
				//Aggiungere su Panel
			}
			else {
				UtilityClassifier.writeOnFile(status,"it.unical.dimes.gridlab.tesi.a2019.Twitter.classifier\\NoAlertStatus.txt");
				this.notAlertList.add(status);
				//Aggiungere su Panel
			}		
		}
		
	}//classifierStatus
}
