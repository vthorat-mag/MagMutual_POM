package com.mm.dto;

import MMTestCase.SmokeTestCase;

public class CISPageDTO {
	
	public String LongName;
	public String Address_Line1;
	public String City;
	public String Phone_no;
	public String Area_code; 
	public String Class_Eff_To_Date;
	public String zipCode;
	public String Classification;
	public String Addr_Type;
	public String State;
	
	
	public CISPageDTO() throws IllegalArgumentException, IllegalAccessException, SecurityException{
		
		for (int iFC=0; iFC < CISPageDTO.class.getFields().length; iFC++)
		{
			CISPageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap.get(CISPageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));
			
		}
		
	}

}
