package it.unical.dimes.gridlab.tesi.a2019.Twitter.heuristic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeMap;

import it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher.BarChart;
import twitter4j.Status;

public class MonitorIteration extends Thread {
	private Iteration iteration;
	private BarChart barChart;
	
	public MonitorIteration(Iteration iteration, BarChart barChart) {
		this.iteration=iteration;
		this.barChart=barChart;
	}//Constructor
	
	public void check() {
		this.iteration.configureIteration();
		TreeMap<String, Integer>hashtagNumber=this.iteration.getHashtagNumber();
		if(hashtagNumber.size()!=0) {
			barChart.removeAllHashtag();
			barChart.add(hashtagNumber, iteration.getThreshold());
			barChart.repaint();

		}
		HashSet<Status>status=null;
		if(this.iteration.allertHashtag() || this.iteration.allertLocation()) {
			status=this.iteration.getStatusAfterAllert();
		/*	LinkedList<String>allertHashtag=this.iteration.getAllertHashtag();
			if(allertHashtag.size()!=0) {
				
			}*/
		}
		
		//__________________________________________
		//Qui abbiamo tutti i possibili stati allert
		//__________________________________________
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
