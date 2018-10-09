package com.project.service;

import java.awt.event.WindowEvent;
import com.microsoft.cognitive_services.speech_recognition.SpeechAPI.SpeechToText;
import com.project.dao.CustomerDao;
import com.project.tts.TexttoSpeech;

public class CustomerService {
	static int count =0;
	static CustomerDao customer = new CustomerDao();

	public String checkPhone(String phone){
		return new CustomerDao().checkUserDao(phone);
	}


public static void saving(String s,String phone)
{
	s=s.toLowerCase();
	s=s.replace("saving", "abcd");
	if(s.contains("balance"))
	{	s=s.replace("balance", "");
		customer.getdata("savings","balance",phone,s);}
	else if(s.contains("interest")||s.contains("rate"))
	{s=s.replace("interest", "");
		customer.getdata("savings","interest_rate",phone,s);}
	else if(s.contains("transaction"))
	{s=s.replace("transaction", "");
		customer.getdata("savings","transaction",phone,s);}
	else
	{
		TexttoSpeech ob=new TexttoSpeech();
		ob.speak("Please mention if balance or interest rate or last three transactions");
		SpeechToText.main(null,phone,1);
	}
}
public static void credit(String s,String phone)
{	s=s.replace("credit", "abcd");
	if(s.contains("outstanding")||(s.contains("amount")&&!s.contains("payable")))
	{	
		s=s.replace("outstanding", "");s=s.replace("amount", "");
		customer.getdata("credit","due_amount",phone,s);
	}
	else if(s.contains("due")||s.contains("date"))
	{
		s=s.replace("due", "");s=s.replace("date", "");
		customer.getdata("credit","due_date",phone,s);
	}
	else if(s.contains("transaction"))
	{	
		s=s.replace("transaction", "");
		customer.getdata("credit","transaction",phone,s);
	}
	else if(s.contains("limit"))
	{	s=s.replace("limit", "");
		customer.getdata("credit","credit_card_limit",phone,s);
	}
	else if(s.contains("minimum")||s.contains("payable")||s.contains("amount"))
	{	s=s.replace("payable", "");s=s.replace("minimum", "");s=s.replace("amount", "");
		customer.getdata("credit","minimum_payable_amount",phone,s);
	}
	else if(s.contains("interest")||s.contains("rate"))
	{	
		s=s.replace("interest", "");s=s.replace("rate", "");
		customer.getdata("credit","interest_rate",phone,s);
	}
	else
	{
		TexttoSpeech ob=new TexttoSpeech();
		ob.speak("Please mention on of our services with regard to your credit account");
		ob.speak("due amount , due date , credit card limit , minimum payable amount , interest rate or last three transactions");
		SpeechToText.main(null,phone,2);
	}
}
public static void loan(String s,String phone) {
	s=s.replace("loan", "abcd");
if(s.contains("principle")||s.contains("principal"))
{
	s=s.replace("principal", "");s=s.replace("principle", "");
	customer.getdata("loan","principal_amount",phone,s);
}

else if(s.contains("interest")||s.contains("rate"))
{
	s=s.replace("interest", "");s=s.replace("rate", "abcd");
	customer.getdata("loan","interest_rate",phone,s);
}
else if(s.contains("pending"))
{	s=s.replace("pending", "");
	customer.getdata("loan","pending_amount",phone,s);
}
else if(s.contains("monthly")||s.contains("installment")||(s.contains("emi")&&!(s.contains("date")||s.contains("due"))))
{
	s=s.replace("monthly", "");s=s.replace("installment", "");s=s.replace("emi", "");
	customer.getdata("loan","EMI",phone,s);
}
else if(s.contains("due")||s.contains("date"))
{
	s=s.replace("due", "");s=s.replace("date", "");
	customer.getdata("loan","EMI_DUE_DATE",phone,s);
}
else
{
	TexttoSpeech ob=new TexttoSpeech();
	ob.speak("Please mention on of our services with regard to your credit account");
	ob.speak("pending amount ,emi due date , emi , minimum payable amount , interest rate or prinicpal amount");
	SpeechToText.main(null,phone,3);
}
}
public static void errorfunc(String phone)
{/*relevant error function*/
	/*call flow() again*/
	count++;
	if(count>2)
		error(phone);
	else {
	TexttoSpeech ob=new TexttoSpeech();
	ob.speak("The system could not identify your request.Please try again");
	SpeechToText.main(null,phone,0);
	}
}	

public static void error(String phone) {

	TexttoSpeech ob=new TexttoSpeech();
	ob.speak("The system cannot proceed further.");
	end_service(phone);
}
public static void new_service(String phone) {
	
	TexttoSpeech ob=new TexttoSpeech();
	ob.speak("Please mention another service if required else goodbye");
	SpeechToText.main(null,phone,0);
}
public static void end_service(String phone) {
	
	TexttoSpeech ob=new TexttoSpeech();
	ob.speak("Thank you for contacting us");
	SpeechToText.frame.dispatchEvent(new WindowEvent(SpeechToText.frame, WindowEvent.WINDOW_CLOSING));
}

}
