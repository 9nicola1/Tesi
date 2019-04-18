package it.unical.dimes.gridlab.tesi.a2019.Twitter.test;

import java.util.LinkedList;
import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.Location;
import twitter4j.Place;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.URLEntity;

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
		keyWords.add("Help");
		keyWords.add("Allert");
		AccessDB accessDB=new AccessDB();
		LinkedList<String>hashtag=new LinkedList<String>();
		hashtag.add("#10aprile");
		LinkedList<String>location=new LinkedList<String>();
		location.add("NewYork");
		location.add("Roma");
		//hashtag.add("Help");
		//hashtag.add("Allert");
		List<Status>listStatus=null, listKeyStatus=null,listHashtag=null, listOneHashtag=null, listFromLocation=null;
		List<Location>listLocation=null;
		try {
		//	listStatus=container.getStatus();
			listOneHashtag=container.getTweetFromOneHashtag("#calabria", 100, 39.3099931, 16.2501929, 20, "2019-04-11");
		//	listKeyStatus=container.getKeyStatus(keyWords);
		//	listHashtag=container.getTweetFromHashtag(hashtag, 10, 0, 10);
		//	listFromLocation=container.getTweetsFromLocation(location, 100, "2019-04-10");
		}catch(TwitterException tE) {
			System.out.println("TwitterException!, please check methods");
			tE.printStackTrace();
		}
	//	for(Status s:listFromLocation) {
	//		System.out.println(s.getText());
	//	}
		for(Status s:listOneHashtag) {
			Place x=s.getPlace();	//Prende la posizione del tweet, se non c'è return null
			if(x!=null)
				System.out.println(s);
	//		System.out.println("Città: "+x.getName()+ "\tPaese: "+x.getCountry());
	//		System.out.println("User: "+s.getUser().getName()+'\n'+s.getText());
	//		System.out.println();
		}
		/*for(Status s:listStatus)
			System.out.println(s.getText());
		accessDB.insertListStatus(listStatus);
		for(Status s:listKeyStatus)
			System.out.println(s.getText());
		accessDB.insertListKeyStatus(listKeyStatus);*/
		//for(Status s:listHashtag)
			//System.out.println(s.getText());
	}//main
	
}//Test
