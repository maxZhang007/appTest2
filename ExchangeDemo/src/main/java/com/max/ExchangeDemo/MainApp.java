package com.max.ExchangeDemo;

/**
 * Hello world!
 *
 */
public class MainApp 
{

	
    public static void main( String[] args )
    {
    	System.out.println( "---app start!---" );
       	   
    	try {      
    		//payment service for reading and printing
    		PaymentService paymentService = new PaymentServiceImpl();
    		
        	//check if start app with file or not
        	if(args.length==1){
        		//if start app with file name, read file
        		String fileName = args[0];
        		paymentService.readFile(fileName);
        	}
        	
        	//read service
        	ReadThread readThread = new ReadThread(paymentService);
        	//print service
        	PrintThread printThread = new PrintThread(paymentService);
	    	//start read service
    		readThread.start();
	    	//start print service
    		printThread.start();
	    	
            readThread.join();
            printThread.interrupt();
            printThread.join();
	    	
    	} catch (Exception e) {  
            System.out.println(e);  
        }  
    		//app end
	    	System.out.println( "---app end!---" );
    }
}
