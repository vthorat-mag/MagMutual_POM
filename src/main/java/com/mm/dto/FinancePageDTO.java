package com.mm.dto;

import java.util.List;

import MMTestCase.SmokeTestCase;

public class FinancePageDTO {

	public String screenShotName;
	public String billingFrequency;
	public String AccountType;
	public String LastOrgName;
	public String policyNum;
	public Object currentBalance;
	public String accountNumber;
	public String policyNo;
	public String Number;
	public String Amount;
	public String TCSheetNumber;
	public String exportedExcelSheetName;
	public String testDataColumnName;
	public String testDataColumnheader;
	public int rowNumber;
	public int dataRowNumber;
	public String dataSheetName;
	public String columnCellValue;
	public String coverage;
	public String policyAction;
	public String cancelComment;
	public String saveOption;
	public String CreditInstallmentBeforeFileName;
	public String CreditInstallmentAfterFileName;
	public String CancelledCoverageTransactionFileName;
	public String onDemandInvoiceInstallmentExcel;
	public String onDemandInvoiceInstallementBeforeExcel;
	public String invoicesInstallmentDueDateExcel;
	public String excelNameAddCoverageInstallment;
	public String excelNameOnDemandInvoiceInstallmentAfter;
	public String coverageNameFromGrid;
	public String retroDate;
	public String effectiveDate;
	public String endorsementReason;
	public String endorsementComment;
	public String accountHolderName;
	
	
	
public FinancePageDTO() throws IllegalArgumentException, IllegalAccessException, SecurityException{
		
		for (int iFC = 0; iFC < FinancePageDTO.class.getFields().length; iFC++) {
			

			if (FinancePageDTO.class.getFields()[iFC].getType().toString().toLowerCase().contains("java.util.list")) {
				
			//	if (SmokeTestCase.testDataMap.containsKey(FinancePageDTO.class.getFields()[iFC].getName().toLowerCase()))		{
				try {
					FinancePageDTO.class.getFields()[iFC].set(this,
							SmokeTestCase.testDataMap.get(FinancePageDTO.class.getFields()[iFC].getName().toLowerCase()));

				} catch (Exception e) {
					e.printStackTrace();
				}

			} 
			else if (FinancePageDTO.class.getFields()[iFC].getType().toString().toLowerCase().contains("int")) {
				try {
					FinancePageDTO.class.getFields()[iFC].set(this, Integer.parseInt(SmokeTestCase.testDataMap
							.get(FinancePageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0)));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
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
