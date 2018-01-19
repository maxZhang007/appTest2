package com.max.ExchangeDemo;

//print all payments per minute
public class PrintThread extends Thread{
	
	//data map
	private PaymentService paymentService;	
	
	public PrintThread(PaymentService paymentService){
        this.paymentService = paymentService;
    }
	
	public void run() {
		//print per minute
		while(true){
			this.paymentService.printPayment();
			try {
				this.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
		}
		
		System.out.println("printThread end!");
	}
}
