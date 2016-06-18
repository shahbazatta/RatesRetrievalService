package com.rate.retrieval.service;

import java.util.List;

import com.rate.retrieval.model.Rate;

public interface RateService {
 
    Rate findById(int id);
     
    void saveRates(List<Rate> rates);
     
    void updateRate(Rate rate);
     
    void deleteRateByDate(String date);
 
    List<Rate> findAllRates(); 
     
    List<Rate> findRateByDate(String date);
 
     
}