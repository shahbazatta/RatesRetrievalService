package com.rate.retrieval.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.rate.retrieval.dao.RateDAO;
import com.rate.retrieval.model.Rate;

@Service
@Path("/")
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
		newRate1.setTimestamp("20161111");

		Rate newRate2 = new Rate();
		newRate2.setId(2);
		newRate2.setBuyCurrency(2.1);
		newRate2.setFile("hello2");
		newRate2.setRate(2.11);
		newRate2.setSellCurrency(2.111);
		newRate2.setTimestamp("20161212");
		
		rates.put("1", newRate1);
		rates.put("2", newRate2);

	}

	public RateService() {
		init();
	}
	@GET
	@Path("/{id}/")
	public Rate getRate(@PathParam("id") String id) {
		Rate c = rates.get(id);
		return c;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getRates")
	public Response  getAllRates() {
		return Response.ok(rates).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getRates/{date}/")
	public Response getAllRates(@PathParam("date") String date) {
		List accountList = new ArrayList();
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd"); 
		Date requiredDate;
		try {
			requiredDate = (Date)formatter.parse(date);

		for (int i = 0; i <= rates.size(); i++) {
			Rate rateFromList = (Rate) rates.get(String.valueOf(i));
			if (rateFromList == null)
				continue;
			Date dateFromRate = formatter.parse(rateFromList.getTimestamp());
			if (rateFromList != null && dateFromRate.compareTo(requiredDate) == 0)
				accountList.add((Rate) rates.get(String.valueOf(i)));
		}
		} catch (ParseException e) {
		} 
		return Response.ok(accountList).build();
	}

	@GET
	@Path("/processRates")
	public int batchProcessRates() throws Exception {
		// Batch job to fetch, parse, and save rates from the file
		return 1;
	}

}
