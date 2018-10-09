package com.controller;

import com.project.service.CustomerService;

public class Controller {

	public static void flow(String speech,String phone)
	{	
		
		System.out.println("in flow");
		String speech_text = speech;

		 if(speech_text.contains("saving"))
		{
			System.out.println("in saving");
			CustomerService.saving(speech_text,phone);
		}
		else if(speech_text.contains("credit"))
		{
			System.out.println("in credit");
			CustomerService.credit(speech_text,phone);
		}
		else if(speech_text.contains("loan")||speech_text.contains("lone")||speech_text.contains("emi")||speech_text.contains("pending")||speech_text.contains("principle")||(speech.contains("principal")))
		{
			System.out.println("in loan");
			CustomerService.loan(speech_text,phone);
		}
		else if(speech_text.contains("abcd"))
		{
			CustomerService.new_service(phone);
		}
		else
		{
			CustomerService.errorfunc(phone);
		}
		
		
	}
	

	
}
