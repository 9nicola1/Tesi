package it.unical.dimes.gridlab.tesi.a2019.Twitter.heuristic;


import twitter4j.GeoLocation;

public	class Location{
	private int latitudine;
	private int longitudine;
	private double realLatitude;
	private double realLongitude;
	
	public Location(GeoLocation g) {
		if(g!=null) {
			this.latitudine=(int) Math.floor(g.getLatitude());
			this.longitudine=(int) Math.floor(g.getLongitude());
			this.realLatitude=(double) g.getLatitude();
			this.realLongitude=(double) g.getLongitude();
		}
	}//Constructor
	
	public double getRealLatitude() {
		return realLatitude;
	}

	public void setRealLatitude(double realLatitude) {
		this.realLatitude = realLatitude;
	}

	public double getRealLongitude() {
		return realLongitude;
	}

	public void setRealLongitude(double realLongitude) {
		this.realLongitude = realLongitude;
	}

	public int getLatitudine() {
		return latitudine;
	}//getLatitudine
	
	public void setLatitudine(int latitudine) {
		this.latitudine = latitudine;
	}//setLatitudine
	
	public int getLongitudine() {
		return longitudine;
	}//getLongitudine
	
	public void setLongitudine(int longitudine) {
		this.longitudine = longitudine;
	}//setLongitudine
	
	@Override
	public boolean equals(Object x) {
		if(!(x instanceof Location))return false;
		if(x==this)return true;
		return ((Location)x).getLatitudine()==this.latitudine && ((Location)x).getLongitudine()==this.longitudine;
	}//equals
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Latitudine: "+this.latitudine);
		sb.append('\n');
		sb.append("Longitudine: "+this.longitudine);
		return sb.toString();
	}//toString
	
}//Location