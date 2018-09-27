package com.project.dao;

//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Properties;

import com.microsoft.cognitive_services.speech_recognition.SpeechAPI.ExtendedExample;
import com.project.pojo.Customer;
import com.project.tts.TexttoSpeech;

public class CustomerDao {
	
	Customer c = new Customer();

	public void getdata(String table,String column,String phno)
	{
		System.out.println("in getdata");
		TexttoSpeech obj=new TexttoSpeech();
		ExtendedExample ex = new ExtendedExample();
		String name="error";
		try{
		Connection con = (new CustomerDao()).getConnection();
		Statement stmt = con.createStatement();
		System.out.println("connected");
		if(column=="transaction")
		{
			System.out.println("in transaction if loop");
			String sql = "SELECT TR1,TR2,TR3 FROM "+table+" WHERE MOBILE_NO = '"+phno+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				name = rs.getString(1)+rs.getString(2)+rs.getString(3);
//				obj.speak("Dear Customer, Your last three "+table+" account transactions are "+name);
				obj.speak("20-2-2018");
//				ex.main(null);
				}
		}
		else{
			System.out.println("before sql");
			String sql = "SELECT "+column+" FROM "+table+" WHERE MOBILE_NO = '"+phno+"'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("after result set");
			if(rs.next()){
				System.out.println("in if");
			name = rs.getString(1);}
			System.out.println(name);
			if(name==null || name=="")
			{
				obj.speak("Your "+table+column+" doesn't exist.");
				
			}
			else{
			obj.speak("Dear customer, Your "+table+" account "+column+" is ");
			obj.speak(name);
			}
		/*	while(rs.next()){
				name = rs.getString(1);
				System.out.println(name);
				obj.speak(name);
				ex.main(null);
				}    */
		}
		
		
	}
	catch (SQLException e) {
		e.printStackTrace();
		errorfunc2(phno);
	}
		
		}
	

	public String checkUserDao(String phone) 
	{
//		c.setPhno(phone);
		TexttoSpeech obj=new TexttoSpeech();
		ExtendedExample ex = new ExtendedExample();
		String name="error";
		try {

			Connection con = (new CustomerDao()).getConnection();
			Statement stmt = con.createStatement();
			System.out.println(phone);
			String sql = "SELECT FNAME,LNAME FROM customers WHERE MOBILE_NO = '"+phone+"'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
			name = rs.getString(1)+" "+rs.getString(2);
//			c.setName(name);
			obj.speak("Hello "+name+". Welcome to Standard Chartered Banking services. Please mention your required service");
			ex.main(null,phone);
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			errorfunc1(phone);
		}
		return name;
	}
	
	private void errorfunc1(String phone) {
		System.out.println("inside error func");
		TexttoSpeech ob=new TexttoSpeech();
		ob.speak("Your phone number is not registered.Please register first");
		ExtendedExample ee = new ExtendedExample();
		ee.main(null, phone);
		
		
	}

	private void errorfunc2(String phno) {
		System.out.println("inside error func");
		TexttoSpeech ob=new TexttoSpeech();
		ob.speak("Sorry your transaction cannot be completed.");
		ExtendedExample ee = new ExtendedExample();
		ee.main(null, phno);
		
	}

	public Connection getConnection()
	{
		//String user=null,pass=null,DB=null;
//		Properties prop = new Properties();
//		InputStream in = null;
//		try {
//			in = new FileInputStream("info.properties");
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			in = getClass().getClassLoader().getResourceAsStream("info.properties");
//			prop.load(in);
//			//prop.load(getClass().getClassLoader().getResourceAsStream("info.properties"));
//
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		String DB= prop.getProperty("DB");
//		String user = prop.getProperty("user");
//		String pass = prop.getProperty("pass");
//		System.out.println(DB);
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  
		
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
	}
		catch(Exception e){
			System.out.println(e);
		}
		return con;
}
}