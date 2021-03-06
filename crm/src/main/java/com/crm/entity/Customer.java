package com.crm.entity;

import java.util.List;

public class Customer {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column customer.cus_id
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	private Integer cusId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column customer.cus_status
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	private Integer cusStatus;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column customer.cus_grade
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	private Integer cusGrade;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column customer.cus_name
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	private String cusName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column customer.u_id
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	private Integer uId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column customer.cus_area
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	private String cusArea;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column customer.cus_satisfaction
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	private Integer cusSatisfaction;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column customer.cus_credit
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	private Integer cusCredit;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column customer.cus_id
	 * @return  the value of customer.cus_id
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	public Integer getCusId() {
		return cusId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column customer.cus_id
	 * @param cusId  the value for customer.cus_id
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column customer.cus_status
	 * @return  the value of customer.cus_status
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	public Integer getCusStatus() {
		return cusStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column customer.cus_status
	 * @param cusStatus  the value for customer.cus_status
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	public void setCusStatus(Integer cusStatus) {
		this.cusStatus = cusStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column customer.cus_grade
	 * @return  the value of customer.cus_grade
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	public Integer getCusGrade() {
		return cusGrade;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column customer.cus_grade
	 * @param cusGrade  the value for customer.cus_grade
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	public void setCusGrade(Integer cusGrade) {
		this.cusGrade = cusGrade;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column customer.cus_name
	 * @return  the value of customer.cus_name
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	public String getCusName() {
		return cusName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column customer.cus_name
	 * @param cusName  the value for customer.cus_name
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column customer.u_id
	 * @return  the value of customer.u_id
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	public Integer getuId() {
		return uId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column customer.u_id
	 * @param uId  the value for customer.u_id
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	public void setuId(Integer uId) {
		this.uId = uId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column customer.cus_area
	 * @return  the value of customer.cus_area
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	public String getCusArea() {
		return cusArea;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column customer.cus_area
	 * @param cusArea  the value for customer.cus_area
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	public void setCusArea(String cusArea) {
		this.cusArea = cusArea;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column customer.cus_satisfaction
	 * @return  the value of customer.cus_satisfaction
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	public Integer getCusSatisfaction() {
		return cusSatisfaction;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column customer.cus_satisfaction
	 * @param cusSatisfaction  the value for customer.cus_satisfaction
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	public void setCusSatisfaction(Integer cusSatisfaction) {
		this.cusSatisfaction = cusSatisfaction;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column customer.cus_credit
	 * @return  the value of customer.cus_credit
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	public Integer getCusCredit() {
		return cusCredit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column customer.cus_credit
	 * @param cusCredit  the value for customer.cus_credit
	 * @mbg.generated  Mon May 04 13:49:33 CST 2020
	 */
	public void setCusCredit(Integer cusCredit) {
		this.cusCredit = cusCredit;
	}

	/**
	 * 客户联系人列�?
	 */
	private List<CustomerContact> contacts;
	
	/**
	 * 
	 * @return	客户联系人信�?
	 */
	public List<CustomerContact> getContacts() {
		return contacts;
	}

	public void setContacts(List<CustomerContact> contacts) {
		this.contacts = contacts;
	}
}