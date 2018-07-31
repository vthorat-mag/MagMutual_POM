package com.mm.dto;

import java.util.List;

import MMTestCase.SmokeTestCase;

public class CincomPageDTO {
	
	public List<String> coverage;
	public List<String> coverageList;
	public List<String> phase;

	
	public CincomPageDTO() throws IllegalArgumentException, IllegalAccessException, SecurityException{
		
		for (int iFC = 0; iFC < CincomPageDTO.class.getFields().length; iFC++) {
			
			if (CincomPageDTO.class.getFields()[iFC].getType().toString().toLowerCase().contains("java.util.list")) {
				try {
					CincomPageDTO.class.getFields()[iFC].set(this,
							SmokeTestCase.testDataMap.get(CincomPageDTO.class.getFields()[iFC].getName().toLowerCase()));

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				try {
					CincomPageDTO.class.getFields()[iFC].set(this, SmokeTestCase.testDataMap
							.get(CincomPageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
