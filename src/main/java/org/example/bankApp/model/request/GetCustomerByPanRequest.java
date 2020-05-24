package org.example.bankApp.model.request;

import org.springframework.stereotype.Component;

@Component
public class GetCustomerByPanRequest {

	private String panNumber;

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	@Override
	public String toString() {
		return "GetCustomerByPanRequest{" +
				"panNumber='" + panNumber + '\'' +
				'}';
	}
}
