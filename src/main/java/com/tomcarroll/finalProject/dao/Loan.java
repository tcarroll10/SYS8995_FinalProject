package com.tomcarroll.finalProject.dao;

import java.io.Serializable;

public class Loan implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer credit_score;
	private Integer first_payment_date;
	private String first_time_homebuyer_flag;
	private Integer maturity_date;
	private Integer msa;
	private Integer mip;
	private Integer number_of_units;
	private String occupancy_status;
	private Integer ocltv;
	private Integer dti;
	private Double original_upb;
	private Integer oltv;
	private Double original_interest_rate;
	private String channel;
	private String prepayment_penalty_flag;
	private String product_type;
	private String property_state;
	private String property_type;
	private String postal_code;
	private String loan_sequence_number;
	private String loan_purpose;
	private Integer original_loan_term;
	private Integer number_of_borrowers;
	private String seller_name;
	private String servicer_name;
	private String super_conforming_flag;
	private String pre_harp_loan_sequence_number;
	
	
	public Loan() {};
	
	public Loan(Integer credit_score, Integer first_payment_date, String first_time_homebuyer_flag,
			Integer maturity_date, Integer msa, Integer mip, Integer number_of_units, String occupancy_status,
			Integer olctv, Integer dti, Double original_upb, Integer oltv, Double original_interest_rate, String channel,
			String prepayment_penalty_flag, String product_type, String property_state, String property_type,
			String postal_code, String loan_sequence_number, String loan_purpose, Integer original_loan_term,
			Integer number_of_borrowers, String seller_name, String servicer_name, String super_conforming_flag,
			String pre_harp_loan_sequence_number) {
		super();
		this.credit_score = credit_score;
		this.first_payment_date = first_payment_date;
		this.first_time_homebuyer_flag = first_time_homebuyer_flag;
		this.maturity_date = maturity_date;
		this.msa = msa;
		this.mip = mip;
		this.number_of_units = number_of_units;
		this.occupancy_status = occupancy_status;
		this.ocltv = olctv;
		this.dti = dti;
		this.original_upb = original_upb;
		this.oltv = oltv;
		this.original_interest_rate = original_interest_rate;
		this.channel = channel;
		this.prepayment_penalty_flag = prepayment_penalty_flag;
		this.product_type = product_type;
		this.property_state = property_state;
		this.property_type = property_type;
		this.postal_code = postal_code;
		this.loan_sequence_number = loan_sequence_number;
		this.loan_purpose = loan_purpose;
		this.original_loan_term = original_loan_term;
		this.number_of_borrowers = number_of_borrowers;
		this.seller_name = seller_name;
		this.servicer_name = servicer_name;
		this.super_conforming_flag = super_conforming_flag;
		this.pre_harp_loan_sequence_number = pre_harp_loan_sequence_number;
	}
	public Integer getCredit_score() {
		return credit_score;
	}
	public void setCredit_score(Integer credit_score) {
		this.credit_score = credit_score;
	}
	public Integer getFirst_payment_date() {
		return first_payment_date;
	}
	public void setFirst_payment_date(Integer first_payment_date) {
		this.first_payment_date = first_payment_date;
	}
	public String getFirst_time_homebuyer_flag() {
		return first_time_homebuyer_flag;
	}
	public void setFirst_time_homebuyer_flag(String first_time_homebuyer_flag) {
		this.first_time_homebuyer_flag = first_time_homebuyer_flag;
	}
	public Integer getMaturity_date() {
		return maturity_date;
	}
	public void setMaturity_date(Integer maturity_date) {
		this.maturity_date = maturity_date;
	}
	public Integer getMsa() {
		return msa;
	}
	public void setMsa(Integer msa) {
		this.msa = msa;
	}
	public Integer getMip() {
		return mip;
	}
	public void setMip(Integer mip) {
		this.mip = mip;
	}
	public Integer getNumber_of_units() {
		return number_of_units;
	}
	public void setNumber_of_units(Integer number_of_units) {
		this.number_of_units = number_of_units;
	}
	public String getOccupancy_status() {
		return occupancy_status;
	}
	public void setOccupancy_status(String occupancy_status) {
		this.occupancy_status = occupancy_status;
	}
	public Integer getOcltv() {
		return ocltv;
	}
	public void setOcltv(Integer olctv) {
		this.ocltv = olctv;
	}
	public Integer getDti() {
		return dti;
	}
	public void setDti(Integer dti) {
		this.dti = dti;
	}
	public Double getOriginal_upb() {
		return original_upb;
	}
	public void setOriginal_upb(Double original_upb) {
		this.original_upb = original_upb;
	}
	public Integer getOltv() {
		return oltv;
	}
	public void setOltv(Integer oltv) {
		this.oltv = oltv;
	}
	public Double getOriginal_interest_rate() {
		return original_interest_rate;
	}
	public void setOriginal_interest_rate(Double original_interest_rate) {
		this.original_interest_rate = original_interest_rate;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getPrepayment_penalty_flag() {
		return prepayment_penalty_flag;
	}
	public void setPrepayment_penalty_flag(String prepayment_penalty_flag) {
		this.prepayment_penalty_flag = prepayment_penalty_flag;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public String getProperty_state() {
		return property_state;
	}
	public void setProperty_state(String property_state) {
		this.property_state = property_state;
	}
	public String getProperty_type() {
		return property_type;
	}
	public void setProperty_type(String property_type) {
		this.property_type = property_type;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public String getLoan_sequence_number() {
		return loan_sequence_number;
	}
	public void setLoan_sequence_number(String loan_sequence_number) {
		this.loan_sequence_number = loan_sequence_number;
	}
	public String getLoan_purpose() {
		return loan_purpose;
	}
	public void setLoan_purpose(String loan_purpose) {
		this.loan_purpose = loan_purpose;
	}
	public Integer getOriginal_loan_term() {
		return original_loan_term;
	}
	public void setOriginal_loan_term(Integer original_loan_term) {
		this.original_loan_term = original_loan_term;
	}
	public Integer getNumber_of_borrowers() {
		return number_of_borrowers;
	}
	public void setNumber_of_borrowers(Integer number_of_borrowers) {
		this.number_of_borrowers = number_of_borrowers;
	}
	public String getSeller_name() {
		return seller_name;
	}
	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}
	public String getServicer_name() {
		return servicer_name;
	}
	public void setServicer_name(String servicer_name) {
		this.servicer_name = servicer_name;
	}
	public String getSuper_conforming_flag() {
		return super_conforming_flag;
	}
	public void setSuper_conforming_flag(String super_conforming_flag) {
		this.super_conforming_flag = super_conforming_flag;
	}
	public String getPre_harp_loan_sequence_number() {
		return pre_harp_loan_sequence_number;
	}
	public void setPre_harp_loan_sequence_number(String pre_harp_loan_sequence_number) {
		this.pre_harp_loan_sequence_number = pre_harp_loan_sequence_number;
	}
	


	@Override
	public String toString() {
		return "Loan [credit_score=" + credit_score + ", first_payment_date=" + first_payment_date
				+ ", first_time_homebuyer_flag=" + first_time_homebuyer_flag + ", maturity_date=" + maturity_date
				+ ", msa=" + msa + ", mip=" + mip + ", number_of_units=" + number_of_units + ", occupancy_status="
				+ occupancy_status + ", olctv=" + ocltv + ", dti=" + dti + ", original_upb=" + original_upb + ", oltv="
				+ oltv + ", original_interest_rate=" + original_interest_rate + ", channel=" + channel
				+ ", prepayment_penalty_flag=" + prepayment_penalty_flag + ", product_type=" + product_type
				+ ", property_state=" + property_state + ", property_type=" + property_type + ", postal_code="
				+ postal_code + ", loan_sequence_number=" + loan_sequence_number + ", loan_purpose=" + loan_purpose
				+ ", original_loan_term=" + original_loan_term + ", number_of_borrowers=" + number_of_borrowers
				+ ", seller_name=" + seller_name + ", servicer_name=" + servicer_name + ", super_conforming_flag="
				+ super_conforming_flag + ", pre_harp_loan_sequence_number=" + pre_harp_loan_sequence_number + "]";
	}
	
	
	
	
	
	
		
		

}
