package it.unical.dimes.gridlab.tesi.a2019.Twitter.saving;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import au.com.bytecode.opencsv.CSVWriter;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import twitter4j.Status;

public abstract class SavingAbstract {
	 public SavingAbstract() {
		 
	 }//Constructor
	 
	 /**
	  * Memorizza su un file csv tutti gli stati presenti nella lista nel seguente ordine:        
	  * [Author]-[Data Pubblicazione]-[Stato]-[RT]-[Hashtag]-[Media]-[Place]-[Geolocalizzazione]-[Data Prelievo]
	  * @param list -->Lista contenente gli stati 
	  * @param nameFile -->Fornire il path completo del file (Es. "C:\Users\Nicola\Desktop\nameFile.csv")
	 * @throws WriteException 
	 * @throws BiffException 
	  */
	 public abstract void saveListOnCSV(List<Status>list, String nameFile)throws IOException, WriteException, BiffException;
	 
	 /**
	  * Questo metodo memorizza la lista degli stati (JSon) su un file di testo
	  * @param list -->Lista contenente gli stati 
	  * @param nameFile-->Fornire il path completo del file (Es. "C:\Users\Nicola\Desktop\nameFile.txt")
	  * @throws IOException
	  */
	 public abstract void saveListOnTXT(List<Status>list, String nameFile)throws IOException;
	 
	 /**
	  * Questo metodo a differenza degli altri, salva su un file di testo solo gli stati senza tutti i RT, e se non sono 
	  * null salva anche immagini e localizzazione.
	  * Le immagini vengono salvate con il nome "autore+(yyyy_mm_dd HH:mm:ss)"
	  * @param list-->Lista contenente gli stati 
	  * @param nameFile-->Fornire il path completo del file (Es. "C:\Users\Nicola\Desktop\nameFile.txt")
	  * @throws IOException
	  */
	 public abstract void saveStatusAndImageAndOthers(List<Status>list, String nameFile)throws IOException;
	 /**
	  * 
	  * @param nameFile -->Fornire il path completo del file (Es. "C:\Users\Nicola\Desktop\nameFile.csv")
	  * @return Restituisce la lista letta dal file
	  */
	 public abstract List<String>readFile(File file)throws IOException;

}//SavingAbstract
