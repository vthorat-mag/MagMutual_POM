package com.mm.dto;

import java.util.List;
import java.util.Map;

import MMTestCase.SmokeTestCasesUpdated;

public class RateAPolicyPageDTO {

	public List<String> coverageNames;
	public List<String> binderForms;
	public List<String> phase;
	public List<String> coverage;
	public String valueOfPolicyActionAccept;
	public String valueOfPolicyActionBillingSetup;
	public String paymentPlanValue;
	public String productNotifyValue;
	public String footerContent;
	public String optionValue;
	public String optionName;
	public String saveAsPolicyValue;
	public String listDDLValue;
	public String coverageFromCoverageTabGrid;
	public String manuscriptForm;
	public String viewModeOfficial;
	public String viewModeWIP;
	public String policyAction;
	public String endorsementReason;
	public String policyNum;
	public String policyNo;
	public String endorsementComment;

	public RateAPolicyPageDTO(Map<String, List<String>> excelData) {

		for (int i = 0; i <= RateAPolicyPageDTO.class.getFields().length - 1; i++) {
			try {
				// System.out.println(RateAPolicyPageDTO.class.getFields()[i].getName().toLowerCase());
				RateAPolicyPageDTO.class.getFields()[i].set(this,
						excelData.get(RateAPolicyPageDTO.class.getFields()[i].getName().toLowerCase()));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}

		}
	}
}