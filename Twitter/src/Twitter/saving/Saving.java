package Twitter.saving;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import au.com.bytecode.opencsv.CSVWriter;
import twitter4j.Status;

public class Saving extends SavingAbstract{

	@Override
	public void saveList(List<Status> list, String nameFile) throws IOException {
		Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentTime=sdf.format(currentDate);
		List<String>oldList=readFile(new File(nameFile));
		FileWriter writer=new FileWriter(nameFile);
		if(oldList.size()!=0) {
			for(String s:oldList) 
				writer.append(s);
		}
		writer.append(currentTime);
		writer.append('\n');
		for(Status s:list) {
		//	 String[]column=new String[1];
		//	 column[0]=s.toString();
			 writer.append(s.toString());
		}
		writer.flush();
		writer.close();
	}//saveList

	@Override
	public List<String> readFile(File file) {
		List<String>list=new LinkedList<String>();
		try {
			Scanner inputStream=new Scanner(file);
			while(inputStream.hasNext()) {
				String data=inputStream.nextLine();
				list.add(data);
			}
			inputStream.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}//readFile

}//Saving
