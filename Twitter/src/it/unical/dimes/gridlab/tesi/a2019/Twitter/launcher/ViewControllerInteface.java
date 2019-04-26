package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;

import javax.swing.JTextField;

import it.unical.dimes.gridlab.tesi.a2019.Twitter.saving.Saving;
import it.unical.dimes.gridlab.tesi.a2019.Twitter.taking.Searching;

/**
 * Questa classe serve per implementare il pattern MVC
 * @author Nicola
 *
 */
public interface ViewControllerInteface {
	
	/**
	 * Questo metodo si occupa di ricercare i dati forniti dall'utente
	 * @param searching
	 * @param hashtag
	 * @param latitudine
	 * @param longitudine
	 * @param area
	 * @param data
	 * @param pathFile
	 */
	public void search(Searching searching, JTextField hashtag, JTextField latitudine, JTextField longitudine, JTextField area, JTextField data, String pathFile, PanelTable panelTable, Saving saving);
}//search
