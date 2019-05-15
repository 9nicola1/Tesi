package it.unical.dimes.gridlab.tesi.a2019.Twitter.classifier;


public class ModelTweet {
	private String author;
	private String date;
	private String tweet;
	private String rt;
	private String[]hashtag;
	private String[]media;
	private String place;
	private String latitude;
	private String longitude;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	public String getRt() {
		return rt;
	}
	public void setRt(String rt) {
		this.rt = rt;
	}
	public String[] getHashtag() {
		return hashtag;
	}
	public void setHashtag(String[] hashtag) {
		this.hashtag = hashtag;
	}
	public String[] getMedia() {
		return media;
	}
	public void setMedia(String[] media) {
		this.media = media;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("AUTHOR: "+'\t'+author+'\n');
		sb.append("DATE: "+'\t'+date+'\n');
		sb.append("TWEET: "+'\t'+tweet+'\n');
		sb.append("#RT: "+'\t'+rt+'\n');
		if(hashtag!=null) {
			sb.append("HASHTAG:");
			for(String s:hashtag) {
				sb.append('\t'+s);
			}
			sb.append('\n');
		}
		if(media!=null) {
			sb.append("MEDIA:");
			for(String s:media) {
				sb.append('\t'+s);
			}
			sb.append('\n');
		}
		if(place!=null)
			sb.append("PLACE: "+'\t'+place+'\n');
		if(latitude!=null)
			sb.append("LATITUDE: "+'\t'+latitude+'\n');
		if(longitude!=null)
			sb.append("LONGITUDE: "+'\t'+longitude+'\n');
		return sb.toString();
	}//toString
	
	@Override
	public boolean equals(Object x) {
		if(!(x instanceof ModelTweet))return false;
		if(x==this)return true;
		ModelTweet model=(ModelTweet)x;
		return model.author.equals(this.author)&&model.tweet.equals(this.tweet);
	}//equals

}//ModelTweet
