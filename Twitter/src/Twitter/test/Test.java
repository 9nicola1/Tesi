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
		String APIKey="hn3UBOFrP4WBLFVjqsCcYB3Dt";										//Application Settings/Consumer Key (API key)
		String APISecret="ovCke2aWfdW6TvhZ0dggL5mO320mzMlLZwDgcvNVUqlUsZbp9J";	  //Application Settings/Consumer Secret (API Secret)
		String AccessToken="1113747010694340608-csRTyytaX9TqM6zJLN3yPl5O0V2Yos";					 //Your Access Token/Access Token
		String AccessTokenSecret="QB57vcKjJLXQbqQXKQ4I5n6wfvVFuUBkS3MfhOC81sjzA";			  //Your Access Token/Access Token Secret
		ContainerAbstract container=new Container(APIKey, APISecret, AccessToken, AccessTokenSecret);
		List<String>keyWords=new LinkedList<String>();
		keyWords.add("Conversation");
		//keyWords.add("Help");
		//keyWords.add("Allert");
		List<Status>listStatus=null, listKeyStatus=null;
		List<HashtagEntity>listHashtag=null;
		List<Location>listLocation=null;
		try {
			listStatus=container.getStatus();
			listKeyStatus=container.getKeyStatus(keyWords);
		//	listHashtag=container.getHashtag();
		//	listLocation=container.getLocation(keyWords);
		}catch(TwitterException tE) {
			System.out.println("TwitterException!, please check methods");
			tE.printStackTrace();
		}
		for(Status s:listKeyStatus)
			System.out.println(s.getText());
	}//main
	
}//Test
