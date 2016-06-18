package com.rate.retrieval.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@Table(name="RATE")
@XmlRootElement(name = "Rate")
public class Rate {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	String file; //- *file* - which stores the filename from which this rate has been extracted,
	double buyCurrency; //- *buyCurrency* - expressing the currency from which the rate will convert,
	double sellCurrency; //- *sellCurrency* - expressing the currency to which the rate will convert,
	String timestamp; //- *validDate* - containing the date when this rate could be used, and
	double rate; //- *rate* - the foreign exchange rate itself.
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


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


	public String getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(String timestamp) {
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


	@Override
	public String toString() {
		return "Rate [id=" + id + ", file=" + file + ", buyCurrency=" + buyCurrency + ", sellCurrency=" + sellCurrency
				+ ", timestamp=" + timestamp + ", rate=" + rate + "]";
	}
	
	
}
