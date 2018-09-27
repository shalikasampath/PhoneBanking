package com.controller;

import com.project.service.CustomerService;

public class Controller {

	static CustomerService a = new CustomerService();
public static int count = 0;
	public static void flow(String s1,String phone)
	{	
		System.out.println("in flow");
		String s = s1.toLowerCase();
		if(
				(s.contains("saving") && s.contains("credit") && s.contains("loan"))
				||(s.contains("saving") && s.contains("credit"))
				||(s.contains("credit") && s.contains("loan"))
				||(s.contains("saving") && s.contains("loan")) 
		  )
		{
			/*error stuff*/
			a.errorfunc();
		}
		else if(s.contains("saving"))
		{
			System.out.println("in saving");
			a.saving(s,phone);
			}
		else if(s.contains("credit"))
		{System.out.println("in credit");
		a.credit(s,phone);}
		else if((s.contains("loan")||(s.contains("emi")||(s.contains("pending_amt")||(s.contains("emi_due_date"))))))
		{
			System.out.println("in loan");
			a.loan(s,phone);}
		
		else
		{
			/*error stuff*/
			a.errorfunc();
		}
		
		
	}
	

	
}