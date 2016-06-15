package com.rate.retrieval.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "Rate")
public class Rate {
	String file; //- *file* - which stores the filename from which this rate has been extracted,
	double buyCurrency; //- *buyCurrency* - expressing the currency from which the rate will convert,
	double sellCurrency; //- *sellCurrency* - expressing the currency to which the rate will convert,
	Date timestamp; //- *validDate* - containing the date when this rate could be used, and
	double rate; //- *rate* - the foreign exchange rate itself.
	
	
	public String getFile() {
		return file;
	}


	public void setFile(String file) {
		this.file = file;
	}


	public double getBuyCurrency() {
		return buyCurrency;
	}


	public void setBuyCurrency(double buyCurrency) {
		this.buyCurrency = buyCurrency;
	}


	public double getSellCurrency() {
		return sellCurrency;
	}


	public void setSellCurrency(double sellCurrency) {
		this.sellCurrency = sellCurrency;
	}


	public Date getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


	public double getRate() {
		return rate;
	}


	public void setRate(double rate) {
		this.rate = rate;
	}


	public String getMsg() {
		// TODO Auto-generated method stub
		return "Hello currency exchange rate service...";
	}
}
