package Twitter.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.Location;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
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
	protected List<Status> getTweetFromHashtag(LinkedList<String>hashtag, int count, long sinceId, int limit) throws TwitterException {
		List<Status>list=new LinkedList<Status>();
		for(String tag:hashtag) {
			Query query=new Query(tag);
			query.setCount(count);
			getTweets(query);
			query=null;
			int rate=limit;
			do {
				rate--;
				Query querySince=new Query(tag);
				querySince.setCount(count);
				querySince.setSinceId(sinceId);
				LinkedList<Status>listTmp=getTweets(querySince);
				for(Status s:listTmp)
					list.add(s);
				querySince=null;
			}while(checkIfSinceTweetsAreAvaiable(tag, count, sinceId)|| rate>0);
		}
		return null;
	}//getHashtag
	
	/**
	 * 
	 * @param twitter
	 * @return
	 */
	private boolean checkIfSinceTweetsAreAvaiable(String hashtag, int count, long sinceId) {
		Query query = new Query(hashtag);
        query.setCount(count);
        query.setSinceId(sinceId);
        try {
            QueryResult result = twitter.search(query);
            if(result.getTweets()==null || result.getTweets().isEmpty()){
                query = null;
                return false;
            }
        } catch (TwitterException te) {
            System.out.println("Couldn't connect: " + te);
            System.exit(-1);
        }catch (Exception e) {
            System.out.println("Something went wrong: " + e);
            System.exit(-1);
        }
        return true;
	}//checkIfSinceTweetsAreAvaiable

	/**
	 * 
	 * @param query
	 * @param twitter
	 * @param string
	 */
	private LinkedList<Status> getTweets(Query query) {
		boolean getTweets=true;
        LinkedList<Status>listTweet=new LinkedList<Status>();
        while (getTweets){
            try {
                QueryResult result = twitter.search(query);
                if(result.getTweets()==null || result.getTweets().isEmpty()){
                    getTweets=false;
                }else{
                    for (Status status: result.getTweets()) {
                        listTweet.add(status); 
                        System.out.println(status.getText());
                    }
                }
            }catch (TwitterException te) {
                System.out.println("Couldn't connect: " + te);
                System.exit(-1);
            }catch (Exception e) {
                System.out.println("Something went wrong: " + e);
                System.exit(-1);
            }
        }		
        return listTweet;
    }//getTweets

	@Override
	protected List<Status> getKeyStatus(List<String> keyWords) throws TwitterException {
		List<Status> status=twitter.getHomeTimeline();
		List<Status> statusRet=new ArrayList<Status>();
		for(Status s:status) {
			String ss=s.getText();
			for(String tmp:keyWords) 
				if(ss.contains(tmp)) 
					statusRet.add(s);	
		}
		//@TEST
		//for(Status s:status)
		//	System.out.println(s.getUser().getName()+"    "+s.getText());
		//@ENDTEST
		return statusRet;
	}//getKeyStatus

	@Override
	protected List<Location> getLocation(List<String> keyWords) throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}//getLocation

	@Override
	protected List<Status> getTweetFromOneHashtag(String hashtag, int count, double latitude, double longitude, int km, String date)throws TwitterException {
		Query query=new Query(hashtag);
		query.count(count);
		query.setGeoCode(new GeoLocation(latitude,longitude), km, Query.MILES);
		query.setSince(date);
		LinkedList<Status>list=new LinkedList<Status>();
		try {
			QueryResult result=twitter.search(query);
			for(Status s:result.getTweets())
				list.add(s);	
		}catch(TwitterException e) {
			System.out.println("Errore  in getTweetFromOneHashtag "+hashtag);
			e.printStackTrace();
		}
		return list;
	}//getTweetFromOneHashtag
	
	
}//Container
