package com.mm.dto;

import java.util.List;
import java.util.Map;

public class PolicySubmissionPageDTO {

	public String policyPhase;
	public String organisationType;
	public String discoveryPeriodRating;
	public String quoteDescription;
	public String valueOfPolicyActionCopy;
	public String indicationPhaseValue;

	public PolicySubmissionPageDTO(Map<String, List<String>> excelData) {

		for (int i = 0; i <= PolicySubmissionPageDTO.class.getFields().length - 1; i++) {
			try {
				// System.out.println(PolicySubmissionPageDTO.class.getFields()[i].getName().toLowerCase());
				PolicySubmissionPageDTO.class.getFields()[i].set(this,
						excelData.get(PolicySubmissionPageDTO.class.getFields()[i].getName().toLowerCase()));
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
