package com.loan.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.loan.entity.Loan;

public interface ILoanService {
	
	List<Loan> getAllLoans();
    Loan addLoan(Loan loan);
    Optional<Loan> getLoanById(String loanId);
    List<Loan> getLoanByCustomerId(String customerId);
    List<Loan> getLoansByLenderId(String lenderId);
	Map<String, Double> getAggregateLoansByLender();
	Map<String, Double> getAggregateLoansByCustomer();
	Map<Double, Double> getAggregateLoansByInterest();
   
}
