package com.mm.dto;

import java.util.List;
import java.util.Map;

import MMTestCase.SmokeTestCasesUpdated;

public class PolicyBinderPageDTO {

	public String policyNumber;
	public String valueOfPolicyActionEndorse;
	public String saveAsPolicyValue;
	public String ProductNotifyValue;
	public String valueOfSelectReason;
	public String valueOfPolicyActionCopyToQuote;
	public String FileSearchPageTitle;
	public String addFilePageTitle;
	public String entitySelectListPageTitle;
	public String entitySearchListPageTitle;
	public String fileTypeDropDownValue;
	public String lobDropDownValue;
	public String fileHandlerDropDownValue;
	public String stateOfLossDropDownValue;
	public String searchEntityPageTitle;
	public String lastName;
	public String firstName;
	public String description;

	public PolicyBinderPageDTO(Map<String, List<String>> excelData) {

		for (int i = 0; i <= PolicyBinderPageDTO.class.getFields().length - 1; i++) {
			try {
				// System.out.println(PolicyBinderPageDTO.class.getFields()[i].getName().toLowerCase());
				PolicyBinderPageDTO.class.getFields()[i].set(this,
						excelData.get(PolicyBinderPageDTO.class.getFields()[i].getName().toLowerCase()));
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
