package com.customer.dto;

import java.util.ArrayList;
import java.util.List;

public class PolicyDTOList {

	private List<PolicyDTO> policyList;
	
	public PolicyDTOList() {
		policyList = new ArrayList<>();
	}

	public List<PolicyDTO> getPolicyList() {
		return policyList;
	}

	public void setPolicyList(List<PolicyDTO> policyList) {
		this.policyList = policyList;
	}
	
}
