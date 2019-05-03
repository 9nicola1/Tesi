package it.unical.dimes.gridlab.tesi.a2019.Twitter.taking;


import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public abstract class SearchingAbstract {
	protected ConfigurationBuilder cB; 
	protected TwitterFactory tF;
	protected Twitter twitter;
	
	public SearchingAbstract(String APIKey, String APISecret, String AccessToken, String AccessTokenSecret) {
		cB=new ConfigurationBuilder();
		cB.setDebugEnabled(true)
			.setOAuthConsumerKey(APIKey) 			//Application Settings/Consumer Key (API key)
			.setOAuthConsumerSecret(APISecret)//Application Settings/Consumer Secret (API Secret)
			.setOAuthAccessToken(AccessToken) 			     	 //Your Access Token/Access Token
			.setOAuthAccessTokenSecret(AccessTokenSecret);//Your Access Token/Access Token Secret
		
		tF=new TwitterFactory(cB.build());
		twitter =tF.getInstance();
	}//Constructor
	
	/**
	 * 
	 * @param hashtag -->Fornire una Stringa con la parola preceduta dal carattere '#' 
	 * @param count -->Numero di tweet massimo da ricercare
	 * @param latitude -->Tipo "long" positivo se NORD dall'equatore, negativo se SUD dell'equatore
	 * @param longitude -->Tipo "long" positivo se EST dal meridiano di Greenwich, negativo se OVEST dal meridiano di Greenwich
	 * @param km -->Raggio d'azione entro cui effettuare la ricerca
	 * @param date -->Fornire una data del tipo yyyy-mm-dd
	 * @return Ritorna una lista contenente tutti gli stati ricercati alla localizzazione fornita con un area di km forniti
	 * @throws TwitterException
	 */
	public abstract List<Status> getTweetFromHashtagAndLocation(String hashtag, int count, double latitude, double longitude, int km, String date)throws TwitterException;
	
	/**
	 * 
	 * @param keyWord -->Stringa contenente una qualsiasi parola chiave ("terremoto", "earthquake", ...)
	 * @return Ritorna una lista con tutti gli stati che contengono le parole chiavi
	 * @throws TwitterException
	 */
	public abstract List<Status> getKeyStatus(String keyWord)throws TwitterException;
	
	/**
	 * 
	 * @return Ritorna una lista contenente gli stati sulla HomePage di Twitter
	 * @throws TwitterException
	 */
	public abstract List<Status> getTweetFromHashtag(String hashtag, int count)throws TwitterException;
	
	
	/**
	 * 
	 * @param hashtag
	 * @param count
	 * @return Ritorna una lista contenente gli stati sulla HomePage di Twitter usando le parole chiavi 
	 * @throws TwitterException
	 */
	public abstract List<Status> getTweetFromListHashtag(List<String>hashtag, int count, double latitude, double longitude, int km, String date)throws TwitterException;

	/**
	 * 
	 * @param hashtag
	 * @param count
	 * @return  Ritorna una lista contenente gli stati sulla HomePage di Twitter usando le parole chiavi 
	 * @throws TwitterException
	 */
	public abstract List<Status> getTweetFromListHashtag(List<String> hashtag, int count) throws TwitterException;
	

}//SearchingAbstract
