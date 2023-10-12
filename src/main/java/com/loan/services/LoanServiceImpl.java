package com.loan.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan.dao.ILoanRepository;
import com.loan.entity.Loan;
import com.loan.exception.LoanRecordNotFoundException;

@Service
public class LoanServiceImpl implements ILoanService{

	@Autowired
	private ILoanRepository loanRepo;
	
	public List<Loan> getAllLoans() {
		// TODO Auto-generated method stub
		List<Loan> loans=loanRepo.findAll();
		return loanRepo.findAll();
	}

 

	
	public Loan addLoan(Loan loan) {
		// TODO Auto-generated method stub
		List<Loan> loans=loanRepo.findAll();
		if (loan.getPaymentDate().after(loan.getDueDate())) {
            throw new IllegalArgumentException("Payment date cannot be greater than due date");
        }

 

        // Check if the loan is overdue
        if (loan.getDueDate().before(Date.valueOf(LocalDate.now()))) {
        		//.isBefore(LocalDate.now())) {
            System.out.println("Alert: Loan with ID " + loan.getLoanId() + " is overdue!");
        }

        loans.add(loan);
        loanRepo.save(loan);
        return loan;
	}

 

	
	public Optional<Loan> getLoanById(String loanId) {
		// TODO Auto-generated method stub
//		List<Loan> loans=loanRepo.findAll();
//		for (Loan loan : loans) {
//            if (loan.getLoanId().equals(loanId)) {
//            	
//                return loanRepo.findById(loanId);
//            }
//        }

		Optional<Loan> loan=loanRepo.findById(loanId);
		if(loan.isEmpty())
		{
			throw new LoanRecordNotFoundException("Loan Record Not found with the loanId : "+loanId);
		}
		else
        return loanRepo.findById(loanId);
	}

 

	
	public List<Loan> getLoanByCustomerId(String customerId) {
		return loanRepo.findByCustomerId(customerId);

	}

 

	
	public List<Loan> getLoansByLenderId(String lenderId) {
		// TODO Auto-generated method stub
		/*List<Loan> loans=loanRepo.findAll();
		List<Loan> lenderLoans = (List<Loan>) new ArrayList<Loan>();
        for (Loan loan : loans) {
            if (loan.getLenderId().equals(lenderId)) {
                lenderLoans.add(loan);
            }
        }*/
        List<Loan> loans = loanRepo.findByLenderId(lenderId);
        if(loans.isEmpty())
        {
        	throw new LoanRecordNotFoundException("Loan Records not Found");
        }
        else 
        	return loans;
	}

 

	
	public Map<String, Double> getAggregateLoansByLender() {
		List<Loan> loans=loanRepo.findAll();
        Map<String, Double> lenderAggregation =new HashMap<>();

 

        for (Loan loan : loans) {
            String lenderId = loan.getLenderId();
            double remainingAmount = loan.getRemainingAmount();

 

            // If the lender already exists in the map, update the remaining amount
            if (lenderAggregation.containsKey(lenderId)) {
                remainingAmount += lenderAggregation.get(lenderId);
            }

 

            lenderAggregation.put(lenderId, remainingAmount);
        }

 

        return lenderAggregation;
    }

 

    public Map<String, Double> getAggregateLoansByCustomer() {
    	List<Loan> loans=loanRepo.findAll();
        Map<String, Double> customerAggregation =new HashMap<>();

 

        for (Loan loan : loans) {
            String customerId = loan.getCustomerId();
            double remainingAmount = loan.getRemainingAmount();

 

            // If the customer already exists in the map, update the remaining amount
            if (customerAggregation.containsKey(customerId)) {
                remainingAmount += customerAggregation.get(customerId);
            }

 

            customerAggregation.put(customerId, remainingAmount);
        }

 

        return customerAggregation;
    }

 

    @Override
    public Map<Double, Double> getAggregateLoansByInterest() {
    	List<Loan> loans=loanRepo.findAll();
        Map<Double, Double> interestAggregation = new HashMap<>();

 

        for (Loan loan : loans) {
            double interestRate = loan.getInterestPerDay();
            double remainingAmount = loan.getRemainingAmount();

 

            // If the interest rate already exists in the map, update the remaining amount
            if (interestAggregation.containsKey(interestRate)) {
                remainingAmount += interestAggregation.get(interestRate);
            }

 

            interestAggregation.put(interestRate, remainingAmount);
        }

 

        return interestAggregation;
    }
	}


