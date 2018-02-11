package com.max.ExchangeDemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class PaymentServiceImpl implements PaymentService {
	//test git
	private final Map<String, Payment> paymentMap = new LinkedHashMap<String, Payment>();
	
	//add one payment
	/* (non-Javadoc)
	 * @see com.max.CalculatorDemo.paymentService#addPayment(com.max.CalculatorDemo.Payment)
	 */
	@Override
	public synchronized boolean addPayment(Payment inPayment){
		
		if(inPayment==null){
			return true;
		}
		
		//get payment with currency from map
		Payment payment = (Payment) paymentMap.get(inPayment.getCurrency());
		
		if(payment!=null){
			//currency already in map,plus the amount
			payment.setAmount(payment.getAmount().add(inPayment.getAmount()));
			if(inPayment.getRate()!=null){
				payment.setRate(inPayment.getRate());
			}
		} else {
			//currency not in map, add it into map
			paymentMap.put(inPayment.getCurrency(),inPayment);
		}
		
		return true;
	}
	
	//print all payments
	/* (non-Javadoc)
	 * @see com.max.CalculatorDemo.paymentService#printPayment()
	 */
	@Override
	public synchronized void printPayment(){
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(date);
		Iterator iter = paymentMap.entrySet().iterator();
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n");
		buffer.append("--------------------------------------------------");
		buffer.append("\n");
		buffer.append("print start.");
		buffer.append("\n");
		buffer.append("It's time to print all payments: ");
		buffer.append(dateString);
		buffer.append("\n\n");
		//get all the payments
		while(iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String strCurrency = (String)entry.getKey();
			Payment payment = (Payment)entry.getValue();
			//do not print 0
			if(payment.getAmount().compareTo(new BigDecimal(0)) ==0 ){
				continue;
			}
			buffer.append(payment.toString());
			buffer.append("\n");
		}
		buffer.append("\n");
		buffer.append("print end.");
		buffer.append("\n");
		buffer.append("--------------------------------------------------");
		
		//print list
		System.out.println(buffer.toString());
	}
	
    //read payments file
    /* (non-Javadoc)
	 * @see com.max.CalculatorDemo.paymentService#readFile(java.lang.String)
	 */
    @Override
	public synchronized void readFile(String fileName){
    	try{
    		//total lines
    		long totalLine = 0;
    		//read lines
    		long readLine = 0;
	    	File filename = new File(fileName);
	    	
	    	//create input stream to read file
	    	InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
	        BufferedReader br = new BufferedReader(reader);
	        System.out.println("Read input file start!");
	        String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) { 
            	totalLine++;
	            if(this.inputCheck(lineTxt)){
	            	this.addPayment(new Payment(lineTxt));
	            	readLine++;
	            } else {
	            	System.out.println("Error happened when reading payment file, format is not correct when reading payment -    '" + lineTxt + "' ");
	            	continue;
	            }

	        }  
	        System.out.println("Read input file end! " + String.valueOf(readLine) + "/" + String.valueOf(totalLine) + " payments read.");
    	}catch(Exception e){
    		System.out.println("Error happened when reading payment file!");
    		System.out.println(e);
    	}
    }
    
	//check user input
	/* (non-Javadoc)
	 * @see com.max.CalculatorDemo.paymentService#inputCheck(java.lang.String)
	 */
	@Override
	public boolean inputCheck(String strInput){
		//split input via blank
		String[] inputList =  strInput.trim().split(" ");
		
		//check list length: 2 without rate, 3 with rate
		if(inputList.length != 2 && inputList.length != 3){
			System.out.println("input rejected! wrong format, Please following input format: currency amount (optional:rate) /n e.g: USD 100 1    or    e.g: CNY 200 0.1549");
			return false;
		}
		
		//currency check: only 3 upper case letters
		if( inputList[0].length() != 3 || !inputList[0].matches("[A-Z]+")){
			System.out.println("input rejected! invalide currecny:  " + inputList[0]);
			return false;
		}

		//amount check: only number and .
		if(!inputList[1].matches("^(-)?[0-9]+(.[0-9]+)?$")){
			System.out.println("input rejected! invalide amount:  " + inputList[1]);
			return false;
		}
		
		//rate check: only number and .  and must >0
		if( inputList.length == 3 && !inputList[2].matches("^[0-9]+(.[0-9]+)?$")){
			System.out.println("input rejected! invalide rate:  ! " + inputList[2]);
			return false;
		}
		
		return true;
	}
	
	//get Payment
	/* (non-Javadoc)
	 * @see com.max.CalculatorDemo.paymentService#getPayment(java.lang.String)
	 */
	@Override
	public Payment getPayment(String currency){
		Payment payment = null;
		payment = paymentMap.get(currency);
		return payment;
	}
}
