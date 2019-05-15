package it.unical.dimes.gridlab.tesi.a2019.Twitter.classifier;

import twitter4j.HashtagEntity;
import twitter4j.MediaEntity;
import twitter4j.Status;

public class UtilityClassifier {
	
	/**
	 * Da uno stato crea un oggetto ModelTweet
	 * @param s
	 * @return
	 */
	public static ModelTweet createModelTweet(Status s) {
		ModelTweet model=new ModelTweet();
		model.setAuthor(s.getUser().getScreenName());
		model.setDate(s.getCreatedAt().toString());
		model.setTweet(s.getText());
		model.setRt(""+s.getRetweetCount());
		HashtagEntity[]hashtag=s.getHashtagEntities();
		if(hashtag!=null) {
			String[]hashtagString=new String[hashtag.length];
			for(int i=0; i<hashtag.length; i++) {
				hashtagString[i]=hashtag[i].getText();
			}
			model.setHashtag(hashtagString);
		}
		MediaEntity[] media = s.getMediaEntities(); 
		if(media!=null) {
			String[]mediaString=new String[media.length];
			for(int i=0; i<media.length; i++) {
				mediaString[i]=media[i].getText();
			}
			model.setMedia(mediaString);
		}
		if(s.getPlace()!=null)
			model.setPlace(s.getPlace().getFullName());
		if(s.getGeoLocation()!=null) {
			model.setLatitude(""+(double)s.getGeoLocation().getLatitude());
			model.setLongitude(""+(double)s.getGeoLocation().getLongitude());
		}	
		return model;
	}//createModelTweet
	
	/**
	 * Riceve una Stringa che rappresenta lo stato di un tweet e va ad eliminare
	 * tutte le parole di lunghezza 1, 2, 3.
	 * @param text
	 * @return
	 */
	public static String filterToStatus(String text) {
		//TODO
		return null;
	}//filterToStatus

	/**
	 * Dalla stringa tweet fa una operazione di classificazione tra il dataset positivo e il 
	 * dataset negativo. 
	 * Restituisce una probabilità di classificazione che poi in base alla percentuale viene classificato
	 * come positivo, negativo oppure true/false
	 * @param tweet
	 * @param positive
	 * @param negative
	 * @return
	 */
	public static int getProbability(ModelTweet tweet, ModelTweet[] positive, ModelTweet[] negative) {
		// TODO Auto-generated method stub
		return 0;
	}//getProbability

}
