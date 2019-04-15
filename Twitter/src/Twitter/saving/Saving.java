package Twitter.saving;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import au.com.bytecode.opencsv.CSVWriter;
import twitter4j.Status;

public class Saving extends SavingAbstract{

	@Override
	public void saveList(List<Status> list, String nameFile) throws IOException {
		 CSVWriter csvWriter = new CSVWriter(new FileWriter(nameFile));
		 for(Status s:list) {
			 String[]column=new String[1];
			 column[0]=s.toString();
			 csvWriter.writeNext(column);
		 }
		csvWriter.close();
	}//saveList

	@Override
	public List<String> readFile(String nameFile) {
		List<String>list=new LinkedList<String>();
		File file=new File(nameFile);
		try {
			Scanner inputStream=new Scanner(file);
			while(inputStream.hasNext()) {
				String data=inputStream.next();
				list.add(data);
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}//readFile

}//Saving
