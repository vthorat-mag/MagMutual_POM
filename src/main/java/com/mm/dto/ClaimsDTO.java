package com.mm.dto;

import java.util.List;
import java.util.Map;

import MMTestCase.SmokeTestCasesUpdated;

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
	
	public ClaimsDTO(Map<String, List<String>> excelData)
	{		
		
		for(int i = 0; i<= ClaimsDTO.class.getFields().length-1; i++)
		{
			try {
				//System.out.println(ClaimsDTO.class.getFields()[i].getName().toLowerCase());
				ClaimsDTO.class.getFields()[i].set(this, excelData.get(ClaimsDTO.class.getFields()[i].getName().toLowerCase()));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			
		}

	/*public ClaimsDTO() throws Exception{

		
		for (int iFC=0; iFC < ClaimsDTO.class.getFields().length; iFC++) 
		{
			if (SmokeTestCasesUpdated.testDataMap.containsKey(ClaimsDTO.class.getFields()[iFC].getName().toLowerCase()))
			{
				if(ClaimsDTO.class.getFields()[iFC].getType().toString().toLowerCase().contains("java.util.list"))
				{
					try{
						ClaimsDTO.class.getFields()[iFC].set(this, SmokeTestCasesUpdated.testDataMap.get(ClaimsDTO.class.getFields()[iFC].getName().toLowerCase()));

					}catch(Exception e){
						e.printStackTrace();
					}

				}else				
				{
					try{
						ClaimsDTO.class.getFields()[iFC].set(this, SmokeTestCasesUpdated.testDataMap.get(ClaimsDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));

					}catch(Exception e){
						e.printStackTrace();
					}

				}
			}
		}
	}*/
	}
}
