package com.mm.dto;

import java.util.List;
import java.util.Map;

import MMTestCase.SmokeTestCasesUpdated;

public class PolicyIndicationPageDTO {

	public List<String> underwriterName;
	public List<String> teamName;
	public List<String> teamMembername;
	public List<String> coverage;
	public List<String> retroDate;
	public List<String> premiumAmount;
	public List<String> retroDateValue;
	public List<String> coverageTitle;
	public List<String> coverageName;
	public List<String> form;
	public List<String> sharedGroupCoverage;
	public List<String> sharedGroupDescription;
	public String coverageLimit;
	public String policyAction;
	public String producer;
	public String riskCountry;
	public String riskSpeciality;
	public String coverageFromPopup;
	public String policyForms;
	public String coverageClass;
	public String exposureUnit;

	public PolicyIndicationPageDTO(Map<String, List<String>> excelData)
			throws IllegalArgumentException, IllegalAccessException, SecurityException {

		for (int i = 0; i <= PolicyIndicationPageDTO.class.getFields().length - 1; i++) {
			if (PolicyIndicationPageDTO.class.getFields()[i].getType().toString().toLowerCase().contains("java.util.list")) {

				// if
				// (excelData.containsKey(PolicyIndicationPageDTO.class.getFields()[i].getName().toLowerCase())){
				// {
				try {
					PolicyIndicationPageDTO.class.getFields()[i].set(this,
							excelData.get(PolicyIndicationPageDTO.class.getFields()[i].getName().toLowerCase()));

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (PolicyIndicationPageDTO.class.getFields()[i].getType().toString().toLowerCase().contains("int")) {
				try {
					PolicyIndicationPageDTO.class.getFields()[i].set(this, Integer.parseInt(
							excelData.get(PolicyIndicationPageDTO.class.getFields()[i].getName().toLowerCase()).get(0)));

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					PolicyIndicationPageDTO.class.getFields()[i].set(this,
							excelData.get(PolicyIndicationPageDTO.class.getFields()[i].getName().toLowerCase()).get(0));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
