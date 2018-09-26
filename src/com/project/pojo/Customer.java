package com.project.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {
	
	private int phno;
	private String name;
	
	public Customer(){}

	public int getPhno() {
		return phno;
	}

	public void setPhno(int phno) {
		this.phno = phno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Customer(int phno, String name) {
		super();
		this.phno = phno;
		this.name = name;
	};
	
	
}
