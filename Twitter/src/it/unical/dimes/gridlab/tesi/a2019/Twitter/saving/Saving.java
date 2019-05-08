package it.unical.dimes.gridlab.tesi.a2019.Twitter.saving;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.MediaEntity;
import twitter4j.Place;
import twitter4j.Status;


public class Saving extends SavingAbstract{

	@Override
	public void saveListOnCSV(List<Status> list, String nameFile) throws IOException, WriteException, BiffException {
		File file=new File(nameFile);
		Workbook workbook;
		WritableWorkbook wworkbook;
		Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String currentTime=sdf.format(currentDate);
		try {
		 workbook=Workbook.getWorkbook(file);
		 wworkbook= Workbook.createWorkbook(file,workbook);
		}catch(FileNotFoundException e) {
			wworkbook= Workbook.createWorkbook(file);
		}
		WritableSheet wsheet;
		if(wworkbook.getNumberOfSheets()==0)
			wsheet = wworkbook.createSheet("Dati Twitter",0);
		else
			wsheet = wworkbook.getSheet("Dati Twitter");
		int tmp=wsheet.getRows();
		int riga=0;
		if(tmp!=0)riga=tmp+1;
		Label label=null;
		for(Status s: list) {
			int colonna=0;
			//Aggiunta Author
			String author=s.getUser().getScreenName();
			label = new Label(colonna++, riga, author);
			wsheet.addCell(label);
			//Aggiunta data pubblicazione
			Date date=s.getCreatedAt();
			label = new Label(colonna++, riga, date.toString());
			wsheet.addCell(label);
			//Aggiunta stato
			String status=s.getText();
			label = new Label(colonna++, riga, status);
			wsheet.addCell(label);
			//Aggiunta RT
			int RT=s.getRetweetCount();
			label = new Label(colonna++, riga, ""+RT);
			wsheet.addCell(label);
			//Aggiunta Hashtag
			HashtagEntity[]hashtag=s.getHashtagEntities();
			if(hashtag.length!=0) {
				StringBuilder tag=new StringBuilder();
				for(int i=0; i<hashtag.length; i++) {	
					tag.append(hashtag[i].getText());
					tag.append('\n');
				}
				label = new Label(colonna++, riga, tag.toString());
				wsheet.addCell(label);
			}
			else
				colonna++;
			//Aggiunta media
			MediaEntity[] media = s.getMediaEntities(); 
			if(media.length!=0) {
				StringBuilder medias=new StringBuilder();
				for(int i=0; i<media.length; i++) {	
					medias.append(media[i].getText());
					medias.append('\n');
				}
				label = new Label(colonna++, riga, medias.toString());
				wsheet.addCell(label);
			}
			else
				colonna++;
			//Aggiunta Posto
			Place place=s.getPlace();
			if(place!=null ) {
				label = new Label(colonna++, riga, place.getFullName());
				wsheet.addCell(label);
			}
			else
				colonna++;
			GeoLocation geoLocation=s.getGeoLocation();
			if(geoLocation!=null) {
				label = new Label(colonna++, riga, ""+(double) geoLocation.getLatitude());
				wsheet.addCell(label);
				label = new Label(colonna++, riga, ""+(double)geoLocation.getLongitude());
				wsheet.addCell(label);
			}
			else
				colonna=colonna+2;
			label = new Label(colonna++, riga, currentTime);
			wsheet.addCell(label);
			riga++;
			saveImageAndVideo(currentDate, media, author,file.getParent());
		}
		wworkbook.write();
        wworkbook.close();
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
			String author=s.getUser().getScreenName();
			String status=s.getText();
	//		URLEntity[] urls=s.getURLEntities();
			HashtagEntity[]hashtag=s.getHashtagEntities();
			MediaEntity[] media = s.getMediaEntities(); //get the media entities from the status
			int RT=s.getRetweetCount();
			GeoLocation geoLocation=s.getGeoLocation();
			Place place=s.getPlace();	
			Date date=s.getCreatedAt();
	        try {
				fw.write("AUTHOR:\r\t");
				fw.write(author);
				fw.write("\r\n");
				fw.write("DATE:\r\t");
				fw.write(date.toString());
				fw.write("\r\n");
				fw.write("STATUS:\r\t");
				fw.write(status);
				fw.write("\r\n");
				fw.write("#RT:\r\t");
				fw.write(""+RT);
				fw.write("\r\n");
				if(hashtag.length!=0) {
					fw.write("TAG:\r\t");
					for(int i=0; i<hashtag.length; i++) {						
						fw.write(hashtag[i].getText().toUpperCase());
						fw.write("\r\t");

					}
					fw.write("\r\n");
				}
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
					fw.write("GEO:\r\t");
					fw.write((double) geoLocation.getLatitude()+"\r\t"+(double)geoLocation.getLongitude());
					fw.write("\r\n");
				}
				else if(place!=null ) {
					fw.write("PLACE:\r\t");
					fw.write(place.getFullName());
					fw.write("\r\n");
				}
				else if(geoLocation!=null) {
					fw.write("GEO:\r\t");
					fw.write((double) geoLocation.getLatitude()+"\r\t"+(double)geoLocation.getLongitude());
					fw.write("\r\n");
				}
				saveImageAndVideo(currentDate, media, author,file.getParent());
			   
				fw.write("\r\n");
				fw.flush();
	        }catch(NullPointerException e) {
				System.out.println("Immagine o Geolocalizzazione mancante");
			}
		}
		fw.write("\r\n");
		fw.write("\r\n");
		fw.write("\r\n");
		fw.write("\r\n");
		fw.close();	
	}//saveStatusAndImageAndOthers
	
	private void saveImageAndVideo(Date currentDate, MediaEntity[] media, String author, String pathFile) throws IOException {
		for (MediaEntity m : media) {
			if(m.getType().equals("photo")) {
	            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	            String currentTime2=sdf2.format(currentDate);
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
	                String path=pathFile + "\\" +currentTime2+"-"+author+"-"+m.getId() + "." + getExtension(m.getType());
	                FileOutputStream fos = new FileOutputStream(path);
	                fos.write(response);
	                fos.close();
	            } catch (FileNotFoundException ex) {
	                ex.printStackTrace();
	                String path=pathFile + "\\" +currentTime2+"-"+m.hashCode() + "." + getExtension(m.getType());
	                FileOutputStream fos = new FileOutputStream(path);
	                System.out.println(path);
	            }
			}
        }	
	}//saveImageAndVideo

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
