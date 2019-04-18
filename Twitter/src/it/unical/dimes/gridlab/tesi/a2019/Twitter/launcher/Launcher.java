package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;

import it.unical.dimes.gridlab.tesi.a2019.Twitter.saving.*;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.taking.*;

import java.io.IOException;
import java.util.*;

import twitter4j.Status;
import twitter4j.TwitterException;

public class Launcher {
	private static SearchingAbstract searching=null;
	private static SavingAbstract saving=null;
	public static void main(String[]args) {
		String APIKey="hn3UBOFrP4WBLFVjqsCcYB3Dt";										//Application Settings/Consumer Key (API key)
		String APISecret="ovCke2aWfdW6TvhZ0dggL5mO320mzMlLZwDgcvNVUqlUsZbp9J";	  //Application Settings/Consumer Secret (API Secret)
		String AccessToken="1113747010694340608-csRTyytaX9TqM6zJLN3yPl5O0V2Yos";					 //Your Access Token/Access Token
		String AccessTokenSecret="QB57vcKjJLXQbqQXKQ4I5n6wfvVFuUBkS3MfhOC81sjzA";			  //Your Access Token/Access Token Secret
		searching=new Searching(APIKey, APISecret, AccessToken, AccessTokenSecret);
		String keyWord="#earthquake";
		String nameFile="C:\\Users\\Nicola\\Desktop\\statiTwitter.csv";
		saving=new Saving();
		List<Status>status=null;
		try {
			status=searching.getTweetFromHashtagAndLocation("#Cosenza", 100, 39.3099931, 16.2501929, 20, "2019_04_09");
			for(Status s:status)
				System.out.println(s);
			saving.saveList(status, nameFile);		
		}catch(TwitterException | IOException e) {
			e.printStackTrace();
		}

	}//main

}//Launcher
