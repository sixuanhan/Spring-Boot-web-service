package com.example.userRegistration;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CountryData {
    public static class Country {
        protected static class PopulationCount {
            @JsonProperty("year")
            String year;
            @JsonProperty("value")
            String value;


            protected PopulationCount() {}
            protected PopulationCount(String year, String value) {
                this.year = year;
                this.value = value;
            }

            @Override
            public String toString() {
                return "PopulationCount{" +
                        "year='" + year + '\'' +
                        ", value='" + value + '\'' +
                        '}';
            }
        }

        @JsonProperty("code")
        String code;
        @JsonProperty("iso3")
        String iso3;
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

        @Override
        public String toString() {
            return "Country{" +
                    "country='" + country + '\'' +
                    ", latestPopulation=" + latestPopulation +
                    '}';
        }

        public void findLatestPopulation() {
            for (PopulationCount pop: populationCounts) {
                if (latestPopulation == null || Integer.parseInt(pop.year) > Integer.parseInt(latestPopulation.year))
                    latestPopulation = pop;
            }
        }
    }

    @JsonProperty("error")
    String error;
    @JsonProperty("msg")
    String msg;
    @JsonProperty("data")
    List<Country> list;


    public CountryData() {}

    public CountryData(List<Country> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "CountryData{" +
                "list=" + list +
                '}';
    }
}
