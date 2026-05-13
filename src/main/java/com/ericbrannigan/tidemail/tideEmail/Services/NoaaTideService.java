package com.ericbrannigan.tidemail.tideEmail.Services;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.ericbrannigan.tidemail.tideEmail.Records.StationResponseRecordList;
import com.ericbrannigan.tidemail.tideEmail.Records.TideResponseRecordList;

@Service
public class NoaaTideService {

  private final RestClient client = RestClient.create(
    "https://api.tidesandcurrents.noaa.gov"
  );

  public TideResponseRecordList getPredictions(String stationID, String date) {
    return client
      .get()
      .uri(uriBuilder ->
        uriBuilder
          .path("/api/prod/datagetter")
          .queryParam("station", stationID)
          .queryParam("begin_date", date)
          .queryParam("range", 72)
          .queryParam("product", "predictions")
          .queryParam("datum", "MLLW")
          .queryParam("interval", "hilo")
          .queryParam("units", "english")
          .queryParam("time_zone", "lst_ldt")
          .queryParam("format", "json")
          .build()
      )
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .body(TideResponseRecordList.class);
  }

  public StationResponseRecordList getStationInfo(String stationID) {
    return client
      .get()
      .uri(uriBuilder ->
        uriBuilder
          .path("/mdapi/prod/webapi/stations/" + stationID + ".json")
          .build()
      )
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .body(StationResponseRecordList.class);
  }
}
