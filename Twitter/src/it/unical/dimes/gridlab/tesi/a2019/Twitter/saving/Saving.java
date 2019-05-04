package it.unical.dimes.gridlab.tesi.a2019.Twitter.saving;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import au.com.bytecode.opencsv.CSVWriter;
import jdk.nashorn.internal.parser.JSONParser;
import twitter4j.GeoLocation;
import twitter4j.JSONObject;
import twitter4j.MediaEntity;
import twitter4j.Place;
import twitter4j.Status;
import twitter4j.URLEntity;

public class Saving extends SavingAbstract{

	@Override
	public void saveListOnCSV(List<Status> list, String nameFile) throws IOException {
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

	@Override
	public void saveListOnTXT(List<Status> list, String nameFile) throws IOException {
		Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String currentTime=sdf.format(currentDate);
		File file=new File(nameFile);
		FileWriter fw=new FileWriter(file, true);
		fw.write(currentTime);
		fw.write("\r\n");
		fw.write("\r\n");
		fw.write("\r\n");
		for(Status s: list) {
			fw.write(s.toString());
			fw.write("\r\n");
			fw.write("\r\n");
			fw.flush();
		}
		fw.write("\r\n");
		fw.write("\r\n");
		fw.write("\r\n");
		fw.write("\r\n");
		fw.close();
	}//saveListOnTXT

	@Override
	public void saveStatusAndImageAndOthers(List<Status> list, String nameFile) throws IOException {
		Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String currentTime=sdf.format(currentDate);
		File file=new File(nameFile);
		FileWriter fw=new FileWriter(file, true);
		fw.write(currentTime);
		fw.write("\r\n");
		fw.write("\r\n");
		fw.write("\r\n");
		for(Status s: list) {
			String author=s.getUser().getName();
			String status=s.getText();
	//		URLEntity[] urls=s.getURLEntities();
			MediaEntity[] media = s.getMediaEntities(); //get the media entities from the status
			int RT=s.getRetweetCount();
			GeoLocation geoLocation=s.getGeoLocation();
			Place place=s.getPlace();		
	        try {
				fw.write("AUTHOR:\r\t");
				fw.write(author);
				fw.write("\r\n");
				fw.write("STATUS:\r\t");
				fw.write(status);
				fw.write("\r\n");
				fw.write("#RT:\r\t");
				fw.write(""+RT);
				fw.write("\r\n");
				if(media.length!=0) {
					for(int i=0; i<media.length; i++) {
						fw.write("URL:\r\t");
						fw.write(media[i].getURL());
						fw.write("\r\n");
						fw.write("MEDIA:\r\t");
						fw.write(media[i].getMediaURL());
						fw.write("\r\n");
					}
				}
				if(place!=null && geoLocation!=null) {
					fw.write("PLACE:\r\t");
					fw.write(place.getFullName());
					fw.write("\r\n");
					fw.write("GEOLOCATION:\r\t");
					fw.write((double) geoLocation.getLatitude()+"\r\t"+(double)geoLocation.getLongitude());
					fw.write("\r\n");
				}
				else if(place!=null ) {
					fw.write("PLACE:\r\t");
					fw.write(place.getFullName());
					fw.write("\r\n");
				}
				else if(geoLocation!=null) {
					fw.write("GEOLOCATION:\r\t");
					fw.write((double) geoLocation.getLatitude()+"\r\t"+(double)geoLocation.getLongitude());
					fw.write("\r\n");
				}
				fw.write("\r\n");
				fw.flush();
	        }catch(NullPointerException e) {
				System.out.println("Immagine o Geolocalizzazione mancante");
			}
	        for (MediaEntity m : media) {
	            try {
	                URL url = new URL(m.getMediaURL());
	                InputStream in = new BufferedInputStream(url.openStream());
	                ByteArrayOutputStream out = new ByteArrayOutputStream();
	                byte[] buf = new byte[1024];
	                int n = 0;
	                while (-1 != (n = in.read(buf))) {
	                    out.write(buf, 0, n);
	                }
	                out.close();
	                in.close();
	                byte[] response = out.toByteArray();
	                FileOutputStream fos = new FileOutputStream(file.getParent() + "\\" + m.getId() + "." + getExtension(m.getType()));
	                fos.write(response);
	                fos.close();
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
		}
		fw.write("\r\n");
		fw.write("\r\n");
		fw.write("\r\n");
		fw.write("\r\n");
		fw.close();	
	}//saveStatusAndImageAndOthers
	
	private String getExtension(String type) {
        if (type.equals("photo")) {
            return "jpg";
        } else if (type.equals("video")) {
            return "mp4";
        } else if (type.equals("animated_gif")) {
            return "gif";
        } else {
            return "err";
        }
    }

}//Saving
