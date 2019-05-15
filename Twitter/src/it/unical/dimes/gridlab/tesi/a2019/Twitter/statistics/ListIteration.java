package it.unical.dimes.gridlab.tesi.a2019.Twitter.statistics;

import java.util.LinkedList;

public class ListIteration {
	private LinkedList<Iteration>iterations=new LinkedList<Iteration>();
	private LinkedList<String>paroleCercate=new LinkedList<String>();
	private int threshold;
	private int lengthList;
	
	public ListIteration(LinkedList<String>paroleCercate, int threshold, int lengthList) {
		this.paroleCercate=paroleCercate;
		this.threshold=threshold;
		this.lengthList=lengthList;
	}//Constructor
	
	public void addIteration(Iteration iteration) {
		if(iterations.size()==0 || iterations.size()<threshold)
			iterations.add(iteration);
		else {
			if(iterations.size()==lengthList) {
				iterations.add(iteration);
				iterations.removeFirst();
			}
		}
	}//addIteration
	
	

}//ListIteration
