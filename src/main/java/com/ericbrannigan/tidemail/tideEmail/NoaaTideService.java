package com.ericbrannigan.tidemail.tideEmail;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.http.MediaType;

@Service
public class NoaaTideService {
    private final RestClient client = RestClient.create("https://api.tidesandcurrents.noaa.gov");

    public TideResponse getPredictions(String stationID, String date){
        return client.get()
            .uri(uriBuilder -> uriBuilder
                .path("/api/prod/datagetter")
                .queryParam("station", stationID)
                .queryParam("begin_date", date)
                .queryParam("range", 24)
                .queryParam("product","predictions")
                .queryParam("datum","MLLW")
                .queryParam("interval","hilo")
                .queryParam("units", "english")
                .queryParam("time_zone", "lst_ldt")
                .queryParam("format", "json")
            .build())
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .body(TideResponse.class);
    }
}
