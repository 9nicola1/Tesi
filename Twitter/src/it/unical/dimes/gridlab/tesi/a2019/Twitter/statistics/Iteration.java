package it.unical.dimes.gridlab.tesi.a2019.Twitter.statistics;

import java.util.*;

import twitter4j.*;

public class Iteration {
	
	private LinkedList<Status>status;
	private int threshold;
	private HashMap<Location, Integer>geoLocationNumber=new HashMap<Location, Integer>();
	private HashMap<String, Integer>hashtagNumber=new HashMap<String, Integer>();
	private LinkedList<String>allertHashtag=new LinkedList<String>();
	private LinkedList<Location>allertLocation=new LinkedList<Location>();
	private int sizeStatus;
	
	public Iteration(LinkedList<Status>status, int threshold) {
		this.status=status;
		this.sizeStatus=status.size();
		this.threshold=threshold;
	}//Constructor
	
	public int getSizeIteration() {
		return this.sizeStatus;
	}
	
	public LinkedList<String> getAllertHashtag() {
		return allertHashtag;
	}

	public void setAllertHashtag(LinkedList<String> allertHashtag) {
		this.allertHashtag = allertHashtag;
	}

	public LinkedList<Location> getAllertLocation() {
		return allertLocation;
	}

	public void setAllertLocation(LinkedList<Location> allertLocation) {
		this.allertLocation = allertLocation;
	}

	/**
	 * Nel momento in cui c'è stato un allert si può invocare il metodo per ottenere gli stati
	 * Se una delle due liste è vuota mettere null come parametro.
	 * Usiamo HashSet per non avere stati duplicati
	 * @param hashtag
	 * @param location
	 * @return
	 */
	public HashSet<Status>getStatusAfterAllert(){
		HashSet<Status>list=new HashSet<Status>();
		if(this.allertHashtag!=null) {
			for(String s:this.allertHashtag) {
				for(Status st:this.status) {
					HashtagEntity[]h=st.getHashtagEntities();
					for(HashtagEntity he:h) {
						if(s.equals(he.getText()))
							list.add(st);
					}
				}
			}
		}
		if(this.allertLocation!=null) {
			for(Location l:this.allertLocation) {
				for(Status st:this.status) {
					GeoLocation g=st.getGeoLocation();
					if(l.getLatitudine()==(int) Math.floor(g.getLatitude())
							&& l.getLongitudine()==(int) Math.floor(g.getLongitude()))
						list.add(st);
				}
			}
		}
		return list;
	}//getStatusAfterAllert
	
	/**
	 * Metodo che inserisce in una LinkedList gli hashtag che superano la soglia
	 * Si usa una LinkedList perché si è certi che non ci sono ripetizioni dopo la
	 * scrematura effettuata nel metodo configureIteration
	 * @return bolean
	 */
	public boolean allertHashtag(){
		System.out.println("ALLERT HASHTAG");
		Set<String> keySet = hashtagNumber.keySet();
		for(String s:keySet){
		     Integer value = hashtagNumber.get(s);
		     if(value>=threshold)this.allertHashtag.add(s);
		}
		System.out.println(this.hashtagNumber.size());
		System.out.println(this.allertHashtag.size());
		return this.allertHashtag.size()>0;
	}//allertHashtag
	
	/**
	 * Metodo che inserisce in una LinkedList le Location che superano la soglia
	 * Si usa una LinkedList perché si è certi che non ci sono ripetizioni dopo la
	 * scrematura effettuata nel metodo configureIteration
	 * @return boolean
	 */
	public boolean allertLocation(){
		System.out.println("ALLERT LOCATION");
		Set<Location> keySet = geoLocationNumber.keySet();
		for(Location l:keySet){
		     int value = geoLocationNumber.get(l);
		     if(value>=threshold)this.allertLocation.add(l);
		}
		System.out.println(this.geoLocationNumber.size());
		System.out.println(this.allertLocation.size());
		return this.allertLocation.size()>0;
	}//allertLocation
	
	
	
	
	/**
	 * Questo metodo inserisci nelle HashMap tutti i valori contenuti
	 * nella LinkedList<Status>status 
	 */
	public void configureIteration() {	
		for(Status s:this.status) {
			HashtagEntity[]hashtag=s.getHashtagEntities();
			GeoLocation geoLocation=s.getGeoLocation();
			for(HashtagEntity h:hashtag) {
				if(h!=null) {
					try {
						if(hashtagNumber.containsKey(h.getText().toUpperCase())) {
							int number=hashtagNumber.get(h.getText().toUpperCase());
							hashtagNumber.remove(h.getText().toUpperCase());
							number++;
							hashtagNumber.put(h.getText().toUpperCase(), number);
						}
						else
							hashtagNumber.put(h.getText().toUpperCase(), 1);
					}catch(NullPointerException e) {
							hashtagNumber.put(h.getText().toUpperCase(), 1);
					}
				}
			}
			if(geoLocation!=null) {
				Location l=new Location(geoLocation);
				try {
					if(geoLocationNumber.containsKey(l)) {
						int number=geoLocationNumber.get(l);
						number++;
						geoLocationNumber.remove(l);
						geoLocationNumber.put(l, number);
					}
					else
						geoLocationNumber.put(l, 1);
				}catch(NullPointerException e) {
					geoLocationNumber.put(l, 1);
				}
			}
		}	
	}//updateIteration

	public LinkedList<Status> getStatus() {
		return status;
	}//getStatus
	
	public void setStatus(LinkedList<Status> status) {
		this.status = status;
	}//setStatus
	
	public int getThreshold() {
		return threshold;
	}//getThreshold
	
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}//setThreshold
	
	public HashMap<Location, Integer> getGeoLocationNumber() {
		return geoLocationNumber;
	}//getGeoLocationNumber
	
	public void setGeoLocationNumber(HashMap<Location, Integer> geoLocationNumber) {
		this.geoLocationNumber = geoLocationNumber;
	}//setGeoLocationNumber
	
	public TreeMap<String, Integer> getHashtagNumber() {
		String[] keys = hashtagNumber.keySet().toArray(new String[0]);
		Arrays.sort(keys, new MapValueKeyComparator<String,Integer>(hashtagNumber));
		TreeMap<String, Integer>tmp=new TreeMap<String, Integer>();
		for(String s:keys){
		     Integer value = hashtagNumber.get(s);
		  //   System.out.println(s+" "+value);
		     tmp.put(s, value);
		}
		return tmp;
	}//getHashtagNumber
	
	public void setHashtagNumber(HashMap<String, Integer> hashtagNumber) {
		this.hashtagNumber = hashtagNumber;
	}//setHashtagNumber
	
	class MapValueKeyComparator<K,V extends Comparable<? super V>> implements Comparator<K> {
		private Map<K,V> map;

		public MapValueKeyComparator(Map<K,V> map) {
			this.map = map;
		}
		public int compare(K k1, K k2) {
			V v1 = map.get(k1);
			V v2 = map.get(k2);
			return v1.compareTo(v2);
		}
	}//MapValueKeyComparator
	
}//Iteration
