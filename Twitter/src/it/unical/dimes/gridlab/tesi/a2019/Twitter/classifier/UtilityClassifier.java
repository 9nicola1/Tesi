package it.unical.dimes.gridlab.tesi.a2019.Twitter.classifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.MediaEntity;
import twitter4j.Place;
import twitter4j.Status;

public class UtilityClassifier {
	private static final double PROB_DA1A80_SI=0.52173913;
	private static final double PROB_DA81A160_SI=0.391304348;
	private static final double PROB_DA161A240_SI=0.086956522;
	private static final double PROB_DA1A80_NO=0.125;
	private static final double PROB_DA81A160_NO=0.0375;
	private static final double PROB_DA161A240_NO=0.5;
	private static final double PROB_GEOSI_SI=0.0695652174;
	private static final double PROB_GEONO_SI=0.304347826;
	private static final double PROB_GEOSI_NO=0.3125;
	private static final double PROB_GEONO_NO=0.6875;
	private static final double PROB_POSTOSI_SI=0.608695652;
	private static final double PROB_POSTONO_SI=0.391304348;
	private static final double PROB_POSTOSI_NO=0.375;
	private static final double PROB_POSTONO_NO=0.625;
	private static final double PROB_CHIAVESI_SI=0.565217391;
	private static final double PROB_CHIAVENO_SI=0.434782609;
	private static final double PROB_CHIAVESI_NO=0.04375;
	private static final double PROB_CHIAVENO_NO=0.05625;
	private static final double PROB_PAROLACHIAVESI_SI=0.652173913;
	private static final double PROB_PAROLACHIAVENO_SI=0.347826087;
	private static final double PROB_PAROLACHIAVESI_NO=0.0625;
	private static final double PROB_PAROLACHIAVENO_NO=0.9375;
	private static final String[]keyWords= {"magnitudo","magnitude","trema","scossa","quake","shake","epicentro","epicenter","richter", "km", "miles"};	
	
	/**
	 * Controlla se subito dopo una parola chiave sono presenti
	 * i termini "in" oppure "a".
	 * @param tweet
	 * @return
	 */
	public static boolean containKeyAfterHashtag(String tweet) {
		tweet.toLowerCase();
		StringTokenizer st=new StringTokenizer(tweet," ");
		while(st.hasMoreTokens()) {
			String key=st.nextToken();
			if(key.equalsIgnoreCase("earthquake")||key.equalsIgnoreCase("terremoto")||key.equalsIgnoreCase("#earthquake")||key.equalsIgnoreCase("#terremoto")) {
				if(st.hasMoreTokens()) {
					String tmp=st.nextToken();
					if(tmp.equalsIgnoreCase("in") ||tmp.equalsIgnoreCase("a")|| tmp.equalsIgnoreCase("at") ||tmp.equalsIgnoreCase("alle"))
						return true;
				}
			}			
		}
		return false;
	}//containKeyAfterHashtag

	/**
	 * Controlla se il tweet contiene parole chiavi con riferimenti
	 * a terremoti.
	 * @param tweet
	 * @return True se ne contiene almeno 1, false altrimenti.
	 */
	public static boolean containKeyWords(String tweet) {
		tweet.toLowerCase();
		for(int i=0; i<keyWords.length; i++) 
			if(tweet.contains(keyWords[i]))return true;
		return false;
	}//containKeyWords

	
	/**
	 * Questo metodo applica esattamente l'algoritmo Naive-Bayes per prendere 
	 * una decisione sulla classficazione
	 * @param pLength
	 * @param pGeoLocation
	 * @param pPlace
	 * @param pTweet
	 * @param pKeys
	 * @return
	 */
	public static double getProbabilityAlert(String pLength, String pGeoLocation, String pPlace, String pTweet, String pKeys) {
		double pLSi, pLNo;
		if(pLength.equals("Da1a80")) {
			pLSi=PROB_DA1A80_SI;
			pLNo=PROB_DA1A80_NO;
		}else if(pLength.equals("Da81a160")) {
			pLSi=PROB_DA81A160_SI;
			pLNo=PROB_DA81A160_NO;
		}else {
			pLSi=PROB_DA161A240_SI;
			pLNo=PROB_DA161A240_NO;
		}
		
		double pGSi=pGeoLocation.equals("GeoSi")?PROB_GEOSI_SI:PROB_GEONO_SI;
		double pGNo=pGeoLocation.equals("GeoSi")?PROB_GEOSI_NO:PROB_GEONO_NO;
		
		double pPSi=pPlace.equals("PostoSi")?PROB_POSTOSI_SI:PROB_POSTONO_SI;
		double pPNo=pPlace.equals("PostoSi")?PROB_POSTOSI_NO:PROB_POSTONO_NO;
		
		double pCSi=pTweet.equals("ChiaveSi")?PROB_CHIAVESI_SI:PROB_CHIAVENO_SI;
		double pCNo=pTweet.equals("ChiaveSi")?PROB_CHIAVESI_NO:PROB_CHIAVENO_NO;
		
		double pWSi=pKeys.equals("ParolaChiaveSi")?PROB_PAROLACHIAVESI_SI:PROB_PAROLACHIAVENO_SI;
		double pWNo=pKeys.equals("ParolaChiaveSi")?PROB_PAROLACHIAVESI_NO:PROB_PAROLACHIAVENO_NO;
		
		double probSi=pLSi*pGSi*pPSi*pCSi*pWSi;
		double probNo=pLNo*pGNo*pPNo*pCNo*pWNo;
		
		double normSi=((probSi)/(probSi+probNo));
//		System.out.println("PROB SI "+probSi);
//		System.out.println("PROB No "+probNo);
//		System.out.println("Norm "+normSi);
		return normSi;
	}//getProbabilityAlert

	public static void writeOnFile(Status status, String path) throws RowsExceededException, WriteException, BiffException {
		try{
			File file=new File(path);
			Workbook workbook;
			WritableWorkbook wworkbook;
			try {
				 workbook=Workbook.getWorkbook(file);
				 wworkbook= Workbook.createWorkbook(file,workbook);
			}catch(FileNotFoundException |BiffException e) {
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
			String tweet=status.getText();
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
		}
		catch(IOException e) {
				e.printStackTrace();
		}		
	}//writeOnFile

}//UtilityClassifier
