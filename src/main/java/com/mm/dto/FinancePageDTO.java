package com.mm.dto;

import MMTestCase.SmokeTestCase;
<<<<<<< Upstream, based on branch 'VT_Feature_Sprint_5' of https://github.com/vthorat-mag/MagMutual_POM.git
=======

public class FinancePageDTO {
>>>>>>> 049402d 1. Save Excel AUtoIT script. 2. ExcelUtil update. 3.

public class FinancePageDTO {
	
	public String policyNo;
	public Object currunetBalance;
	public String accountNumber;
	public String currunetBalance;
	public String policyNo;
	public String Number;
	public String Amount;
	
public FinancePageDTO() throws IllegalArgumentException, IllegalAccessException, SecurityException{
		
		for (int iFC = 0; iFC < FinancePageDTO.class.getFields().length; iFC++) {
			
			if (FinancePageDTO.class.getFields()[iFC].getType().toString().toLowerCase().contains("java.util.list")) {
				try {
					FinancePageDTO.class.getFields()[iFC].set(this,
							SmokeTestCase.testDataMap.get(FinancePageDTO.class.getFields()[iFC].getName().toLowerCase()));

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				try {
					FinancePageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap
							.get(FinancePageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public FinancePageDTO() throws IllegalArgumentException, IllegalAccessException, SecurityException{
		
		for (int iFC = 0; iFC < FinancePageDTO.class.getFields().length; iFC++) {
			
			if (SmokeTestCase.testDataMap.containsKey(FinancePageDTO.class.getFields()[iFC].getName().toLowerCase()))
			{
			
			if (FinancePageDTO.class.getFields()[iFC].getType().toString().toLowerCase().contains("java.util.list")) {
				try {
					FinancePageDTO.class.getFields()[iFC].set(this,
							SmokeTestCase.testDataMap.get(FinancePageDTO.class.getFields()[iFC].getName().toLowerCase()));

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				try {
					FinancePageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap
							.get(FinancePageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
  }
}
