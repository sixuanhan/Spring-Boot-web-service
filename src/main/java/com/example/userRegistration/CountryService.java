package com.example.userRegistration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CountryService {
    private Map<String, Long> map = new HashMap<>();

    /*
    Load country data if we haven't done that before and find the population of a country
    Parameter: String country
    Return: the population if success; 0 if failed
     */
    public long getPopulationByCountry(String country) {
        if (map == null) {
            try {
                String uri="https://countriesnow.space/api/v0.1/countries/population";
                RestTemplate restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(uri, String.class);
            ObjectMapper mapper = new ObjectMapper();
            // map to data
            CountryData data = mapper.readValue(result, CountryData.class);

            // load to map
            for (CountryData.Country count: data.list) {
                count.findLatestPopulation();
                map.put(count.country, Long.valueOf(count.latestPopulation.value));
            }
            } catch (Exception e){
                e.printStackTrace();
                return 0;
            }
        }

        return map.getOrDefault(country, (long)0);
    }
}
