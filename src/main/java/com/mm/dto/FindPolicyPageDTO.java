package com.mm.dto;

import java.util.List;
import java.util.Map;

import MMTestCase.SmokeTestCasesUpdated;

public class FindPolicyPageDTO {

	public static String phase;
	public static String status;

	public FindPolicyPageDTO(Map<String, List<String>> excelData) {

		for (int i = 0; i <= FindPolicyPageDTO.class.getFields().length - 1; i++) {
			if (FindPolicyPageDTO.class.getFields()[i].getType().toString().toLowerCase().contains("java.util.list")) {

				// if
				// (excelData.containsKey(FindPolicyPageDTO.class.getFields()[i].getName().toLowerCase())){
				// {
				try {
					FindPolicyPageDTO.class.getFields()[i].set(this,
							excelData.get(FindPolicyPageDTO.class.getFields()[i].getName().toLowerCase()));

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (FindPolicyPageDTO.class.getFields()[i].getType().toString().toLowerCase().contains("int")) {
				try {
					FindPolicyPageDTO.class.getFields()[i].set(this, Integer.parseInt(
							excelData.get(FindPolicyPageDTO.class.getFields()[i].getName().toLowerCase()).get(0)));

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					FindPolicyPageDTO.class.getFields()[i].set(this,
							excelData.get(FindPolicyPageDTO.class.getFields()[i].getName().toLowerCase()).get(0));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
