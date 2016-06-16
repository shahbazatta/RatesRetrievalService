package com.rate.retrieval.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.rate.retrieval.dao.RateDAO;
import com.rate.retrieval.model.Rate;

@Service
@Path("/rateservice/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RateService {

	Map<String, Rate> rates = new HashMap<String, Rate>();
	private RateDAO rateDAO;

	
	public RateDAO getRateDAO() {
		return rateDAO;
	}

	public void setRateDAO(RateDAO rateDAO) {
		this.rateDAO = rateDAO;
	}

	public void init() {

		Rate newRate1 = new Rate();
		newRate1.setId(1);
		newRate1.setBuyCurrency(1.1);
		newRate1.setFile("hello1");
		newRate1.setRate(1.11);
		newRate1.setSellCurrency(1.111);
		newRate1.setTimestamp(new Date("11/11/2016"));

		Rate newRate2 = new Rate();
		newRate2.setId(2);
		newRate2.setBuyCurrency(2.1);
		newRate2.setFile("hello2");
		newRate2.setRate(2.11);
		newRate2.setSellCurrency(2.111);
		newRate2.setTimestamp(new Date("12/12/2016"));
		
		rates.put("1", newRate1);
		rates.put("2", newRate2);

	}

	public RateService() {
		init();
	}
	@POST
	@Path("/rates/{id}/")
	public Rate getAccount(@PathParam("id") String id) {
		Rate c = rates.get(id);
		return c;
	}

	@POST
	@Path("/rates/getall")
	public List getAllAccounts(Rate rate) {
		List accountList = new ArrayList();
		for (int i = 0; i <= rates.size(); i++) {
			accountList.add((Rate) rates.get(i));
		}
		return accountList;
	}

}
