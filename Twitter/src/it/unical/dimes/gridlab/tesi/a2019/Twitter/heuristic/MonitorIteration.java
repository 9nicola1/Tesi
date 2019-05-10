package it.unical.dimes.gridlab.tesi.a2019.Twitter.heuristic;

import java.util.HashSet;

import twitter4j.Status;

public class MonitorIteration extends Thread {
	private Iteration iteration;
	
	public MonitorIteration(Iteration iteration) {
		this.iteration=iteration;
	}//Constructor
	
	public void check() {
		this.iteration.configureIteration();
		HashSet<Status>status=null;
		if(this.iteration.allertHashtag() || this.iteration.allertLocation()) {
			status=this.iteration.getStatusAfterAllert();
			System.out.println("TRUE");
		}
		else
			System.out.println("FALSE");
		if(status!=null) {
			for(Status s:status) {
				System.out.println(s.getText());
			}
		}
	}//update
	
	@Override
	public void run() {
		//TODO
	}//run
}
