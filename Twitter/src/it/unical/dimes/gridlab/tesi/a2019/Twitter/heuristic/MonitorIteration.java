package it.unical.dimes.gridlab.tesi.a2019.Twitter.heuristic;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;


import it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher.DrawGraph;
import twitter4j.Status;

public class MonitorIteration extends Thread {
	private Iteration iteration;
	private DrawGraph barChart;
	
	public MonitorIteration(Iteration iteration, DrawGraph barChart) {
		if(this.barChart!=null)
			this.barChart.removeAllHashtag();
		this.iteration=iteration;
		this.barChart=barChart;
	}//Constructor
	
	public void check() {
		this.iteration.configureIteration();
		TreeMap<String, Integer>hashtagNumber=this.iteration.getHashtagNumber();
		if(hashtagNumber.size()!=0) {
			barChart.removeAllHashtag();
			List<Integer>score=new LinkedList<Integer>();
			List<String>strings=new LinkedList<String>();
			int occ=0;
			Set<String> keySet = hashtagNumber.keySet();
			for(String s:keySet){
				Integer value = hashtagNumber.get(s);
				if(value>occ) {
					score.add(value);
					strings.add(s);
					System.out.println(s+" "+value);
					occ=value;
				}
			}
			barChart.setList(score,strings, occ);
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
