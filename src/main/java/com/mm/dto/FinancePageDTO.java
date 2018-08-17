package com.mm.dto;

import java.util.List;
import java.util.Map;

import MMTestCase.SmokeTestCasesUpdated;

public class FinancePageDTO {

	public String policyNum;
	public Object currentBalance;
	public String accountNumber;
	public String policyNo;
	public String AccountType;
	public String LastOrgName;
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
	public String billingFrequency;
	public String screenShotName;

	public FinancePageDTO(Map<String, List<String>> excelData) {

		for (int i = 0; i <= FinancePageDTO.class.getFields().length - 1; i++) {
			if (FinancePageDTO.class.getFields()[i].getType().toString().toLowerCase().contains("java.util.list")) {

				// if
				// (excelData.containsKey(FinancePageDTO.class.getFields()[i].getName().toLowerCase())){
				// {
				try {
					FinancePageDTO.class.getFields()[i].set(this,
							excelData.get(FinancePageDTO.class.getFields()[i].getName().toLowerCase()));

				} catch (Exception e) {
					e.printStackTrace();
				}


			} else if (FinancePageDTO.class.getFields()[i].getType().toString().toLowerCase().contains("int")) {
				try {
					FinancePageDTO.class.getFields()[i].set(this, Integer.parseInt(
							excelData.get(FinancePageDTO.class.getFields()[i].getName().toLowerCase()).get(0)));

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					FinancePageDTO.class.getFields()[i].set(this,
							excelData.get(FinancePageDTO.class.getFields()[i].getName().toLowerCase()).get(0));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
				
		}
	}
}
