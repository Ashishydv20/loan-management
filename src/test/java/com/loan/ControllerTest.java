package com.loan;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.loan.controller.LoanController;
import com.loan.entity.Loan;
import com.loan.services.LoanServiceImpl;

import io.swagger.v3.oas.models.media.MediaType;

public class ControllerTest {
	 @Mock
	    private LoanServiceImpl loanService;

	    @InjectMocks
	    private LoanController loanController;

	    private MockMvc mockMvc;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(loanController).build();
	    }
	    
	    @Test
	    void testGetAllLoans() throws Exception {
	        List<Loan> loans = List.of(new Loan(), new Loan());
	        when(loanService.getAllLoans()).thenReturn(loans);

	        mockMvc.perform(get("/loans/"))
	                .andExpect(status().isOk())
	                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$").isArray())
	                .andExpect(jsonPath("$.length()").value(loans.size()));

	        verify(loanService, times(1)).getAllLoans();
	    }
	    
	    @Test
	    void testAddLoan() throws Exception {
	        Loan loan = new Loan();
	        loan.setLoanId("L1");

	        when(loanService.addLoan((Loan) any(Loan.class))).thenReturn(loan);

	        mockMvc.perform(post("/loans/add")
	                .content("{\"loanId\":\"L1\"}"))
	                .andExpect(status().isOk())
	                .andExpect((ResultMatcher) content().contentType(""))
	                .andExpect(jsonPath("$.loanId").value("L1"));

	        verify(loanService, times(1)).addLoan((Loan) any(Loan.class));
	    }
	    
	    @Test
	    void testGetLoanById() throws Exception {
	        Optional<Loan> loan = Optional.of(new Loan());
	        when(loanService.getLoanById("L1")).thenReturn(loan);

	        mockMvc.perform(get("/loans/loanId/L1"))
	                .andExpect(status().isOk())
	                .andExpect((ResultMatcher) content().contentType("L1"));
	                //		.contentType(Loan.class));

	        verify(loanService, times(1)).getLoanById("L1");
	    }
	    
	    @Test
	    void testGetLoanByCustomerId() throws Exception {
	        List<Loan> loans = List.of(new Loan(), new Loan());
	        when(loanService.getLoanByCustomerId("C1")).thenReturn(loans);

	        mockMvc.perform(get("/loans/customerId/C1"))
	                .andExpect(status().isOk())
	                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$").isArray())
	                .andExpect(jsonPath("$.length()").value(loans.size()));

	        verify(loanService, times(1)).getLoanByCustomerId("C1");
	    }
	    
	    @Test
	    void testGetLoansByLenderId() throws Exception {
	        List<Loan> loans = List.of(new Loan(), new Loan());
	        when(loanService.getLoansByLenderId("LEN1")).thenReturn(loans);

	        mockMvc.perform(get("/loans/lenderId/LEN1"))
	                .andExpect(status().isOk())
	                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$").isArray())
	                .andExpect(jsonPath("$.length()").value(loans.size()));

	        verify(loanService, times(1)).getLoansByLenderId("LEN1");
	    }
	    
	    @Test
	    void testGetAggregateAmountByLender() throws Exception {
	        when(loanService.getAggregateLoansByLender()).thenReturn(Map.of("LEN1", 5000.0, "LEN2", 7000.0));

	        mockMvc.perform(get("/loans/aggregate/lender"))
	                .andExpect(status().isOk())
	                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$.LEN1").value(5000.0))
	                .andExpect(jsonPath("$.LEN2").value(7000.0));

	        verify(loanService, times(1)).getAggregateLoansByLender();
	    }
	    
	    @Test
	    void testGetAggregateAmountByCustomer() throws Exception {
	        when(loanService.getAggregateLoansByCustomer()).thenReturn(Map.of("C1", 3000.0, "C2", 4000.0));

	        mockMvc.perform(get("/loans/aggregate/customer"))
	                .andExpect(status().isOk())
	                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$.C1").value(3000.0))
	                .andExpect(jsonPath("$.C2").value(4000.0));

	        verify(loanService, times(1)).getAggregateLoansByCustomer();
	    }
	    
	    @Test
	    void testGetAggregateAmountByInterest() throws Exception {
	        when(loanService.getAggregateLoansByInterest()).thenReturn(Map.of(1.0, 5000.0, 2.0, 7000.0));

	        mockMvc.perform(get("/loans/aggregate/interest"))
	                .andExpect(status().isOk())
	                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$.1.0").value(5000.0))
	                .andExpect(jsonPath("$.2.0").value(7000.0));

	        verify(loanService, times(1)).getAggregateLoansByInterest();
	    }

}
