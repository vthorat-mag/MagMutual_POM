package com.mm.dto;

import java.util.List;
import java.util.Map;

import MMTestCase.SmokeTestCasesUpdated;

public class CincomPageDTO {
	
	public List<String> coverage;
	public List<String> coverageList;
	public List<String> phase;
	public CincomPageDTO(Map<String, List<String>> excelData)
	{		
		
		for(int i = 0; i<= CincomPageDTO.class.getFields().length-1; i++)
		{
			try {
				//System.out.println(CincomPageDTO.class.getFields()[i].getName().toLowerCase());
				CincomPageDTO.class.getFields()[i].set(this, excelData.get(CincomPageDTO.class.getFields()[i].getName().toLowerCase()));
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
