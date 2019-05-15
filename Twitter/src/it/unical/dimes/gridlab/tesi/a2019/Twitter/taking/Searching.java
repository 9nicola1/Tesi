package it.unical.dimes.gridlab.tesi.a2019.Twitter.taking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;

public class Searching extends SearchingAbstract{

	public Searching(String APIKey, String APISecret, String AccessToken, String AccessTokenSecret) {
		super(APIKey, APISecret, AccessToken, AccessTokenSecret);
	}//Constructor

	@Override
	public List<Status> getTweetFromHashtagAndLocation(String hashtag, int count, double latitude, double longitude, int km, String date) throws TwitterException {
		Query query=new Query(hashtag);
		query.count(count);
		query.setGeoCode(new GeoLocation(latitude,longitude), km, Query.MILES);
		query.setSince(date);
		LinkedList<Status>list=new LinkedList<Status>();
		try {
			QueryResult result=twitter.search(query);
			for(Status s:result.getTweets())
				if(s.getLang().equals("en") || s.getLang().equals("it"))
					list.add(s);	
		}catch(TwitterException e) {
			System.out.println("Errore  in getTweetFromHashtagAndLocation "+hashtag);
			e.printStackTrace();
		}
		return list;
	}//getTweetFromHashtag

	@Override
	public List<Status> getKeyStatus(String keyWord) throws TwitterException {
		List<Status> status=twitter.getHomeTimeline();
		List<Status> statusRet=new ArrayList<Status>();
		for(Status s:status) {
			String ss=s.getText();
			if(ss.contains(keyWord)) 
				statusRet.add(s);	
		}
		return statusRet;
	}//getKeyStatus

	@Override
	public List<Status> getTweetFromHashtag(String hashtag, int count)throws TwitterException {
		Query query=new Query(hashtag);
		query.count(count);
		LinkedList<Status>list=new LinkedList<Status>();
		try {
			QueryResult result=twitter.search(query);
			for(Status s:result.getTweets())
				if(s.getLang().equals("en")|| s.getLang().equals("it"))
					list.add(s);	
		}catch(TwitterException e) {
			System.out.println("Errore  in getTweetFromHashtag "+hashtag);
			e.printStackTrace();
		}
		return list;
	}//getTweetFromHashtag

	@Override
	public List<Status> getTweetFromListHashtag(List<String> hashtag, int count, double latitude, double longitude, int km, String date) throws TwitterException {
		LinkedList<Status>list=new LinkedList<Status>();
		for(String s:hashtag) {
			Query query=new Query(s);
			query.count(count);
			query.setGeoCode(new GeoLocation(latitude,longitude), km, Query.MILES);
			query.setSince(date);
			try {
				QueryResult result=twitter.search(query);
				for(Status status:result.getTweets())
					if(status.getLang().equals("en")|| status.getLang().equals("it"))
						list.add(status);
			}catch(TwitterException e) {
				System.out.println("Errore  in getTweetFromListHashtag "+hashtag);
				e.printStackTrace();
			}
		}
		return list;
	}//getTweetFromListHashtag
	
	public List<Status> getTweetFromListHashtag(List<String> hashtag, int count) throws TwitterException {
		LinkedList<Status>list=new LinkedList<Status>();
		for(String s:hashtag) {
			Query query=new Query(s);
			query.count(count);
			try {
				QueryResult result=twitter.search(query);
				for(Status status:result.getTweets())
					if(status.getLang().equals("en")|| status.getLang().equals("it"))
						list.add(status);
			}catch(TwitterException e) {
				System.out.println("Errore  in getTweetFromListHashtag "+hashtag);
				e.printStackTrace();
			}
		}
		return list;
	}//getTweetFromListHashtag

}//Searching
