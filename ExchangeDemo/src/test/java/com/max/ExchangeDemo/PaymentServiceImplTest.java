package com.max.ExchangeDemo;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.max.ExchangeDemo.Payment;
import com.max.ExchangeDemo.PaymentService;
import com.max.ExchangeDemo.PaymentServiceImpl;

public class PaymentServiceImplTest {

	PaymentService paymentSerivce = new PaymentServiceImpl();
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testAddPaymentWithoutRate() {
		//input
		String strIn = "USD 100";
		Payment payment = new Payment(strIn);
		paymentSerivce.addPayment(payment);
		
		//run method
		String currency = "USD";
		Payment outPayment = paymentSerivce.getPayment(currency);
		
		//assert
		assert(outPayment.getCurrency().equals("USD"));
		assert(outPayment.getAmount().equals(new BigDecimal(100)));
		assert(outPayment.getRate()==null);
	}

	@Test
	public void testAddPaymentWithRate() {
		//input
		String strIn = "USD 100 1";
		Payment payment = new Payment(strIn);
		paymentSerivce.addPayment(payment);
		
		//run method
		String currency = "USD";
		Payment outPayment = paymentSerivce.getPayment(currency);
		
		//assert
		assert(outPayment.getCurrency().equals("USD"));
		assert(outPayment.getAmount().equals(new BigDecimal(100)));
		assert(outPayment.getRate().equals(new BigDecimal(1)));
	}
	
	@Test
	public void inputCheckCorrect() {
		//input
		String strInput = "USD 100";
		boolean result = false;	
		//assert
		result = paymentSerivce.inputCheck(strInput);
		assert(result==true);
		
		//input
		strInput = "USD -999";
		result = false;
		//assert
		result = paymentSerivce.inputCheck(strInput);
		assert(result==true);
	}
	
	@Test
	public void inputCheckCorrectRate() {
		//input
		String strInput = "AAA 100 0.1";
		boolean result = false;
		//assert
		result = paymentSerivce.inputCheck(strInput);
		assert(result==true);
		
		//input
		strInput = "USD -999 12.12";
		result = false;
		//assert
		result = paymentSerivce.inputCheck(strInput);
		assert(result==true);
	}
	
	@Test
	public void inputCheckErrorFormat() {
		//input 
		String strInput = "AAA1000.1";
		boolean result = false;
		//assert
		result = paymentSerivce.inputCheck(strInput);
		assert(result==false);
		
		
		//input 
		strInput = "AAA";
		result = false;
		//assert
		result = paymentSerivce.inputCheck(strInput);
		assert(result==false);
		
		//input 
		strInput = "AAA 1g2c";
		result = false;
		//assert
		result = paymentSerivce.inputCheck(strInput);
		assert(result==false);
		
		//input 
		strInput = "AAA 2000.1 ab ";
		result = false;
		//assert
		result = paymentSerivce.inputCheck(strInput);
		assert(result==false);
		
		//input 
		strInput = "AAA 2000.1 -0.12 ";
		result = false;
		//assert
		result = paymentSerivce.inputCheck(strInput);
		assert(result==false);
	}
}
