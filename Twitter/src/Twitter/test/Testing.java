package Twitter.test;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Testing {

	public static void main(String[]args) throws TwitterException {
		ConfigurationBuilder cB=new ConfigurationBuilder();
		cB.setDebugEnabled(true)
		.setOAuthConsumerKey("hn3UBOFrP4WBLFVjqsCcYB3Dt") 			//Application Settings/Consumer Key (API key)
		.setOAuthConsumerSecret("ovCke2aWfdW6TvhZ0dggL5mO320mzMlLZwDgcvNVUqlUsZbp9J")//Application Settings/Consumer Secret (API Secret)
		.setOAuth2AccessToken("1113747010694340608-csRTyytaX9TqM6zJLN3yPl5O0V2Yos") 			     	 //Your Access Token/Access Token
		.setOAuthAccessTokenSecret("QB57vcKjJLXQbqQXKQ4I5n6wfvVFuUBkS3MfhOC81sjzA");//Your Access Token/Access Token Secret
		TwitterFactory tF=new TwitterFactory(cB.build());;
		Twitter twitter =tF.getInstance();
		List<Status> status=twitter.getHomeTimeline();
		for(Status s:status)
			System.out.println(s.getText());
	}
}
