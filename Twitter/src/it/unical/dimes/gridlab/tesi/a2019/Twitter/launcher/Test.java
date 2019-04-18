package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;

import it.unical.dimes.gridlab.tesi.a2019.Twitter.saving.*;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.taking.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import twitter4j.Status;
import twitter4j.TwitterException;

public class Test {
	private static SearchingAbstract searching=null;
	private static SavingAbstract saving=null;
	public static void main(String[]args) {
		String APIKey="hn3UBOFrP4WBLFVjqsCcYB3Dt";										//Application Settings/Consumer Key (API key)
		String APISecret="ovCke2aWfdW6TvhZ0dggL5mO320mzMlLZwDgcvNVUqlUsZbp9J";	  //Application Settings/Consumer Secret (API Secret)
		String AccessToken="1113747010694340608-csRTyytaX9TqM6zJLN3yPl5O0V2Yos";					 //Your Access Token/Access Token
		String AccessTokenSecret="QB57vcKjJLXQbqQXKQ4I5n6wfvVFuUBkS3MfhOC81sjzA";			  //Your Access Token/Access Token Secret
		searching=new Searching(APIKey, APISecret, AccessToken, AccessTokenSecret);
		String nameFile="C:\\Users\\Nicola\\Desktop\\statiTwitter.csv";
		saving=new Saving();
		List<String>status=null;
		try {
			status=saving.readFile(new File(nameFile));
			for(String s:status)
				System.out.println(s);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
