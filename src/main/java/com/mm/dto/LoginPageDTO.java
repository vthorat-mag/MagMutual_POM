package com.mm.dto;

import java.util.List;
import java.util.Map;

import MMTestCase.SmokeTestCasesUpdated;

public class LoginPageDTO {
	public String username;
	public String password;

	public LoginPageDTO(Map<String, List<String>> excelData)
	{		
		
		for(int i = 0; i<= LoginPageDTO.class.getFields().length-1; i++)
		{
			try {
				//System.out.println(LoginPageDTO.class.getFields()[i].getName().toLowerCase());
				LoginPageDTO.class.getFields()[i].set(this, excelData.get(LoginPageDTO.class.getFields()[i].getName().toLowerCase()).get(0));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			
		}
		
	/*public LoginPageDTO() throws IllegalArgumentException, IllegalAccessException, SecurityException{
		
		for (int iFC=0; iFC < LoginPageDTO.class.getFields().length; iFC++)
		{
			
			LoginPageDTO.class.getFields()[iFC].set(this, SmokeTestCasesUpdated.testDataMap.get(LoginPageDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));
			
		}
	}*/
	}
}
