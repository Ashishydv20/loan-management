package com.loan.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.loan.entity.Loan;

@Repository
public interface ILoanRepository extends JpaRepository<Loan,String> {
	
//	public Loan getLoanById(String loanId);
//	public List<Loan> getLoanByCustomerId(String customerId);
//	public List<Loan> getLoansByLenderId(String lenderId);
	Optional<Loan> findById(String id);
	@Query("select loan from Loan loan where loan.customerId= :customerId ")
    List<Loan> findByCustomerId(String customerId);
	
	@Query("select loan from Loan loan where loan.lenderId= :lenderId")
	List<Loan> findByLenderId(String lenderId);
}
