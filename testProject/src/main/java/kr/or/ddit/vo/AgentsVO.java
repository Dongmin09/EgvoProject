package kr.or.ddit.vo;

public class AgentsVO {
	
	// 카멜 표기법 변환
	private String agentCode;
	private String agentName;
	private String workingArea;
	private int commission;
	private String phoneNo;
	private String country;
	
	// 기본 생성자
	public AgentsVO() {}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getWorkingArea() {
		return workingArea;
	}

	public void setWorkingArea(String workingArea) {
		this.workingArea = workingArea;
	}

	public int getCommission() {
		return commission;
	}

	public void setCommission(int commission) {
		this.commission = commission;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "AgentsVO [agentCode=" + agentCode + ", agentName=" + agentName + ", workingArea=" + workingArea
				+ ", commission=" + commission + ", phoneNo=" + phoneNo + ", country=" + country + "]";
	}
	
	
}
