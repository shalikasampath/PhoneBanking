package com.project.dao;

import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.controller.Controller;
import com.microsoft.cognitive_services.speech_recognition.SpeechAPI.SpeechToText;
import com.project.service.CustomerService;
import com.project.tts.TexttoSpeech;

public class CustomerDao {
	
	String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String DB_USERNAME = "hr";
	String DB_PASSWORD = "hr";
	

	public void getdata(String table,String column,String phno,String speech)
	{
		System.out.println("in getdata");
		TexttoSpeech tts=new TexttoSpeech();
		String result;
		try{
		Connection connection = (new CustomerDao()).getConnection();
		Statement statement = connection.createStatement();
		System.out.println("connected");
		if(column=="transaction")
		{
			System.out.println("in transaction if loop");
			String sql_query = "SELECT TR1,TR2,TR3 FROM "+table+" WHERE MOBILE_NO = '"+phno+"'";
			ResultSet rs = statement.executeQuery(sql_query);
			String[] transaction_input = new String[3];
			
			if(rs.next()==false)
			{
				tts.speak("Your "+table+" "+column+" does not exist.");
				CustomerService.new_service(phno);
			} 
			
			else
			{
				transaction_input[0]=rs.getString(1);
				transaction_input[1]=rs.getString(2);
				transaction_input[2]=rs.getString(3);
				
			String[][] final_words=new String[3][3];
	        
	        final_words=split(transaction_input);
	        System.out.println("split done");
	        final_words=date(final_words);
	        System.out.println("date done");
	        tts.speak("Dear Customer, Your last three "+table+" account transactions are ");
	        for(int i=0;i<3;i++)
	        	for(int j=0;j<3;j++)
	        		tts.speak(final_words[i][j]);
	        CustomerService.new_service(phno);
		}}
		else{
			String sql_query = "SELECT "+column+" FROM "+table+" WHERE MOBILE_NO = '"+phno+"'";
			ResultSet rs = statement.executeQuery(sql_query);
			System.out.println("after result set");
			column = column.replace("_", " ");
			if(rs.next()==false)
			{
				tts.speak("Your "+table+" "+column+" does not exist.");
				CustomerService.new_service(phno);
			}
			else {
				result = rs.getString(1);
				
				result = date(result);
				tts.speak("Dear customer, Your "+table+" account "+column+" is ");
				tts.speak(result);  
				
				Controller.flow(speech, phno);  
//				CustomerService.new_service(phno);
				}
			}		
		}
	catch (SQLException e) {
		e.printStackTrace();
		}
	}
	
	public String checkUserDao(String phone) 
	{

		TexttoSpeech tts=new TexttoSpeech();
		String name=null;
		try {

			Connection connection = (new CustomerDao()).getConnection();
			Statement statement = connection.createStatement();
			System.out.println(phone);
			String sql = "SELECT FNAME,LNAME FROM customers WHERE MOBILE_NO = '"+phone+"'";
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()==false) {
				tts.speak("Your mobile number is not registered with us. Please register to avail our services");
			}
			else
			{
			name = rs.getString(1)+" "+rs.getString(2);
			tts.speak("Hello "+name+". Welcome to Standard Chartered Banking services. Please mention your required service");
//			tts.speak("Hello "+name+".");
			SpeechToText.main(null,phone,0);
			}
	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
    public static String[][] split(String[] input)
    {	
    	String[][] final_words = new String[3][3];
    	String[] tempArray;
    	for(int i=0;i<3;i++)
        {
        	tempArray=input[i].split(" ");
        	for(int j=0;j<3;j++)
        	{
        		if(tempArray[j].equals("credit"))
        			final_words[i][j]="credited to";
        		else if(tempArray[j].equals("debit"))
        			final_words[i][j]="debited from";
        		else
        			final_words[i][j]=tempArray[j];

        	}
        }
    	return final_words;
    }
    
    public static String[][] date(String[][] input)
    {
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
    	Date date;
    	String suffix;
    	
    	for(int i=0;i<3;i++)
    	{
    		
			try {
				date = formatter.parse(input[i][2]);
				suffix=new SimpleDateFormat("d").format(date);
				int n=Integer.parseInt(suffix)%10;
				switch(n%10){
				case 1:suffix="st";
					break;
				case 2:suffix="nd";
					break;
				case 3:suffix="rd";
					break;
				default:suffix="th";
				}
	            input[i][2]=(new SimpleDateFormat("MMMM").format(date))+" "
	            		+(new SimpleDateFormat("d").format(date))
	            		+suffix+" "
	            		+(new SimpleDateFormat("YYYY").format(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            

    	}
    	return input;
    }
    
    public static String date(String input)
    {
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
    	Date date;
    	String suffix;
    		
			try {
				date = formatter.parse(input);
				suffix=new SimpleDateFormat("d").format(date);
				int n=Integer.parseInt(suffix)%10;
				switch(n%10){
				case 1:suffix="st";
					break;
				case 2:suffix="nd";
					break;
				case 3:suffix="rd";
					break;
				default:suffix="th";
				}
	            input=(new SimpleDateFormat("MMMM").format(date))+" "
	            		+(new SimpleDateFormat("d").format(date))
	            		+suffix+" "
	            		+(new SimpleDateFormat("YYYY").format(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
  	return input;
    } 
	
	public Connection getConnection()
	{

		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
		}
		catch(Exception e){
			System.out.println(e);
		}
		return connection;
	}
}
