package com.mm.dto;

import java.util.List;
import java.util.Map;

import MMTestCase.SmokeTestCasesUpdated;

public class PolicyQuotePageDTO {

	public String policyactionvalue;
	public String saveAsPolicyDDLValue;
	public String secondSaveAsPolicyDDLValue;
	public String valueOfPolicyActionCopy;
	public String quotePhaseValue;
	public String riskType;
	public String binderForm;
	public String saveAsPolicyValue;
	public String saveAsPolicyValueRenewal;
	public String saveAsPolicyValueOfficial;
	public String policyActionValue;
	public String productNotifyValue;
	public String quoteDescriptionText;
	public List <String> coverages;
	public List <String> phases;
	
	public PolicyQuotePageDTO(Map<String, List<String>> excelData)
	{		
		
		for(int i = 0; i<= PolicyQuotePageDTO.class.getFields().length-1; i++)
		{
			try {
				//System.out.println(PolicyQuotePageDTO.class.getFields()[i].getName().toLowerCase());
				PolicyQuotePageDTO.class.getFields()[i].set(this, excelData.get(PolicyQuotePageDTO.class.getFields()[i].getName().toLowerCase()));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			
		}

	/*public PolicyQuotePageDTO() throws IllegalArgumentException, IllegalAccessException, SecurityException{


		for (int iFC=0; iFC < PolicyQuotePageDTO.class.getFields().length; iFC++) 
		{
			if (SmokeTestCasesUpdated.testDataMap.containsKey(PolicyQuotePageDTO.class.getFields()[iFC].getName().toLowerCase()))
			{
				if(PolicyQuotePageDTO.class.getFields()[iFC].getType().toString().toLowerCase().contains("java.util.list"))
				{
					try{
						PolicyQuotePageDTO.class.getFields()[iFC].set(this, SmokeTestCasesUpdated.testDataMap.get(PolicyQuotePageDTO.class.getFields()[iFC].getName().toLowerCase()));

					}catch(Exception e){
						e.printStackTrace();
						System.out.println(SmokeTestCasesUpdated.testDataMap.get(PolicyQuotePageDTO.class.getFields()[iFC].getName()));
					}

				}else				
				{
					try{
						PolicyQuotePageDTO.class.getFields()[iFC].set(this, SmokeTestCasesUpdated.testDataMap.get(PolicyQuotePageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));

					}catch(Exception e){
						e.printStackTrace();
						System.out.println(SmokeTestCasesUpdated.testDataMap.get(PolicyQuotePageDTO.class.getFields()[iFC].getName()));
					}
				}
			}
		}*/
	}
}
