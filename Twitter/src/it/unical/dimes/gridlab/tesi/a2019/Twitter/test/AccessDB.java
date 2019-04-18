package it.unical.dimes.gridlab.tesi.a2019.Twitter.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Statement;

import twitter4j.HashtagEntity;
import twitter4j.Location;
import twitter4j.Status;

/**
 * 
 * @author Nicola
 *
 */
public class AccessDB implements AccessDBInterface {
	
	private String urlDB="jdbc:mysql://localhost:3306/testtwitter";;
	private String driverMysql="com.mysql.jdbc.Driver";
	private Connection connection;
	public AccessDB() {
		try {
			Class.forName(driverMysql);
		    connection=(Connection) DriverManager.getConnection(urlDB,"root","9nicola1");
		}catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}//Constructor

	@Override
	public void insertListStatus(List<Status> listStatus) {
		if(listStatus.size()==0) {
			throw new IllegalArgumentException("Lista Status vuota!");
		}
		for(Status s:listStatus) {
			String queryCheck="SELECT * FROM listastatus WHERE Status='"+s.getText()+"'";
			try{
				PreparedStatement pst=((java.sql.Connection) connection).prepareStatement(urlDB);
				ResultSet rs=pst.executeQuery(queryCheck);
				if(rs.next()) {
					System.out.println("Status gia esistente");
					System.out.println(s.getText());
				}
			}catch(SQLException e) {
				System.out.println("SQLException in insertListStatus!");
				System.out.println("Errore nella ricerca dello stato: ");
				System.out.println(s.getText());
				e.printStackTrace();
			}
			String query="INSERT INTO listastatus VALUES('"+s.getText()+"')";
			try {
				Statement pst=(Statement) connection.createStatement();
				pst.executeUpdate(query);
				 pst.close();
			}catch(SQLException e) {
				System.out.println("SQLException in insertListStatus!");
				System.out.println("Errore nell'inserire lo stato: ");
				System.out.println(s.getText());
				e.printStackTrace();
			}
		}	
	}//insertListStatus

	@Override
	public void insertListKeyStatus(List<Status> listKeyStatus) {
		if(listKeyStatus.size()==0) {
			throw new IllegalArgumentException("Lista Status vuota!");
		}
		for(Status s:listKeyStatus) {
			String queryCheck="SELECT * FROM listakeystatus WHERE keystatus='"+s.getText()+"'";
			try{
				PreparedStatement pst=((java.sql.Connection) connection).prepareStatement(urlDB);
				ResultSet rs=pst.executeQuery(queryCheck);
				if(rs.next()) {
					System.out.println("Status gia esistente");
					System.out.println(s.getText());
				}
			}catch(SQLException e) {
				System.out.println("SQLException in insertListKeyStatus!");
				System.out.println("Errore nella ricerca dello stato: ");
				System.out.println(s.getText());
				e.printStackTrace();
			}
			String query="INSERT INTO listakeystatus VALUES('"+s.getText()+"')";
			try {
				Statement pst=(Statement) connection.createStatement();
				pst.executeUpdate(query);
				 pst.close();
			}catch(SQLException e) {
				System.out.println("SQLException in insertListKeyStatus!");
				System.out.println("Errore nell'inserire lo stato: ");
				System.out.println(s.getText());
				e.printStackTrace();
			}
		}	
	}//insertListKeyStatus

	@Override
	public void insertListLocation(List<Location> listLocation) {
		// TODO Auto-generated method stub
		
	}//insertListLocation

	@Override
	public void insertListHashtag(List<HashtagEntity> listHashtag) {
		// TODO Auto-generated method stub
		
	}//insertListHashtag

}//AccessDB
