package com.mm.dto;

import MMTestCase.SmokeTestCase;

public class FinancPageDTO {

	public String policyNo;
	public Object currunetBalance;
	public String accountNumber;
	public String Number;
	public String Amount;
	
public FinancPageDTO() throws IllegalArgumentException, IllegalAccessException, SecurityException{
		
		for (int iFC = 0; iFC < FinancPageDTO.class.getFields().length; iFC++) {
			
			if (FinancPageDTO.class.getFields()[iFC].getType().toString().toLowerCase().contains("java.util.list")) {
				try {
					FinancPageDTO.class.getFields()[iFC].set(this,
							SmokeTestCase.testDataMap.get(FinancPageDTO.class.getFields()[iFC].getName().toLowerCase()));

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				try {
					FinancPageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap
							.get(FinancPageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
	
	
