package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;

import java.io.IOException;
import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterException;

public class ThreadSearching extends Thread{

	/**
	 * Questo metodo aggiorna la tabella degli stati e attende un numero di secondi per la ricerca
	 * @param millis
	 * @param panelTable
	 * @param currentTime
	 * @param status
	 */
	public void sleepAndUpdate(int millis, PanelTable panelTable, String currentTime, List<Status> status) {
		try {
			for(Status s:status) {
				Object[]obj= {currentTime, s.getText().toString()};
				panelTable.dtm.addRow(obj);
				panelTable.repaint();
			}
			sleep(millis);	//15 minuti
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}//sleepAndUpdate

}//ThreadSearching
