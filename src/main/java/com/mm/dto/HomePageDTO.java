package com.mm.dto;

import java.util.List;

import MMTestCase.SmokeTestCase;

public class HomePageDTO {

	public String lastOrgName;
	public String effectiveFromDate;
	public String issueCompany;
	public String issueState;
	public String policyNo;


	public HomePageDTO() throws IllegalArgumentException, IllegalAccessException, SecurityException{

		for (int iFC=0; iFC < HomePageDTO.class.getFields().length; iFC++)
		{
			if (SmokeTestCase.testDataMap.containsKey(HomePageDTO.class.getFields()[iFC].getName().toLowerCase()))
			{
				try{
					HomePageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap.get(HomePageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));

				}catch(Exception e){
					e.printStackTrace();
					//	 System.out.println(SmokeTestCase.testDataMap.get(HomePageDTO.class.getFields()[iFC].getName()));
				}
			}
		}
	}
}
