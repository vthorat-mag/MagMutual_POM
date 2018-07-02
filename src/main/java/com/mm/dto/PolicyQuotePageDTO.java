package com.mm.dto;

import MMTestCase.SmokeTestCase;

public class PolicyQuotePageDTO {
	
	public String policyactionvalue;
	public String saveAsPolicyDDLValue;
	public String secondSaveAsPolicyDDLValue;

	
public PolicyQuotePageDTO() throws IllegalArgumentException, IllegalAccessException, SecurityException{
		
		for (int iFC=0; iFC < HomePageDTO.class.getFields().length; iFC++)
		{
			try{
				PolicyQuotePageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap.get(PolicyQuotePageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));
			
			}catch(Exception e){
				 e.printStackTrace();
				 System.out.println(SmokeTestCase.testDataMap.get(PolicyQuotePageDTO.class.getFields()[iFC].getName()));
				}
		}
	}
}
