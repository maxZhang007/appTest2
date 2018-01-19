package com.max.ExchangeDemo;

public interface PaymentService {

	//add one payment
	/* (non-Javadoc)
	 * @see com.max.CalculatorDemo.paymentService#addPayment(com.max.CalculatorDemo.Payment)
	 */
	boolean addPayment(Payment inPayment);

	//print all payments
	/* (non-Javadoc)
	 * @see com.max.CalculatorDemo.paymentService#printPayment()
	 */
	void printPayment();

	//read payments file
	/* (non-Javadoc)
	 * @see com.max.CalculatorDemo.paymentService#readFile(java.lang.String)
	 */
	void readFile(String fileName);

	//check user input
	/* (non-Javadoc)
	 * @see com.max.CalculatorDemo.paymentService#inputCheck(java.lang.String)
	 */
	boolean inputCheck(String strInput);

	//get Payment
	/* (non-Javadoc)
	 * @see com.max.CalculatorDemo.paymentService#getPayment(java.lang.String)
	 */
	Payment getPayment(String currency);

}