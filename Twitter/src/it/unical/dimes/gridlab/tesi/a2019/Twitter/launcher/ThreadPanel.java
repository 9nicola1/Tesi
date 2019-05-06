package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;

public class ThreadPanel extends Thread{
	private Object[]obj;
	private PanelTable panelTable;
	public void update(Object[]obj, PanelTable panelTable) {
		this.obj=obj;
		this.panelTable=panelTable;
		panelTable.dtm.addRow(obj);
	}//update
	
	@Override
	public void run() {
		//TODO
	}//run
}//ThreadPanel
