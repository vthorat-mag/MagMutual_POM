package com.mm.dto;

import java.util.List;
import java.util.Map;

import MMTestCase.SmokeTestCasesUpdated;

public class HomePageDTO {

	public String lastOrgName;
	public String effectiveFromDate;
	public String issueCompany;
	public String issueState;
	public String policyNum;
	public String policyNo;

	public HomePageDTO(Map<String, List<String>> excelData) {

		for (int i = 0; i <= HomePageDTO.class.getFields().length - 1; i++) {
			try {
				// System.out.println(HomePageDTO.class.getFields()[i].getName().toLowerCase());
				HomePageDTO.class.getFields()[i].set(this,
						excelData.get(HomePageDTO.class.getFields()[i].getName().toLowerCase()));
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
