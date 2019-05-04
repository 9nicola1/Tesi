package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;

import it.unical.dimes.gridlab.tesi.a2019.Twitter.saving.*;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.taking.*;

import java.io.IOException;
import java.util.*;

import javax.swing.plaf.synth.SynthSeparatorUI;

import twitter4j.Status;
import twitter4j.TwitterException;

public class Launcher {
	private static SearchingAbstract searching=null;
	private static SavingAbstract saving=null;
	public static void main(String[]args) throws IOException {
		String APIKey="hn3UBOFrP4WBLFVjqsCcYB3Dt";										//Application Settings/Consumer Key (API key)
		String APISecret="ovCke2aWfdW6TvhZ0dggL5mO320mzMlLZwDgcvNVUqlUsZbp9J";	  //Application Settings/Consumer Secret (API Secret)
		String AccessToken="1113747010694340608-csRTyytaX9TqM6zJLN3yPl5O0V2Yos";					 //Your Access Token/Access Token
		String AccessTokenSecret="QB57vcKjJLXQbqQXKQ4I5n6wfvVFuUBkS3MfhOC81sjzA";			  //Your Access Token/Access Token Secret
		searching=new Searching(APIKey, APISecret, AccessToken, AccessTokenSecret);
		String keyWord="#earthquake";
		String nameFile="C:\\Users\\Nicola\\Desktop\\statiTwitter.txt";
		saving=new Saving();
		LinkedList<String>list=new LinkedList<String>();
		list.add("#earthquake");
		list.add("#magnitude");
		List<Status>status=null;
		try {
			status=searching.getTweetFromHashtag("#Cosenza", 100);
			//status=searching.getTweetFromListHashtag(list, 100, 66.24, -157.199, 100, "2019_05_03");
		//	for(Status s:status)
		//		System.out.println(s);
			saving.saveStatusAndImageAndOthers(status, nameFile);	
		}catch(TwitterException  e) {
			e.printStackTrace();
		}
		System.out.println(status.size());

	}//main

}//Launcher
