package com.rate.retrieval.service;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rate.retrieval.dao.RateDAO;
import com.rate.retrieval.model.Rate;

@Service
@Transactional
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RateServiceImpl implements RateService{

	Map<String, Rate> rates = new HashMap<String, Rate>();
	private java.nio.file.Path fFilePath;

	
	public RateDAO getRateDAO() {
		return dao;
	}

	public void setRateDAO(RateDAO rateDAO) {
		this.dao = rateDAO;
	}

	public void init() {

		
		Rate newRate1 = new Rate();
		newRate1.setId(1);
		newRate1.setBuyCurrency("USD");
		newRate1.setFile("hello1");
		newRate1.setRate(1.11);
		newRate1.setSellCurrency("PKR");
		newRate1.setTimestamp("20161111");

		Rate newRate2 = new Rate();
		newRate2.setId(2);
		newRate2.setBuyCurrency("USD");
		newRate2.setFile("hello2");
		newRate2.setRate(2.11);
		newRate2.setSellCurrency("PKR");
		newRate2.setTimestamp("20161212");
		
		rates.put("1", newRate1);
		rates.put("2", newRate2);

	}

	public RateServiceImpl() {
		init();
	}
	@GET
	@Path("/{id}/")
	public Response getRate(@PathParam("id") String id) {
		
		List<Rate> c = findRateByDate(id);
		return Response.ok(c).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getRates")
	public Response  getAllRates() {
		return Response.ok(findAllRates()).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getRates/{date}/")
	public Response getAllRates(@PathParam("date") String date) {
		List<Rate> c = findRateByDate(date);
		return Response.ok(c).build();
	}

	@GET
	@Path("/processRates")
	public int batchProcessRates() throws Exception {
		// Batch job to fetch, parse, and save rates from the file
		URL url = this.getClass().getClassLoader().getResource("/");
		// Process each .DAT file for persistance
		Files.walk(Paths.get(url.toURI())).forEach(filePath -> {
			if (filePath.getFileName().toString().endsWith(".DAT")) {
				processFile(filePath);
			}
		});
		
		return 1;
	}
	
	protected void processFile(java.nio.file.Path fFile) {
		try (Scanner scanner = new Scanner(fFile, StandardCharsets.UTF_8.name())) {
			List <Rate> ratesList = new ArrayList<Rate>();
			while (scanner.hasNextLine()) {
				Rate rate = processLine(scanner.nextLine());
				rate.setFile(fFile.getFileName().toString());
				ratesList.add(rate);
			}
			
			saveRates(ratesList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected Rate processLine(String aLine) {
		// use a second Scanner to parse the content of each line
		Rate rateObj = new Rate();
		
		String date = aLine.substring(0, 8);
		String rate = aLine.substring(8, 16);
		String buyCurrency = aLine.substring(16, 19);
		String sellCurrency = aLine.substring(19, 22);
		try {
			rateObj.setTimestamp(date);
			double rateValue = (double) Integer.valueOf(rate) / 100000;
			rateObj.setRate(rateValue);

			rateObj.setBuyCurrency(buyCurrency);
			rateObj.setSellCurrency(sellCurrency);
		}
		catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		return rateObj;
	}

	
	@Autowired
    private RateDAO dao;
    
	@Override
	public Rate findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void saveRates(List<Rate> rates) {
		 dao.saveRates(rates);
	}

	@Override
	public void updateRate(Rate rate) {
		Rate entity = dao.findById(rate.getId());
        if(entity!=null){
            entity.setId(rate.getId());
            entity.setFile(rate.getFile());
            entity.setTimestamp(rate.getTimestamp());
            entity.setRate(rate.getRate());
            entity.setBuyCurrency(rate.getBuyCurrency());
            entity.setSellCurrency(rate.getSellCurrency());
        }
	}

	@Override
	public void deleteRateByDate(String date) {
		dao.deleteRateByDate(date);
	}

	@Override
	public List<Rate> findAllRates() {
		return dao.findAllRates();
	}

	@Override
	public List<Rate> findRateByDate(String date) {
		return dao.findRateByDate(date);
	}

}
