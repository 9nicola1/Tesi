package Twitter.test;

import java.util.List;

import twitter4j.HashtagEntity;
import twitter4j.Location;
import twitter4j.Status;
import twitter4j.TwitterException;

public class Container extends ContainerAbstract{

	public Container(String APIKey, String APISecret, String AccessToken, String AccessTokenSecret) {
		super(APIKey, APISecret, AccessToken, AccessTokenSecret);
	}//Constructor

	@Override
	protected List<Status> getStatus() throws TwitterException {
		List<Status> status=twitter.getHomeTimeline();
		return status;
	}//getStatus

	@Override
	protected List<HashtagEntity> getHashtag() throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}//getHashtag

	@Override
	protected List<Status> getKeyStatus(List<String> keyWords) throws TwitterException {
		List<Status> status=twitter.getHomeTimeline();
		for(Status s:status) {
			String ss=s.getText();
			boolean contain=false;
			for(String tmp:keyWords) 
				if(ss.contains(tmp)) 
					contain=true;
			if(!contain)status.remove(s);
		}
		//@TEST
		//for(Status s:status)
		//	System.out.println(s.getUser().getName()+"    "+s.getText());
		//@ENDTEST
		return status;
	}//getKeyStatus

	@Override
	protected List<Location> getLocation(List<String> keyWords) throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}//getLocation

}//Container
