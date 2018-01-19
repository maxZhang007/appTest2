package com.max.ExchangeDemo;

import java.math.BigDecimal;

public class Payment {
	
	//currency 
	private String currency;
	
	//amount
	private BigDecimal amount;

	//rate
	private BigDecimal rate;
		
	//constructor
	public Payment(String strInput){
		String[] inputList =  strInput.trim().split(" ");
		this.currency = inputList[0];
		this.amount = new BigDecimal(inputList[1]);
		if(inputList.length == 3){
			this.rate = new BigDecimal(inputList[2]);
		}
	}
	
	//constructor
	public Payment(){

	}
	
	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public BigDecimal getUsdAmount() {
		BigDecimal usdAmount = new BigDecimal(0);
		usdAmount = this.getAmount().multiply(this.getRate());
		return usdAmount;
	}
	
	public String toString(){
		
		String strOut = "";
		
		if(this.rate !=null){
			//return string with USD amount
			strOut = this.currency + " " + this.amount.toString() + " (USD " + this.getUsdAmount().toString() + ")";
		}else{
			//return string without USD amount
			strOut = this.currency + " " + this.amount.toString();
		}
		
		return strOut;
	}
}
