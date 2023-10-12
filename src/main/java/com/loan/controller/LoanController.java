package com.loan.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loan.entity.Loan;
import com.loan.services.LoanServiceImpl;

@RestController
@RequestMapping("/loans")
public class LoanController {

	@Autowired
	private  LoanServiceImpl loanService;

   /* @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }*/

    @GetMapping("/")
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @PostMapping("/add")
    public Loan addLoan(@RequestBody Loan loan) {
        return loanService.addLoan(loan);
    }

    @GetMapping("/loanId/{loanId}")
    public Optional<Loan> getLoanById(@PathVariable String loanId) {
        return loanService.getLoanById(loanId);
    }

    @GetMapping("/customerId/{customerId}")
    public List<Loan> getLoanByCustomerId(@PathVariable String customerId) {
        return loanService.getLoanByCustomerId(customerId);
    }

    @GetMapping("/lenderId/{lenderId}")
    public List<Loan> getLoansByLenderId(@PathVariable String lenderId) {
        return loanService.getLoansByLenderId(lenderId);
    }

    @GetMapping("/aggregate/lender")
    public Map<String, Double> getAggregateAmountByLender() {
        return loanService.getAggregateLoansByLender();
    }

    @GetMapping("/aggregate/customer")
    public Map<String, Double> getAggregateAmountByCustomer() {
        return loanService.getAggregateLoansByCustomer();
    }

    @GetMapping("/aggregate/interest")
    public Map<Double, Double> getAggregateAmountByInterest() {
        return loanService.getAggregateLoansByInterest();
    }
}
