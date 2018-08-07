package com.mm.dto;

import java.util.List;
import java.util.Map;


public class pdfReaderDTO {
	
	public List<String> verifyPDFcontent;
	
		public pdfReaderDTO(Map<String, List<String>> excelData)
		{		
			
			for(int i = 0; i<= pdfReaderDTO.class.getFields().length-1; i++)
			{
				try {
					//System.out.println(pdfReaderDTO.class.getFields()[i].getName().toLowerCase());
					pdfReaderDTO.class.getFields()[i].set(this, excelData.get(pdfReaderDTO.class.getFields()[i].getName().toLowerCase()));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				
			}
		/*for (int iFC = 0; iFC < pdfReaderDTO.class.getFields().length; iFC++) {
			if (SmokeTestCasesUpdated.testDataMap.containsKey(pdfReaderDTO.class.getFields()[iFC].getName().toLowerCase()))
			{
			if (pdfReaderDTO.class.getFields()[iFC].getType().toString().toLowerCase().contains("java.util.list")) {
				try {
					pdfReaderDTO.class.getFields()[iFC].set(this,
							SmokeTestCasesUpdated.testDataMap.get(pdfReaderDTO.class.getFields()[iFC].getName().toLowerCase()));

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(SmokeTestCasesUpdated.testDataMap.get(pdfReaderDTO.class.getFields()[iFC].getName()));
				}

			} else {
				try {
					pdfReaderDTO.class.getFields()[iFC].set(this, SmokeTestCasesUpdated.testDataMap
							.get(pdfReaderDTO.class.getFields()[iFC].getName().toLowerCase()).get(0));

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(SmokeTestCasesUpdated.testDataMap.get(pdfReaderDTO.class.getFields()[iFC].getName()));
				}
			}
		}
	}*/
	}	
}
