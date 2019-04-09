package Twitter.test;

import java.util.LinkedList;
import java.util.List;

import twitter4j.HashtagEntity;
import twitter4j.Location;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * 
 * @author Nicola
 *
 */
public class Test {
	/**
	 * 
	 * @param args
	 * @throws TwitterException
	 */
	public static void main(String[]args) throws TwitterException {
		String APIKey="";						//Application Settings/Consumer Key (API key)
		String APISecret="";			  //Application Settings/Consumer Secret (API Secret)
		String AccessToken="";								 //Your Access Token/Access Token
		String AccessTokenSecret="";				  //Your Access Token/Access Token Secret
		ContainerAbstract container=new Container(APIKey, APISecret, AccessToken, AccessTokenSecret);
		List<String>keyWords=new LinkedList<String>();
		keyWords.add("Earthquake");
		keyWords.add("Help");
		keyWords.add("Allert");
		List<Status>listStatus, listKeyStatus;
		List<HashtagEntity>listHashtag;
		List<Location>listLocation;
		try {
			listStatus=container.getStatus();
			listKeyStatus=container.getKeyStatus(keyWords);
			listHashtag=container.getHashtag();
			listLocation=container.getLocation(keyWords);
		}catch(TwitterException tE) {
			System.out.println("TwitterException!, please check methods");
			tE.printStackTrace();
		}
	}//main
	
}//Test
