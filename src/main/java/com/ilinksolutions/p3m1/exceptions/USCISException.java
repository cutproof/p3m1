package com.ilinksolutions.p3m1.exceptions;

import java.util.List;
import java.util.ArrayList;

public class USCISException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ErrorCode code = null;
	private List<Throwable> exceptions = null; 

	public USCISException(ErrorCode code)
	{
		super();
		this.code = code;
	}

	public USCISException(String message, ErrorCode code)
	{
		super(message);
		this.code = code;
	}

	public USCISException(Throwable cause, ErrorCode code)
	{
		super(cause);
		this.code = code;
	}
	
	public USCISException(String message, Throwable cause, ErrorCode code)
	{
		super(message, cause);
		this.code = code;
	}

	public ErrorCode getCode()
	{
		return this.code;
	}

	public List<Throwable> getExceptions()
	{
		return exceptions;
	}

	public void setExceptions(List<Throwable> _x)
	{
		this.exceptions = _x;
	}
	
	public void addException(Throwable _x)
	{
		if(exceptions == null)
		{
			exceptions = new ArrayList<Throwable>();
			exceptions.add(_x);
		}
		else
		{
			exceptions.add(_x);
		}
	}
}