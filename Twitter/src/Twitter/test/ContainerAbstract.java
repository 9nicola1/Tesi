package Twitter.test;

import java.util.LinkedList;
import java.util.List;

import twitter4j.HashtagEntity;
import twitter4j.Location;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * 
 * @author Nicola
 *
 */
public abstract class ContainerAbstract {
	protected ConfigurationBuilder cB; 
	protected TwitterFactory tF;
	protected Twitter twitter;
	
	public ContainerAbstract(String APIKey, String APISecret, String AccessToken, String AccessTokenSecret) {
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
	 * @return Ritorna una lista contenente gli stati sulla HomePage di Twitter
	 * @throws TwitterException
	 */
	protected abstract List<Status> getStatus()throws TwitterException;
	
	/**
	 * 
	 * @return Ritorna una lista contenente gli stati sulla HomePage di Twitter
	 * @throws TwitterException
	 */
	protected abstract List<Status> getTweetFromHashtag(LinkedList<String>hashtag, int count, long sinceId, int limit)throws TwitterException;
	
	/**
	 * 
	 * @param hashtag
	 * @param count
	 * @param latitude
	 * @param longitude
	 * @param km
	 * @param date -->Fornire una data del tipo yyyy-mm-dd
	 * @return Ritorna una lista contenente tutti gli stati ricercati alla localizzazione fornita con un area di km forniti
	 * @throws TwitterException
	 */
	protected abstract List<Status> getTweetFromOneHashtag(String hashtag, int count, double latitude, double longitude, int km, String date)throws TwitterException;
	
	/**
	 * 
	 * @param keyWords
	 * @return Ritorna una lista con tutti gli stati che contengono le parole chiavi
	 * @throws TwitterException
	 */
	protected abstract List<Status> getKeyStatus(List<String>keyWords)throws TwitterException;
	
	/**
	 * 
	 * @param keyWords
	 * @return Ritorna una lista con tutti le Location che contengono le parole chiavi
	 * @throws TwitterException
	 */
	protected abstract List<Location> getLocation(List<String>keyWords)throws TwitterException;

}//AbstractContainer
