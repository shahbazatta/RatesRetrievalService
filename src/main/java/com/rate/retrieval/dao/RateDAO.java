package com.rate.retrieval.dao;

import java.util.List;

import com.rate.retrieval.model.Rate;

public interface RateDAO {

	Rate findById(int id);
	 
    void saveRates(List<Rate> rates);
     
    void deleteRateByDate(String date);
     
    List<Rate> findAllRates();
 
    List<Rate> findRateByDate(String date);

}
