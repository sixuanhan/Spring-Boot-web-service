package com.example.userRegistration;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CountryData {
    public class Country {
        protected class PopulationCount {
            @JsonProperty("year")
            String year;
            @JsonProperty("value")
            String value;


            protected PopulationCount() {}
            protected PopulationCount(String year, String value) {
                this.year = year;
                this.value = value;
            }
        }

        @JsonProperty("country")
        String country;
        @JsonProperty("populationCounts")
        List<PopulationCount> populationCounts;

        PopulationCount latestPopulation;

        public Country() {}

        public Country(String country, List<PopulationCount> populationCounts) {
            this.country = country;
            this.populationCounts = populationCounts;
            findLatestPopulation();
        }

        public void findLatestPopulation() {
            for (PopulationCount pop: populationCounts) {
                if (latestPopulation == null || Integer.parseInt(pop.year) > Integer.parseInt(latestPopulation.year))
                    latestPopulation = pop;
            }
        }
    }

    @JsonProperty("data")
    List<Country> list;

    public CountryData() {}

    public CountryData(List<Country> list) {
        this.list = list;
    }
}
