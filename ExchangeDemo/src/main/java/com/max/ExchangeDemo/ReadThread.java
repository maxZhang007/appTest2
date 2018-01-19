package com.max.ExchangeDemo;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

//get user input from command line
public class ReadThread extends Thread{
	
	private PaymentService paymentService;
	
	public ReadThread(com.max.ExchangeDemo.PaymentService paymentService){
        this.paymentService = paymentService;
    }
	
	//run service
	public void run() {
		// TODO Auto-generated method stub
		Scanner inScanner = new Scanner(System.in);
        String strInput = null;
                
        do{
            strInput = inScanner.nextLine();
            if(strInput==null){
            	continue;
            }
            if(strInput.equals("quit")){
            	break;
            }
            //input check
            if(paymentService.inputCheck(strInput)){
            	System.out.println("input received: " + strInput);
            	
            	try {
            		Payment payment = new Payment(strInput);
            		//put input payment into cache
            		paymentService.addPayment(payment);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
            }
            
        }while(
        		//if user input "quit", quit application
        		!strInput.equals("quit")
        );
        System.out.println("readThread end!");
        inScanner.close();
	}
}
