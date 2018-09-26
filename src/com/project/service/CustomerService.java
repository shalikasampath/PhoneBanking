package com.project.service;

import com.project.dao.CustomerDao;

public class CustomerService {
	static int count =0;
	static CustomerDao a = new CustomerDao();

	public String checkPhone(String phone){
		
		
		return new CustomerDao().checkUserDao(phone);
	}


public static void saving(String s,String phone)
{
	if(s.contains("balance"))
	{
		System.out.println("in savings oda balance");
		a.getdata("savings","balance",phone);}
	else if(s.contains("interest")||s.contains("rate"))
	{a.getdata("savings","interest_rate",phone);}
	else if(s.contains("transaction"))
	{a.getdata("savings","transaction",phone);}
	else
	{count++;
	
	
	if(count<3)
	{/*tts and stt*//*try again tts message*/
	saving(s,phone);
	}
	else
	{count =0; errorfunc();}
	}
}
public static void credit(String s,String phone)   // RE-VERIFY
{
	if(s.contains("outstanding")||(s.contains("amount")&&!s.contains("payable")))
	{a.getdata("credit","due_amt",phone);}
	else if(s.contains("due")||s.contains("date"))
	{a.getdata("credit","due_date",phone);}
	else if(s.contains("transaction"))
	{a.getdata("credit","transaction",phone);}
	else if(s.contains("limit"))
	{a.getdata("credit","limit",phone);}
	else if(s.contains("minimum")||s.contains("payable")||s.contains("amount"))
	{a.getdata("credit","min_payble_amt",phone);}
	else if(s.contains("interest")||s.contains("rate"))
	{a.getdata("credit","interest_rate",phone);}
	else
	{count++;
	if(count<3)
	{/*try again tts message*/
		credit(s,phone);
	}
	else
	{count =0; errorfunc();}
	}
}
public static void loan(String s,String phone) // SAY EMI DUE DATE
{if(s.contains("principle")||s.contains("principal"))
{a.getdata("loan","principal_amt",phone);}
else if(s.contains("interest")||s.contains("rate"))
{a.getdata("loan","interest_rate",phone);}
else if(s.contains("pending"))
{a.getdata("loan","pending_amt",phone);}
else if(s.contains("monthly")||s.contains("installment")||(s.contains("emi")&&!(s.contains("date")||s.contains("due"))))
{a.getdata("loan","EMI",phone);}
else if(s.contains("due")||s.contains("date"))
{a.getdata("loan","EMI_DUE_DATE",phone);}
else
{count++;
if(count<3)
{/*try again tts message*/
loan(s,phone);
}
else
{count =0; errorfunc();}
}}
public static void errorfunc()
{/*relevant error function*/
	/*call flow() again*/
	System.out.println("inside error func");
}	
}