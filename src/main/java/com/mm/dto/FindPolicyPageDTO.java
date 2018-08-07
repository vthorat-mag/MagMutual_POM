package com.mm.dto;

import java.util.List;
import java.util.Map;

import MMTestCase.SmokeTestCasesUpdated;

public class FindPolicyPageDTO {

	public static String phase;
	public static String status;

	public FindPolicyPageDTO(Map<String, List<String>> excelData) {

		for (int i = 0; i <= FindPolicyPageDTO.class.getFields().length - 1; i++) {
			try {
				// System.out.println(FindPolicyPageDTO.class.getFields()[i].getName().toLowerCase());
				FindPolicyPageDTO.class.getFields()[i].set(this,
						excelData.get(FindPolicyPageDTO.class.getFields()[i].getName().toLowerCase()));
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
