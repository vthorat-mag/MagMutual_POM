package com.mm.dto;

import MMTestCase.SmokeTestCase;

public class RateAPolicyPageDTO {
	
public RateAPolicyPageDTO() throws IllegalArgumentException, IllegalAccessException, SecurityException{
		
		for (int iFC=0; iFC < RateAPolicyPageDTO.class.getFields().length; iFC++)
		{
			try{
				RateAPolicyPageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap.get(RateAPolicyPageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));
			
			}catch(Exception e){
				 e.printStackTrace();
				 System.out.println(SmokeTestCase.testDataMap.get(RateAPolicyPageDTO.class.getFields()[iFC].getName()));
				}
		}
	}
}