package com.mm.dto;

import java.util.List;

import MMTestCase.SmokeTestCase;

public class ClaimsDTO {

	public static String addFilePageTitle;
	public static String entitySearchListPageTitle;
	public static String lastName;
	public static String firstName;
	public static String fileTypeDropDownValue;
	public static String lobDropDownValue;
	public static String fileHandlerDropDownValue;
	public static String stateOfLossDropDownValue;
	public static String searchEntityPageTitle;
	public static String description;
	public String FileSearchPageTitle;
	public String claimNum;
	public String clientIDValue;
	public String clientNameValue;
	public String seperateCheck;
	public List <String> transactionType;
	public List <String> paymentType;
	public List <String> vendorIDValue;
	public List <String> taxIDType;
	public List <String> transactionAmount;
	public List <String> invoiceNo;
	public List <String> payeeName;
	public String  policyNum;
	public String CoverageDescription;

	public ClaimsDTO() throws Exception{

		for (int iFC=0; iFC < ClaimsDTO.class.getFields().length; iFC++) 
		{
			if (SmokeTestCase.testDataMap.containsKey(ClaimsDTO.class.getFields()[iFC].getName().toLowerCase()))
			{
				if(ClaimsDTO.class.getFields()[iFC].getType().toString().toLowerCase().contains("java.util.list"))
				{
					try{
						ClaimsDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap.get(ClaimsDTO.class.getFields()[iFC].getName().toLowerCase()));

					}catch(Exception e){
						e.printStackTrace();
					}

				}else				
				{
					try{
						ClaimsDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap.get(ClaimsDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));

					}catch(Exception e){
						e.printStackTrace();
					}

				}
			}
		}
	}
}
