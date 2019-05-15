package it.unical.dimes.gridlab.tesi.a2019.Twitter.classifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.MediaEntity;
import twitter4j.Place;
import twitter4j.Status;

public class AccessDataSet {
	
	public enum DataSet {
		TRUE, FALSE, TRUEFALSE;
		private String fileDataSet;
		static {
			TRUE.fileDataSet = "it.unical.dimes.gridlab.tesi.a2019.Twitter.classifier\\TrueDataSet.txt";
			FALSE.fileDataSet = "it.unical.dimes.gridlab.tesi.a2019.Twitter.classifier\\FalseDataSet.txt";
			TRUEFALSE.fileDataSet = "it.unical.dimes.gridlab.tesi.a2019.Twitter.classifier\\TrueFalseDataSet.txt";
		}

		public String getFile() {
			return fileDataSet;
		}
	};
	
	/**
	 * Aggiunge lo stato al dataset specificato dal classificatore in modo d poter aumentare
	 * la precisione del Machine Learning
	 * @param status
	 * @param dataSet
	 * @return
	 * @throws WriteException
	 * @throws BiffException
	 */
	public boolean addStatusToDataSet(Status status, DataSet dataSet) throws WriteException, BiffException {
		try{
			File file=new File(dataSet.getFile());
			Workbook workbook;
			WritableWorkbook wworkbook;
			try {
				 workbook=Workbook.getWorkbook(file);
				 wworkbook= Workbook.createWorkbook(file,workbook);
			}catch(FileNotFoundException e) {
				wworkbook= Workbook.createWorkbook(file);
			}
			WritableSheet wsheet;
			if(wworkbook.getNumberOfSheets()==0)
				wsheet = wworkbook.createSheet("Dati Twitter",0);
			else
				wsheet = wworkbook.getSheet("Dati Twitter");
			int tmp=wsheet.getRows();
			int riga=0;
			if(tmp!=0)riga=tmp+1;
			Label label=null;
			String author=status.getUser().getScreenName();
			String orario=status.getCreatedAt().toString();
			String tweet=UtilityClassifier.filterToStatus(status.getText());
			String rt=""+status.getRetweetCount();
			HashtagEntity[]hashtag=status.getHashtagEntities();
			MediaEntity[] media = status.getMediaEntities(); 
			Place place=status.getPlace();
			GeoLocation geoLocation=status.getGeoLocation();
			int colonna=0;
			//Aggiunta Author
			label = new Label(colonna++, riga, author);
			wsheet.addCell(label);
			//Aggiunta data
			label = new Label(colonna++, riga, orario);
			wsheet.addCell(label);
			//Aggiunta stato
			label = new Label(colonna++, riga, tweet);
			wsheet.addCell(label);
			//Aggiunta numero RT
			label = new Label(colonna++, riga, rt);
			wsheet.addCell(label);
			//Aggiunta Hashtag
			if(hashtag.length!=0) {
				StringBuilder tag=new StringBuilder();
				for(int i=0; i<hashtag.length; i++) {	
					tag.append(hashtag[i].getText());
					tag.append('\n');
				}
				label = new Label(colonna++, riga, tag.toString());
				wsheet.addCell(label);
			}
			else
				colonna++;
			//Aggiunta Media
			if(media.length!=0) {
				StringBuilder medias=new StringBuilder();
				for(int i=0; i<media.length; i++) {	
					medias.append(media[i].getText());
					medias.append('\n');
				}
				label = new Label(colonna++, riga, medias.toString());
				wsheet.addCell(label);
			}
			else
				colonna++;
			//Aggiunta Place
			if(place!=null ) {
				label = new Label(colonna++, riga, place.getFullName());
				wsheet.addCell(label);
			}
			else
				colonna++;
			//Aggiunta GeoLocation
			if(geoLocation!=null) {
				label = new Label(colonna++, riga, ""+(double) geoLocation.getLatitude());
				wsheet.addCell(label);
				label = new Label(colonna++, riga, ""+(double)geoLocation.getLongitude());
				wsheet.addCell(label);
			}
			else
				colonna=colonna+2;
			riga++;
			wworkbook.write();
	        wworkbook.close();
	        return true;
		}
		catch(IOException e) {
			return false;
		}
	}//addStatusToDataSet
	
	public ModelTweet[]getDataSetFrom(DataSet dataSet){
		ModelTweet[]models=new ModelTweet[1];
		return models;
		//TODO
	}//getDataSetFrom

}
