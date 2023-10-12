package com.loan.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="loan")
public class Loan {
	
	@Id
	private String loanId;
    private String customerId;
    private String lenderId;
    private double amount;
    private double remainingAmount;
    private Date paymentDate;
    private double interestPerDay;
    private Date dueDate;
    private double penaltyPerDay;
    private boolean isCancelled;
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getLenderId() {
		return lenderId;
	}
	public void setLenderId(String lenderId) {
		this.lenderId = lenderId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public double getInterestPerDay() {
		return interestPerDay;
	}
	public void setInterestPerDay(double interestPerDay) {
		this.interestPerDay = interestPerDay;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public double getPenaltyPerDay() {
		return penaltyPerDay;
	}
	public void setPenaltyPerDay(double penaltyPerDay) {
		this.penaltyPerDay = penaltyPerDay;
	}
	public boolean isCancelled() {
		return isCancelled;
	}
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	public Loan(String loanId, String customerId, String lenderId, double amount, double remainingAmount,
			Date paymentDate, double interestPerDay, Date dueDate, double penaltyPerDay, boolean isCancelled) {
		super();
		this.loanId = loanId;
		this.customerId = customerId;
		this.lenderId = lenderId;
		this.amount = amount;
		this.remainingAmount = remainingAmount;
		this.paymentDate = paymentDate;
		this.interestPerDay = interestPerDay;
		this.dueDate = dueDate;
		this.penaltyPerDay = penaltyPerDay;
		this.isCancelled = isCancelled;
	}
	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", customerId=" + customerId + ", lenderId=" + lenderId + ", amount=" + amount
				+ ", remainingAmount=" + remainingAmount + ", paymentDate=" + paymentDate + ", interestPerDay="
				+ interestPerDay + ", dueDate=" + dueDate + ", penaltyPerDay=" + penaltyPerDay + ", isCancelled="
				+ isCancelled + "]";
	}
	public Loan() {
		super();
	}
	public void setPaymentDate(LocalDate localDate) {
		// TODO Auto-generated method stub
		
	}
	public void setDueDate(LocalDate localDate) {
		// TODO Auto-generated method stub
		
	}
}
