package it.unical.dimes.gridlab.tesi.a2019.Twitter.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.Location;
import twitter4j.Place;
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
		//TODO
		return null;
	}//getHashtag
	
	

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
	protected List<Status> getTweetsFromLocation(List<String> city,  int count, String date) throws TwitterException {
		LinkedList<Status>list=new LinkedList<Status>();
		for(String s:city) {
			Query query=new Query(s);
			query.count(count);
			query.setSince(date);
			try {
				QueryResult result=twitter.search(query);
				for(Status status:result.getTweets()) {
					System.out.println(status);
					Place place=status.getPlace();
					System.out.println("############");
					if(place!=null)System.out.println(place.getName());
					if((place!=null && !place.getName().equals(' ')) && place.getName().equals(s)) {
						System.out.println("Luogo cercato: "+place.getName()+"\t Citta': "+s);
						list.add(status);
					}
				}
			}catch(TwitterException tE) {
				System.out.println("Errore  in getLocation: "+s);
				tE.printStackTrace();
			}
		}
		return list;
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
