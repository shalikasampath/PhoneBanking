package com.project.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.project.service.CustomerService;

@Path("/customer")
public class CustomerResource {
	
	@GET
	@Path("/{phone}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPhone(@PathParam("phone") String phone){
		System.out.println("Hello2");
		CustomerService cs = new CustomerService();
		String name = cs.checkPhone(phone);
		System.out.println("Hello"+name);
		return "Welcome "+name;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String check(){
		System.out.println("Inside customer");
		return "Hello";
	}
}
