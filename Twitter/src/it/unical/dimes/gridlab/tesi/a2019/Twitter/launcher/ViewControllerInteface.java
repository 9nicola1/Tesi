package it.unical.dimes.gridlab.tesi.a2019.Twitter.launcher;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
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
	 * 
	 * @param searching
	 * @param listModel
	 * @param pathFile
	 * @param panelTable
	 * @param saving
	 */
	public void normalSearch(Searching searching, DefaultListModel<String> listModel, String pathFile, PanelTable panelTable,
			PanelAlert panelAlert,PanelAlert panelNoAlert, DrawGraph barChart, Saving saving, JButton avvia);
	
	/**
	 * Questo metodo si occupa di ricercare i dati forniti dall'utente
	 * @param searching
	 * @param listModel
	 * @param latitudine
	 * @param longitudine
	 * @param area
	 * @param data
	 * @param pathFile
	 * @param panelTable
	 * @param saving
	 */
	public void advanceSearch(Searching searching, DefaultListModel<String> listModel, JTextField latitudine, JTextField longitudine,
			JTextField area, JTextField data, String pathFile, PanelTable panelTable,PanelAlert panelAlert, PanelAlert panelNoAlert, DrawGraph barChart, Saving saving,
			JButton avvia);
}//search
