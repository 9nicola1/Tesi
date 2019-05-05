package it.unical.dimes.gridlab.tesi.a2019.Twitter.test;

import java.util.List;

import twitter4j.HashtagEntity;
import twitter4j.Location;
import twitter4j.Status;

/**
 * 
 * @author Nicola
 *
 */
public interface AccessDBInterface {
	/**
	 * Inserisce nel DB la lista degli stati trovata
	 * @param listStatus
	 */
	public void insertListStatus(List<Status>listStatus);
	
	/**
	 * Inserisce nel DB la lista degli stati contenente le parole chiavi 
	 * @param listKeyStatus
	 */
	public void insertListKeyStatus(List<Status>listKeyStatus);
	
	/**
	 * Inserisce nel DB la lista degli stati con la posizione trovata
	 * @param listLocation
	 */
	public void insertListLocation(List<Location>listLocation);
	
	/**
	 * Inserisce nel DB la lista degli stati con gli hashtag trovati
	 * @param listHashtag
	 */
	public void insertListHashtag(List<HashtagEntity>listHashtag);

}//AccessDBInterface
