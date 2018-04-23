package com.tomcarroll.finalProject.dao;

import java.io.Serializable;

public class Observation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String loan_sequence_number;
	private Integer reporting_period;
	private Double current_upb;
	private String dq_status;
	private Integer loan_age;
	private Integer rmm;
	private String repurchase_flag;
	private String modification_flag;
	private String zero_balance_code;
	private Integer zero_balance_effective_date;
	private Double current_interest_rate;
	
	
	public Observation(String loan_sequence_number, Integer reporting_period, Double current_upb,
			String dq_status, Integer loan_age, Integer rmm, String repurchase_flag, String modification_flag,
			String zero_balance_code, Integer zero_balance_effective_date, Double current_interest_rate) {
		super();
		this.loan_sequence_number = loan_sequence_number;
		this.reporting_period = reporting_period;
		this.current_upb = current_upb;
		this.dq_status = dq_status;
		this.loan_age = loan_age;
		this.rmm = rmm;
		this.repurchase_flag = repurchase_flag;
		this.modification_flag = modification_flag;
		this.zero_balance_code = zero_balance_code;
		this.zero_balance_effective_date = zero_balance_effective_date;
		this.current_interest_rate = current_interest_rate;
	}
	
	
	public Observation() {}
	public String getLoan_sequence_number() {
		return loan_sequence_number;
	}
	public void setLoan_sequence_number(String loan_sequence_number) {
		this.loan_sequence_number = loan_sequence_number;
	}
	public Integer getReporting_period() {
		return reporting_period;
	}
	public void setReporting_period(Integer reporting_period) {
		this.reporting_period = reporting_period;
	}
	public Double getCurrent_upb() {
		return current_upb;
	}
	public void setCurrent_upb(Double current_upb) {
		this.current_upb = current_upb;
	}
	public String getDq_status() {
		return dq_status;
	}
	public void setDq_status(String dq_status) {
		this.dq_status = dq_status;
	}
	public Integer getLoan_age() {
		return loan_age;
	}
	public void setLoan_age(Integer loan_age) {
		this.loan_age = loan_age;
	}
	public Integer getRmm() {
		return rmm;
	}
	public void setRmm(Integer rmm) {
		this.rmm = rmm;
	}
	public String getRepurchase_flag() {
		return repurchase_flag;
	}
	public void setRepurchase_flag(String repurchase_flag) {
		this.repurchase_flag = repurchase_flag;
	}
	public String getModification_flag() {
		return modification_flag;
	}
	public void setModification_flag(String modification_flag) {
		this.modification_flag = modification_flag;
	}
	public String getZero_balance_code() {
		return zero_balance_code;
	}
	public void setZero_balance_code(String zero_balance_code) {
		this.zero_balance_code = zero_balance_code;
	}
	public Integer getZero_balance_effective_date() {
		return zero_balance_effective_date;
	}
	public void setZero_balance_effective_date(Integer zero_balance_effective_date) {
		this.zero_balance_effective_date = zero_balance_effective_date;
	}
	public Double getCurrent_interest_rate() {
		return current_interest_rate;
	}
	public void setCurrent_interest_rate(Double current_interest_rate) {
		this.current_interest_rate = current_interest_rate;
	}
	
	
	
	
	
	
	
}
