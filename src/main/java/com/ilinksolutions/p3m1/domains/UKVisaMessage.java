package com.ilinksolutions.p3m1.domains;

import java.io.Serializable;

/**
 *
 */
public class UKVisaMessage
{
    private int id;
    private String firstName;
    private String lastName;
    private String contactNo;
    private String email;

    public UKVisaMessage()
    {
    }

    public UKVisaMessage(int id, String firstName, String lastName, String contactNo, String email)
    {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNo = contactNo;
		this.email = email;
	}

	public Serializable getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UKVisaMessage [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", contactNo="
				+ contactNo + ", email=" + email + "]";
	}
	
}
