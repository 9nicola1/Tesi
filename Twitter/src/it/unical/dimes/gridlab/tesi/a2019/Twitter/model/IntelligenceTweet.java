package it.unical.dimes.gridlab.tesi.a2019.Twitter.model;

import java.util.*;

import twitter4j.*;

public class IntelligenceTweet {

	private List<Status>status;
	
	
	public void addStatus(LinkedList<Status>s) {
		for(Status i:s)
			this.status.add(i);
	}//addStatus
	
	public List<Status>getStatus(){
		return this.status;
	}//getStatus
	
	public void setStatus(List<Status>status) {
		this.status=status;
	}//setStatus
	
	public void removeStatus(Status s) {
		int x=0;
		for(Status i:this.status) {
			if(this.status.equals(i))
				this.status.remove(x);
			x++;
		}
	}//removeStatus
	
	
	
}//IntelligenceTweet
