package Twitter.saving;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import au.com.bytecode.opencsv.CSVWriter;
import twitter4j.Status;

public abstract class SavingAbstract {
	 public SavingAbstract() {
		 
	 }//Constructor
	 
	 /**
	  * Memorizza su un file csv tutti gli stati presenti nella lista
	  * @param list -->Lista contenente gli stati 
	  * @param nameFile -->Fornire il path completo del file (Es. "C:\Users\Nicola\Desktop\nameFile.csv")
	  */
	 public abstract void saveList(List<Status>list, String nameFile)throws IOException;
	 
	 /**
	  * 
	  * @param nameFile -->Fornire il path completo del file (Es. "C:\Users\Nicola\Desktop\nameFile.csv")
	  * @return Restituisce la lista letta dal file
	  */
	 public abstract List<String>readFile(File file)throws IOException;

}//SavingAbstract
