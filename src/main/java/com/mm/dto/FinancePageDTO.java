package com.mm.dto;

import MMTestCase.SmokeTestCase;

public class FinancePageDTO {

	public Object currentBalance;
	public String accountNumber;
	public String policyNo;
	public String Number;
	public String Amount;
	public String onDemandInvoiceInstallmentExcel;
	public String onDemandInvoiceInstallementBeforeExcel;
	public String invoicesInstallmentDueDateExcel;
	
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
}
	
	
