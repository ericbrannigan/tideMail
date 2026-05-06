package com.ericbrannigan.tidemail.tideEmail.EmailFormatter;

import java.util.List;
import org.springframework.stereotype.Component;

import com.ericbrannigan.tidemail.tideEmail.Records.StationResponseListRecord;
import com.ericbrannigan.tidemail.tideEmail.Records.TidePredictionResponse;
import com.ericbrannigan.tidemail.tideEmail.Records.TideResponseRecordList;

@Component
public class TidemailFormatter {

  public String format(
    TideResponseRecordList tideResponse,
    StationResponseListRecord stationResponse
  ) {
    StringBuilder sb = new StringBuilder();
    sb.append("<pre>");
    sb.append("Today's Tides for Your Area\n\n");
    sb.append(stationResponse.stations().get(0).name().toString() + "\n");
    for (TidePredictionResponse prediction : tideResponse.predictions()) {
      String type = prediction.type().equals("H") ? "High Tide" : "Low Tide";
      sb
        .append(type)
        .append(": ")
        .append(prediction.t())
        .append("   |   ")
        .append(prediction.v())
        .append(" ft\n");
    }
    sb.append("</pre>");
    return sb.toString();
  }
}
